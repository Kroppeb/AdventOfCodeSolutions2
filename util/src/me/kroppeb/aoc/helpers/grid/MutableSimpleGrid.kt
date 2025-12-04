@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.*

public class MutableSimpleGrid<T>(public val items: MutableList<MutableList<T>>) : StrictGrid<T>, MutableGrid<T>, Iterable<MutableBoundedGridPoint<T>> {
	override val bounds: Bounds


	init {
		require(items.isEmpty() || items.all { it.size == items.first().size }) { "non consistent length" }
		val maxValue =
			if (Clock.nX != 0) items.lastIndex toP items[0].lastIndex // x is first index
			else items[0].lastIndex toP items.lastIndex// y is first index

		bounds = 0 toP 0 toB maxValue
	}


	override fun set(index: Point, item: T) {
		when (Clock.mode) {
			Clock.Mode.SE -> items[index.x.i][index.y.i] = item
			Clock.Mode.SW -> items[index.x.i].also { it[it.lastIndex - index.y.i] = item}
			Clock.Mode.NE -> items[items.lastIndex - index.x.i][index.y.i] = item
			Clock.Mode.NW -> items[items.lastIndex - index.x.i].also { it[it.lastIndex - index.y.i] = item}
			Clock.Mode.ES -> items[index.y.i][index.x.i] = item
			Clock.Mode.EN -> items[index.y.i].also { it[it.lastIndex - index.x.i] = item}
			Clock.Mode.WS -> items[items.lastIndex - index.y.i][index.x.i] = item
			Clock.Mode.WN -> items[items.lastIndex - index.y.i].also { it[it.lastIndex - index.x.i] = item}
		}
	}

	override fun get(index: Point): T {
		return when (Clock.mode) {
			Clock.Mode.SE -> items[index.x.i][index.y.i]
			Clock.Mode.SW -> items[index.x.i].let { it[it.lastIndex - index.y.i] }
			Clock.Mode.NE -> items[items.lastIndex - index.x.i][index.y.i]
			Clock.Mode.NW -> items[items.lastIndex - index.x.i].let { it[it.lastIndex - index.y.i] }
			Clock.Mode.ES -> items[index.y.i][index.x.i]
			Clock.Mode.EN -> items[index.y.i].let { it[it.lastIndex - index.x.i] }
			Clock.Mode.WS -> items[items.lastIndex - index.y.i][index.x.i]
			Clock.Mode.WN -> items[items.lastIndex - index.y.i].let { it[it.lastIndex - index.x.i] }
		}
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

	override fun iterator(): Iterator<MutableBoundedGridPoint<T>> = bounds.map { this.getBp(it) }.iterator()

	public fun getBpOrNull(point: Point): MutableBoundedGridPoint<T>? =
		if (point in bounds) MutableBoundedGridPoint(point, this) else null

	public fun getBp(point: Point): MutableBoundedGridPoint<T> =
		getBpOrNull(point) ?: throw IndexOutOfBoundsException(point.toString())


	override fun toString(): String {
		return items.joinToString("\n") { it.joinToString(" ") }
	}

	override fun equals(other: Any?): Boolean = other is SimpleGrid<*> && this.items == other.items
	override fun hashCode(): Int = this.items.hashCode()
}


public fun <T> List<List<T>>.mutableGrid(): MutableSimpleGrid<T> {
	if (isEmpty() || first().isEmpty())
		error("empty grid")
	val l = this[0].size
	if (subList(0, size - 1).any { it.size != l })
		error("non consistent length")
	if (last().isEmpty())
		return MutableSimpleGrid(subList(0, size - 1).mut2())
	if (last().size != l)
		error("non consistent length")
	return MutableSimpleGrid(this.mut2())
}

public fun <T> Iterable<List<List<T>>>.mutableGrids(): List<MutableSimpleGrid<T>> = map { it.mutableGrid() }

public inline fun <T, R> MutableSimpleGrid<T>.mapGrid(block: (T) -> R): SimpleGrid<R> = this.items.map2(block).grid()

public fun <T> SimpleGrid<T>.mutable(): MutableSimpleGrid<T> = this.items.mutableGrid()
public fun <T> MutableSimpleGrid<T>.copy(): MutableSimpleGrid<T> = this.items.mutableGrid()
public fun <T> MutableSimpleGrid<T>.immutable(): SimpleGrid<T> = this.items.grid()
