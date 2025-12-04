package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.point.Bounds
import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toPoint
import me.kroppeb.aoc.helpers.sint.Sint

public typealias MBGP<T> = MutableBoundedGridPoint<T>
public typealias MBGPC = MutableBoundedGridPoint<Char>

public data class MutableBoundedGridPoint<T>(val p: Point, val g: MutableSimpleGrid<T>) {
	var v: T
		get() = g[p]
		set(value) {
			g[p] = value
		}
	val b: Bounds get() = g.bounds

	val right: MBGP<T>? get() = g.getBpOrNull(this.p.right)
	val down: MBGP<T>? get() = g.getBpOrNull(this.p.down)
	val left: MBGP<T>? get() = g.getBpOrNull(this.p.left)
	val up: MBGP<T>? get() = g.getBpOrNull(this.p.up)

	val north: MBGP<T>? get() = up
	val east: MBGP<T>? get() = right
	val south: MBGP<T>? get() = down
	val west: MBGP<T>? get() = left


	val downRight: MBGP<T>? get() = down?.right
	val downLeft: MBGP<T>? get() = down?.left
	val upRight: MBGP<T>? get() = up?.right
	val upLeft: MBGP<T>? get() = up?.left
	val rightDown: MBGP<T>? get() = right?.down
	val rightUp: MBGP<T>? get() = right?.up
	val leftDown: MBGP<T>? get() = left?.down
	val leftUp: MBGP<T>? get() = left?.up

	val northEast: MBGP<T>? get() = north?.east
	val southEast: MBGP<T>? get() = south?.east
	val southWest: MBGP<T>? get() = south?.west
	val northWest: MBGP<T>? get() = north?.west

	// useful for hex grids, not sure how useful here
	val downDown: MBGP<T>? get() = down?.down
	val rightRight: MBGP<T>? get() = right?.right
	val upUp: MBGP<T>? get() = up?.up
	val leftLeft: MBGP<T>? get() = left?.left

	val northNorth: MBGP<T>? get() = north?.north
	val eastEast: MBGP<T>? get() = east?.east
	val southSouth: MBGP<T>? get() = south?.south
	val westWest: MBGP<T>? get() = west?.west

	private fun Iterable<Point>.fix() = mapNotNull { g.getBpOrNull(it) }

	public fun getQuadNeighbours(): List<MBGP<T>> = p.getQuadNeighbours().fix()
	public fun getDiagonalNeighbours(): List<MBGP<T>> = p.getDiagonalNeighbours().fix()
	public fun getOctNeighbours(): List<MBGP<T>> = p.getOctNeighbours().fix()

	public fun getQuadNeighbourHood(): List<MBGP<T>> = p.getQuadNeighbourHood().fix()
	public fun getDiagonalNeighbourHood(): List<MBGP<T>> = p.getDiagonalNeighbourHood().fix()
	public fun getOctNeighbourHood(): List<MBGP<T>> = p.getOctNeighbourHood().fix()

	public fun getMooreNeighbours(): List<MBGP<T>> = getOctNeighbours()
	public fun getVonNeumannNeighbours(): List<MBGP<T>> = getQuadNeighbours()

	public operator fun minus(other: Point): MBGP<T> = g.getBp(p - other)
	public operator fun plus(other: Point): MBGP<T> = g.getBp(p + other)
	public fun offsetOrNull(other: Point): MBGP<T>? = g.getBpOrNull(p + other)
	public fun offset(other: Point): MBGP<T> = this + other

	public operator fun minus(other: Char): MBGP<T> = this - other.toPoint()
	public operator fun plus(other: Char): MBGP<T> = this + other.toPoint()
	public fun offsetOrNull(other: Char): MBGP<T>? = g.getBpOrNull(p + other)

	public operator fun minus(other: MBGP<T>): Point = p - other.p

	public fun sqrDistTo(other: Point): Sint = (this.p - other).sqrDist()
	public fun distTo(other: Point): Double = (this.p - other).dist()
	public fun manDistTo(other: Point): Sint = (this.p - other).manDist()

	public fun sqrDistTo(other: MutableBoundedGridPoint<*>): Sint = (this.p - other.p).sqrDist()
	public fun distTo(other: MutableBoundedGridPoint<*>): Double = (this.p - other.p).dist()
	public fun manDistTo(other: MutableBoundedGridPoint<*>): Sint = (this.p - other.p).manDist()


	public fun isLeftOf(other: Point): Boolean = p.isLeftOf(other)
	public fun isRightOf(other: Point): Boolean = p.isRightOf(other)
	public fun isAbove(other: Point): Boolean = p.isAbove(other)
	public fun isBelow(other: Point): Boolean = p.isBelow(other)
	public fun sameLeftRight(other: Point): Boolean = p.sameLeftRight(other)
	public fun sameUpDown(other: Point): Boolean = p.sameUpDown(other)

	public fun isLeftOf(other: MutableBoundedGridPoint<*>): Boolean = isLeftOf(other.p)
	public fun isRightOf(other: MutableBoundedGridPoint<*>): Boolean = isRightOf(other.p)
	public fun isAbove(other: MutableBoundedGridPoint<*>): Boolean = isAbove(other.p)
	public fun isBelow(other: MutableBoundedGridPoint<*>): Boolean = isBelow(other.p)
	public fun sameLeftRight(other: MutableBoundedGridPoint<*>): Boolean = sameLeftRight(other.p)
	public fun sameUpDown(other: MutableBoundedGridPoint<*>): Boolean = sameUpDown(other.p)

	public fun sequenceIncSeq(step: Point): Sequence<MBGP<T>> =
		p.sequenceInc(step).takeWhile { it in b }.map { g.getBp(it) }

	public fun sequenceSeq(step: Point): Sequence<MBGP<T>> = p.sequence(step).takeWhile { it in b }.map { g.getBp(it) }

	public fun sequenceInc(step: Point): List<MBGP<T>> = sequenceIncSeq(step).toList()
	public fun sequence(step: Point): List<MBGP<T>> = sequenceSeq(step).toList()

	public fun northsInc(): List<MBGP<T>> = sequenceInc(Clock.up)
	public fun southsInc(): List<MBGP<T>> = sequenceInc(Clock.down)
	public fun eastsInc(): List<MBGP<T>> = sequenceInc(Clock.right)
	public fun westsInc(): List<MBGP<T>> = sequenceInc(Clock.left)

	public fun norths(): List<MBGP<T>> = sequence(Clock.up)
	public fun souths(): List<MBGP<T>> = sequence(Clock.down)
	public fun easts(): List<MBGP<T>> = sequence(Clock.right)
	public fun wests(): List<MBGP<T>> = sequence(Clock.left)

	public fun northsIncSeq(): Sequence<MBGP<T>> = sequenceIncSeq(Clock.up)
	public fun southsIncSeq(): Sequence<MBGP<T>> = sequenceIncSeq(Clock.down)
	public fun eastsIncSeq(): Sequence<MBGP<T>> = sequenceIncSeq(Clock.right)
	public fun westsIncSeq(): Sequence<MBGP<T>> = sequenceIncSeq(Clock.left)

	public fun northsSeq(): Sequence<MBGP<T>> = sequenceSeq(Clock.up)
	public fun southsSeq(): Sequence<MBGP<T>> = sequenceSeq(Clock.down)
	public fun eastsSeq(): Sequence<MBGP<T>> = sequenceSeq(Clock.right)
	public fun westsSeq(): Sequence<MBGP<T>> = sequenceSeq(Clock.left)

	override fun toString(): String {
		return "MBGP(p=$p, v=$v)"
	}
}