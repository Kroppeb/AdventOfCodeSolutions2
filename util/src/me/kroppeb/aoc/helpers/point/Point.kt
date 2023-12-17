@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.math.atan2
import kotlin.math.sqrt

public data class Point(val x: Sint, val y: Sint) : PointN<Point> {
	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right: Point by lazy { this + Clock.right }
	val down: Point by lazy { this + Clock.down }
	val left: Point by lazy { this + Clock.left }
	val up: Point by lazy { this + Clock.up }

	val north: Point get() = up
	val east: Point get() = right
	val south: Point get() = down
	val west: Point get() = left

	val downRight: Point get() = down.right
	val downLeft: Point get() = down.left
	val upRight: Point get() = up.right
	val upLeft: Point get() = up.left
	val rightDown: Point get() = right.down
	val rightUp: Point get() = right.up
	val leftDown: Point get() = left.down
	val leftUp: Point get() = left.up

	val northEast: Point get() = north.east
	val southEast: Point get() = south.east
	val southWest: Point get() = south.west
	val northWest: Point get() = north.west

	// useful for hex grids
	val downDown: Point get() = down.down
	val rightRight: Point get() = right.right
	val upUp: Point get() = up.up
	val leftLeft: Point get() = left.left

	val northNorth: Point get() = north.north
	val eastEast: Point get() = east.east
	val southSouth: Point get() = south.south
	val westWest: Point get() = west.west


	val r: Point get() = right
	val d: Point get() = down
	val l: Point get() = left
	val u: Point get() = up

	val n: Point get() = north
	val e: Point get() = east
	val s: Point get() = south
	val w: Point get() = west

	val dr: Point get() = down.right
	val dl: Point get() = down.left
	val ur: Point get() = up.right
	val ul: Point get() = up.left
	val rd: Point get() = right.down
	val ru: Point get() = right.up
	val ld: Point get() = left.down
	val lu: Point get() = left.up

	val ne: Point get() = north.east
	val se: Point get() = south.east
	val sw: Point get() = south.west
	val nw: Point get() = north.west

	// useful for hex grids
	val dd: Point get() = down.down
	val rr: Point get() = right.right
	val uu: Point get() = up.up
	val ll: Point get() = left.left

	val nn: Point get() = north.north
	val ee: Point get() = east.east
	val ss: Point get() = south.south
	val ww: Point get() = west.west


	public fun rotateClock(): Point = Clock.right * Clock.up.dot(this) + Clock.up * Clock.left.dot(this)
	public fun rotateAntiClock(): Point = -rotateClock()

	public fun getQuadNeighbours(): List<Point> = listOf(up, right, down, left)
	public fun getDiagonalNeighbours(): List<Point> = listOf(right.down, left.down, left.up, right.up)
	public fun getOctNeighbours(): List<Point> = listOf(
		up.left, up, up.right,
		left, /* this, */ right,
		down.left, down, down.right
	)

	public fun getQuadNeighbourHood(): List<Point> = listOf(this, right, down, left, up)
	public fun getDiagonalNeighbourHood(): List<Point> = listOf(this, right.down, left.down, left.up, right.up)
	public fun getOctNeighbourHood(): List<Point> = listOf(
		up.left, up, up.right,
		left, this, right,
		down.left, down, down.right
	)


	public override fun getMooreNeighbours(): List<Point> = getOctNeighbours()
	public override fun getVonNeumannNeighbours(): List<Point> = getQuadNeighbours()
	public override fun getMooreNeighbourHood(): List<Point> = getOctNeighbourHood()
	public override fun getVonNeumannNeighbourHood(): List<Point> = getQuadNeighbourHood()

	override operator fun unaryMinus(): Point = -x toP -y

	override operator fun minus(other: Point): Point = x - other.x toP y - other.y
	override operator fun plus(other: Point): Point = x + other.x toP y + other.y

	public operator fun minus(other: Char): Point = this - other.toPoint()
	public operator fun plus(other: Char): Point = this + other.toPoint()


	override operator fun times(other: Point): Point = x * other.x toP y * other.y
	override operator fun div(other: Point): Point = x / other.x toP y / other.y
	override operator fun rem(other: Point): Point = x % other.x toP y % other.y

	override operator fun times(other: Sint): Point = x * other toP y * other
	override operator fun div(other: Sint): Point = x / other toP y / other
	override operator fun rem(other: Sint): Point = x % other toP y % other

	override fun abs(): Point = abs(x) toP abs(y)

	override fun sqrDist(): Sint = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().l.toDouble())
	override fun manDist(): Sint = abs(x) + abs(y)
	override fun chebyshevDist(): Sint = max(abs(x), abs(y))

	public fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Sint = gcd(abs(x), abs(y))

	override fun min(other: Point): Point = min(this.x, other.x) toP min(this.y, other.y)
	override fun max(other: Point): Point = max(this.x, other.x) toP max(this.y, other.y)

	override fun dot(other: Point): Sint = this.x * other.x + this.y * other.y
	public fun cross(other: Point): Sint = this.x * other.y - this.y * other.x

	public fun isLeftOf(other: Point): Boolean = Clock.left.dot(this) > Clock.left.dot(other)
	public fun isRightOf(other: Point): Boolean = Clock.right.dot(this) > Clock.right.dot(other)
	public fun isAbove(other: Point): Boolean = Clock.up.dot(this) > Clock.up.dot(other)
	public fun isBelow(other: Point): Boolean = Clock.down.dot(this) > Clock.down.dot(other)
	public fun sameLeftRight(other: Point): Boolean = Clock.left.dot(this) == Clock.left.dot(other)
	public fun sameUpDown(other: Point): Boolean = Clock.up.dot(this) == Clock.up.dot(other)


	public fun northsInc(): Sequence<Point> = this.sequence(Clock.up)
	public fun southsInc(): Sequence<Point> = this.sequence(Clock.down)
	public fun eastsInc(): Sequence<Point> = this.sequence(Clock.right)
	public fun westsInc(): Sequence<Point> = this.sequence(Clock.left)

	public fun norths(): Sequence<Point> = this.northsInc().drop(1)
	public fun souths(): Sequence<Point> = this.southsInc().drop(1)
	public fun easts(): Sequence<Point> = this.eastsInc().drop(1)
	public fun wests(): Sequence<Point> = this.westsInc().drop(1)

	override fun sign(): Point = x.sign() toP y.sign()
	override fun isZero(): Boolean = x.isZero() && y.isZero()
	override fun isAxisAligned(): Boolean = x.isZero() || y.isZero()

	public fun toPair(): Pair<Sint, Sint> = x to y

	public fun isOnXAxis(): Boolean = y.isZero()
	public fun isOnYAxis(): Boolean = x.isZero()

	public fun isHorizontal(): Boolean = this.dot(Clock.up) == 0.s
	public fun isVertical(): Boolean = this.dot(Clock.right) == 0.s

	public companion object {
		public val ZERO: Point = 0 toP 0
		public val DIRS: List<Point> get() = ZERO.getQuadNeighbours() // has to be lazy cause directions can change
	}
}
