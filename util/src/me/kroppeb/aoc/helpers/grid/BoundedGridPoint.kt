@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.Clock
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


	val downRight: BGP<T>? get() = down?.right
	val downLeft: BGP<T>? get() = down?.left
	val upRight: BGP<T>? get() = up?.right
	val upLeft: BGP<T>? get() = up?.left
	val rightDown: BGP<T>? get() = right?.down
	val rightUp: BGP<T>? get() = right?.up
	val leftDown: BGP<T>? get() = left?.down
	val leftUp: BGP<T>? get() = left?.up

	val northEast: BGP<T>? get() = north?.east
	val southEast: BGP<T>? get() = south?.east
	val southWest: BGP<T>? get() = south?.west
	val northWest: BGP<T>? get() = north?.west

	// useful for hex grids, not sure how useful here
	val downDown: BGP<T>? get() = down?.down
	val rightRight: BGP<T>? get() = right?.right
	val upUp: BGP<T>? get() = up?.up
	val leftLeft: BGP<T>? get() = left?.left

	val northNorth: BGP<T>? get() = north?.north
	val eastEast: BGP<T>? get() = east?.east
	val southSouth: BGP<T>? get() = south?.south
	val westWest: BGP<T>? get() = west?.west

	private fun Iterable<Point>.fix() = mapNotNull { g.getBpOrNull(it) }

	public fun getQuadNeighbours(): List<BGP<T>> = p.getQuadNeighbours().fix()
	public fun getDiagonalNeighbours(): List<BGP<T>> = p.getDiagonalNeighbours().fix()
	public fun getOctNeighbours(): List<BGP<T>> = p.getOctNeighbours().fix()

	public fun getQuadNeighbourHood(): List<BGP<T>> = p.getQuadNeighbourHood().fix()
	public fun getDiagonalNeighbourHood(): List<BGP<T>> = p.getDiagonalNeighbourHood().fix()
	public fun getOctNeighbourHood(): List<BGP<T>> = p.getOctNeighbourHood().fix()

	public fun getMooreNeighbours(): List<BGP<T>> = getOctNeighbours()
	public fun getVonNeumannNeighbours(): List<BGP<T>> = getQuadNeighbours()

	public operator fun minus(other: Point): BGP<T> = g.getBp(p - other)
	public operator fun plus(other: Point): BGP<T> = g.getBp(p + other)
	public fun offsetOrNull(other: Point): BGP<T>? = g.getBpOrNull(p + other)

	public operator fun minus(other: Char): BGP<T> = this - other.toPoint()
	public operator fun plus(other: Char): BGP<T> = this + other.toPoint()
	public fun offsetOrNull(other: Char): BGP<T>? = g.getBpOrNull(p + other)

	public operator fun minus(other: BGP<T>): Point = p - other.p

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

	public fun sequenceIncSeq(step: Point): Sequence<BGP<T>> =
		p.sequenceInc(step).takeWhile { it in b }.map { g.getBp(it) }

	public fun sequenceSeq(step: Point): Sequence<BGP<T>> = p.sequence(step).takeWhile { it in b }.map { g.getBp(it) }

	public fun sequenceInc(step: Point): List<BGP<T>> = sequenceIncSeq(step).toList()
	public fun sequence(step: Point): List<BGP<T>> = sequenceSeq(step).toList()

	public fun northsInc(): List<BGP<T>> = sequenceInc(Clock.up)
	public fun southsInc(): List<BGP<T>> = sequenceInc(Clock.down)
	public fun eastsInc(): List<BGP<T>> = sequenceInc(Clock.right)
	public fun westsInc(): List<BGP<T>> = sequenceInc(Clock.left)

	public fun norths(): List<BGP<T>> = sequence(Clock.up)
	public fun souths(): List<BGP<T>> = sequence(Clock.down)
	public fun easts(): List<BGP<T>> = sequence(Clock.right)
	public fun wests(): List<BGP<T>> = sequence(Clock.left)

	public fun northsIncSeq(): Sequence<BGP<T>> = sequenceIncSeq(Clock.up)
	public fun southsIncSeq(): Sequence<BGP<T>> = sequenceIncSeq(Clock.down)
	public fun eastsIncSeq(): Sequence<BGP<T>> = sequenceIncSeq(Clock.right)
	public fun westsIncSeq(): Sequence<BGP<T>> = sequenceIncSeq(Clock.left)

	public fun northsSeq(): Sequence<BGP<T>> = sequenceSeq(Clock.up)
	public fun southsSeq(): Sequence<BGP<T>> = sequenceSeq(Clock.down)
	public fun eastsSeq(): Sequence<BGP<T>> = sequenceSeq(Clock.right)
	public fun westsSeq(): Sequence<BGP<T>> = sequenceSeq(Clock.left)

	override fun toString(): String {
		return "BGP(p=$p, v=$v)"
	}
}
