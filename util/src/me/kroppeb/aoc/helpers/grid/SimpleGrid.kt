@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.graph.CGraph
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

public class SimpleGrid<T>(public val items: List<List<T>>) : StrictGrid<T>, Iterable<BoundedGridPoint<T>> {
	public override val bounds: Bounds

	init {
		require(items.isEmpty() || items.all { it.size == items.first().size }) { "non consistent length" }
		val maxValue =
			if (Clock.nX != 0) items.lastIndex toP items[0].lastIndex // x is first index
			else items[0].lastIndex toP items.lastIndex// y is first index

		bounds = 0 toP 0 toB maxValue
	}

	override fun get(index: Point): T = when (Clock.mode) {
		Clock.Mode.SE -> items[index.x.i][index.y.i]
		Clock.Mode.SW -> items[index.x.i].let { it[it.lastIndex - index.y.i] }
		Clock.Mode.NE -> items[items.lastIndex - index.x.i][index.y.i]
		Clock.Mode.NW -> items[items.lastIndex - index.x.i].let { it[it.lastIndex - index.y.i] }
		Clock.Mode.ES -> items[index.y.i][index.x.i]
		Clock.Mode.EN -> items[index.y.i].let { it[it.lastIndex - index.x.i] }
		Clock.Mode.WS -> items[items.lastIndex - index.y.i][index.x.i]
		Clock.Mode.WN -> items[items.lastIndex - index.y.i].let { it[it.lastIndex - index.x.i] }
	}

	public fun rows(): List<List<T>> = items
	public fun cols(): List<List<T>> = items.transpose()
	public fun rowsCols(): List<List<T>> = items + items.transpose()
	public fun diag1(): List<T> {
		assert(bounds.isSquare)
		return items.mapIndexed { i, row -> row[i] }
	}

	public fun diag2(): List<T> {
		assert(bounds.isSquare)
		return items.mapIndexed { i, row -> row[items.size - 1 - i] }
	}

	public fun diagonals(): List<List<T>> = listOf(diag1(), diag2())
	public fun rowsColsDiagonals(): Iterable<List<T>> = rowsCols() + diagonals()

	public fun allItems(): List<T> = items.flatten()

	override fun toString(): String {
		return items.joinToString("\n") { it.joinToString(" ") }
	}

	override fun iterator(): Iterator<BoundedGridPoint<T>> = bounds.map { this.getBp(it) }.iterator()

	public fun getBpOrNull(point: Point): BoundedGridPoint<T>? =
		if (point in bounds) BoundedGridPoint(point, this[point], this) else null


	public fun getBp(point: Point): BoundedGridPoint<T> = getBpOrNull(point) ?: throw IndexOutOfBoundsException(point.toString())

	public fun asQuadGraph(): CGraph<BoundedGridPoint<T>> = asQuadGraphWeight { _, _ -> 1.s }

	public fun asQuadGraphWeight(weight: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Sint?): CGraph<BoundedGridPoint<T>> =
		CGraph(this.associateWith { a ->
			a.getQuadNeighbours().associateWithNotNull { b -> weight(a, b) }.filterNotNullValues()
		})

	public fun asQuadGraph(connected: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Boolean): CGraph<BoundedGridPoint<T>> =
		asQuadGraphWeight { a, b -> if (connected(a, b)) 1.s else null }

	public fun asOctGraph(): CGraph<BoundedGridPoint<T>> = asOctGraphWeight { _, _ -> 1.s }

	public fun asOctGraphWeight(weight: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Sint?): CGraph<BoundedGridPoint<T>> =
		CGraph(this.associateWith { a ->
			a.getOctNeighbours().associateWithNotNull { b -> weight(a, b) }.filterNotNullValues()
		})

	public fun asOctGraph(connected: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Boolean): CGraph<BoundedGridPoint<T>> =
		asOctGraphWeight { a, b -> if (connected(a, b)) 1.s else null }
}


public fun <T> List<List<T>>.grid(): SimpleGrid<T> {
	if (isEmpty() || first().isEmpty()) error("empty grid") // TODO: should this be an error or a warning?
	val l = this[0].size
	if (subList(0, lastIndex - 1).any { it.size != l }) error("non consistent length")
	if (last().isEmpty()) return SimpleGrid(subList(0, lastIndex)) // TODO: warn
	if (last().size != l) error("non consistent length")
	return SimpleGrid(this)
}

@JvmName("gridStrings")
public fun List<String>.grid(): SimpleGrid<Char> = e().grid()

public fun <T> Iterable<List<List<T>>>.grids(): List<SimpleGrid<T>> = map { it.grid() }
