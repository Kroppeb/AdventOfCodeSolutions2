@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.point.Bounds
import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.point.toPoint
import me.kroppeb.aoc.helpers.sint.Sint

public typealias BGP<T> = BoundedGridPoint<T>
public typealias BGPC = BoundedGridPoint<Char>

public data class BoundedGridPoint<T>(val p: Point, val v: T, val g: SimpleGrid<T>) {
	val b: Bounds get() = g.bounds

	val right: BGP<T>? get() = g.getBpOrNull(this.p.right)
	val down: BGP<T>? get() = g.getBpOrNull(this.p.down)
	val left: BGP<T>? get() = g.getBpOrNull(this.p.left)
	val up: BGP<T>? get() = g.getBpOrNull(this.p.up)

	val north: BGP<T>? get() = up
	val east: BGP<T>? get() = right
	val south: BGP<T>? get() = down
	val west: BGP<T>? get() = left

	private fun Iterable<Point>.fix() = mapNotNull { g.getBpOrNull(it) }

	public fun getQuadNeighbours(): List<BGP<T>> = p.getQuadNeighbours().fix()
	public fun getDiagonalNeighbours(): List<BGP<T>> = p.getDiagonalNeighbours().fix()
	public fun getOctNeighbours(): List<BGP<T>> = p.getOctNeighbours().fix()

	public fun getQuadNeighbourHood(): List<BGP<T>> = p.getQuadNeighbourHood().fix()
	public fun getDiagonalNeighbourHood(): List<BGP<T>> = p.getDiagonalNeighbourHood().fix()
	public fun getOctNeighbourHood(): List<BGP<T>> = p.getOctNeighbourHood().fix()

	public fun getMooreNeighbours(): List<BGP<T>> = getOctNeighbours()
	public fun getVonNeumannNeighbours(): List<BGP<T>> = getQuadNeighbours()

	public operator fun minus(other: Point): BGP<T> = g.getBp(p.x - other.x toP p.y - other.y)
	public operator fun plus(other: Point): BGP<T> = g.getBp(p.x + other.x toP p.y + other.y)

	public operator fun minus(other: Char): BGP<T> = this - other.toPoint()
	public operator fun plus(other: Char): BGP<T> = this + other.toPoint()

	public fun sqrDistTo(other: Point): Sint = (this.p - other).sqrDist()
	public fun distTo(other: Point): Double = (this.p - other).dist()
	public fun manDistTo(other: Point): Sint = (this.p - other).manDist()

	public fun sqrDistTo(other: BoundedGridPoint<*>): Sint = (this.p - other.p).sqrDist()
	public fun distTo(other: BoundedGridPoint<*>): Double = (this.p - other.p).dist()
	public fun manDistTo(other: BoundedGridPoint<*>): Sint = (this.p - other.p).manDist()


	public fun isLeftOf(other: Point): Boolean = p.isLeftOf(other)
	public fun isRightOf(other: Point): Boolean = p.isRightOf(other)
	public fun isAbove(other: Point): Boolean = p.isAbove(other)
	public fun isBelow(other: Point): Boolean = p.isBelow(other)
	public fun sameLeftRight(other: Point): Boolean = p.sameLeftRight(other)
	public fun sameUpDown(other: Point): Boolean = p.sameUpDown(other)

	public fun isLeftOf(other: BoundedGridPoint<*>): Boolean = isLeftOf(other.p)
	public fun isRightOf(other: BoundedGridPoint<*>): Boolean = isRightOf(other.p)
	public fun isAbove(other: BoundedGridPoint<*>): Boolean = isAbove(other.p)
	public fun isBelow(other: BoundedGridPoint<*>): Boolean = isBelow(other.p)
	public fun sameLeftRight(other: BoundedGridPoint<*>): Boolean = sameLeftRight(other.p)
	public fun sameUpDown(other: BoundedGridPoint<*>): Boolean = sameUpDown(other.p)

	public fun northsInc(): List<BGP<T>> = p.northsInc().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun southsInc(): List<BGP<T>> = p.southsInc().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun eastsInc(): List<BGP<T>> = p.eastsInc().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun westsInc(): List<BGP<T>> = p.westsInc().takeWhile { it in b }.map { g.getBp(it) }.toList()

	public fun norths(): List<BGP<T>> = p.norths().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun souths(): List<BGP<T>> = p.souths().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun easts(): List<BGP<T>> = p.easts().takeWhile { it in b }.map { g.getBp(it) }.toList()
	public fun wests(): List<BGP<T>> = p.wests().takeWhile { it in b }.map { g.getBp(it) }.toList()

	override fun toString(): String {
		return "BGP(p=$p, v=$v)"
	}
}
