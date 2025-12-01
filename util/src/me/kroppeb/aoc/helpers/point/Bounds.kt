@file:Suppress("MemberVisibilityCanBePrivate") @file:OptIn(ExperimentalTypeInference::class)

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.*
import java.math.BigInteger
import kotlin.experimental.ExperimentalTypeInference


public data class Bounds(
	val xs: SintRange,
	val ys: SintRange,
) : BoundsN<Bounds, Point> {

	public constructor(lower: Point, higher: Point) : this(lower.x..higher.x, lower.y..higher.y)
	public constructor(xs: IntRange, ys: IntRange) : this(xs.s, ys.s)

	public override val lowerCoords: Point get() = xs.first toP ys.first
	public override val higherCoords: Point get() = xs.last toP ys.last

	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	public override fun intersect(other: Bounds): Bounds = Bounds(
		xs.intersect(other.xs),
		ys.intersect(other.ys),
	)


	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	public override fun merge(other: Bounds): Bounds = Bounds(
		this.lowerCoords.min(other.lowerCoords),
		this.higherCoords.max(other.higherCoords),
	)


	public override fun weight(): Sint = area
	public override fun weightB(): BigInteger = areaB

	public override fun fracture(other: Bounds): List<Bounds> = cartesianProductOf(
		xs.fracture(other.xs), ys.fracture(other.ys)
	) { x, y -> Bounds(x, y) }


	public fun expand(x: Sint, y: Sint): Bounds {
		val offset = x toP y
		return Bounds(this.lowerCoords - offset, this.higherCoords + offset)
	}

	public fun expand(i: Sint): Bounds = expand(i, i)
	public fun expand(x: Int, y: Int): Bounds = expand(x.s, y.s)
	public fun expand(i: Int): Bounds = expand(i.s)
	public fun retract(x: Sint, y: Sint): Bounds = expand(-x, -y)
	public fun retract(i: Sint): Bounds = expand(-i)
	public fun retract(x: Int, y: Int): Bounds = expand(-x, -y)
	public fun retract(i: Int): Bounds = expand(-i)

	public fun frac(i: Int): List<Bounds> = xs.frac(i).cartesianProduct(ys.frac(i)).map { (a, b) -> Bounds(a, b) }

	val isSquare: Boolean get() = xs.sizeS == ys.sizeS
	val ne: Point
		get() = when (Clock.nX + Clock.eX) {
			1 -> xs.last() // x increases to the top right
			-1 -> xs.first() // x decreases to the top right
			else -> error("")
		} toP when (Clock.nY + Clock.eY) {
			1 -> ys.last() // y increases to the top right
			-1 -> ys.first() // y decreases to the top right
			else -> error("")
		}
	val nw: Point
		get() = when (Clock.nX - Clock.eX) {
			1 -> xs.last() // x increases to the top left
			-1 -> xs.first() // x decreases to the top left
			else -> error("")
		} toP when (Clock.nY - Clock.eY) {
			1 -> ys.last() // y increases to the top left
			-1 -> ys.first() // y decreases to the top left
			else -> error("")
		}
	val se: Point get() = higherCoords + lowerCoords - nw
	val sw: Point get() = higherCoords + lowerCoords - ne
	val upperLeft: Point get() = nw
	val upperRight: Point get() = ne
	val lowerLeft: Point get() = sw
	val lowerRight: Point get() = se

	override operator fun iterator(): Iterator<Point> = cartesianProductOf(xs, ys) { x, y -> x toP y }.iterator()

	val xSize: Sint get() = xs.sizeS
	val ySize: Sint get() = ys.sizeS
	override val sizes: Point get() = xSize toP ySize

	// Will overflow for MEGA
	val area: Sint get() = xSize * ySize

	val xSizeB: BigInteger get() = xs.sizeB
	val ySizeB: BigInteger get() = ys.sizeB
	val sizesB: Pair<BigInteger, BigInteger> get() = xSizeB to ySizeB

	// Will overflow for MEGA
	val areaB: BigInteger get() = xSizeB * ySizeB

	@Deprecated("use area", ReplaceWith("area"))
	override val size: Int get() = area.i
	override fun contains(element: Point): Boolean = element.x in xs && element.y in ys

	override fun isEmpty(): Boolean = xs.isEmpty() || ys.isEmpty()

	override fun containsAll(elements: Collection<Point>): Boolean = elements.all { it in this }

	public fun exactCenter(): Point = xs.exactCenter() toP ys.exactCenter()

	public fun leftEdge(): Line = this.upperLeft toL this.lowerLeft
	public fun rightEdge(): Line = this.upperRight toL this.lowerRight
	public fun topEdge(): Line = this.upperLeft toL this.upperRight
	public fun bottomEdge(): Line = this.lowerLeft toL this.lowerRight

	public fun northEdge(): Line = topEdge()
	public fun southEdge(): Line = bottomEdge()
	public fun eastEdge(): Line = rightEdge()
	public fun westEdge(): Line = leftEdge()

	public fun edges(): List<Line> = listOf(northEdge(), southEdge(), eastEdge(), westEdge())

	public fun northsInc(p: Point): List<Point> {
		require(p in this)
		return p.sequenceInc(Clock.north).takeWhile { it in this }.toList()
	}

	public fun southsInc(p: Point): List<Point> {
		require(p in this)
		return p.sequenceInc(Clock.south).takeWhile { it in this }.toList()
	}

	public fun eastsInc(p: Point): List<Point> {
		require(p in this)
		return p.sequenceInc(Clock.east).takeWhile { it in this }.toList()
	}

	public fun westsInc(p: Point): List<Point> {
		require(p in this)
		return p.sequenceInc(Clock.west).takeWhile { it in this }.toList()
	}

	public fun norths(p: Point): List<Point> {
		require(p in this)
		return p.sequence(Clock.north).takeWhile { it in this }.toList()
	}

	public fun souths(p: Point): List<Point> {
		require(p in this)
		return p.sequence(Clock.south).takeWhile { it in this }.toList()
	}

	public fun easts(p: Point): List<Point> {
		require(p in this)
		return p.sequence(Clock.east).takeWhile { it in this }.toList()
	}

	public fun wests(p: Point): List<Point> {
		require(p in this)
		return p.sequence(Clock.west).takeWhile { it in this }.toList()
	}

	public fun horizontalLineThrough(p: Point): Line {
		// left to right
		require(p in this)
		val offset = ((p - this.upperLeft).dot(Clock.down) * Clock.down)
		val left = this.upperLeft + offset
		val right = this.upperRight + offset
		return left toL right
	}

	public fun verticalLineThrough(p: Point): Line {
		// top to bottom
		require(p in this)
		val offset = ((p - this.upperLeft).dot(Clock.right) * Clock.right)
		val top = this.upperLeft + offset
		val bottom = this.lowerLeft + offset
		return top toL bottom
	}

	public fun rows(): List<Line> {
		if (Clock.nY != 0) {
			val yys = if (Clock.nY == -1) ys else ys.reversed()
			val (xStart, xStop) = if (Clock.eX == 1) xs.first() to xs.last() else xs.last() to xs.first()
			return yys.map { y -> (xStart toP y) toL (xStop toP y) }
		} else {
			val xxs = if (Clock.nX == -1) xs else xs.reversed()
			val (yStart, yStop) = if (Clock.eY == 1) ys.first() to ys.last() else ys.last() to ys.first()
			return xxs.map{ x -> (x toP yStart) toL (x toP yStop)}
		}
	}

	public fun columns(): List<Line> {
		if (Clock.eY != 0) {
			val yys = if (Clock.eY == 1) ys else ys.reversed()
			val (xStart, xStop) = if (Clock.nX == -1) xs.first() to xs.last() else xs.last() to xs.first()
			return yys.map { y -> (xStart toP y) toL (xStop toP y) }
		} else {
			val xxs = if (Clock.eX == 1) xs else xs.reversed()
			val (yStart, yStop) = if (Clock.nY == -1) ys.first() to ys.last() else ys.last() to ys.first()
			return xxs.map{ x -> (x toP yStart) toL (x toP yStop)}
		}
	}


	public companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		public val INFINITE: Bounds = (Long.MIN_VALUE toP Long.MIN_VALUE) toB (Long.MAX_VALUE toP Long.MAX_VALUE)
		public val EMPTY: Bounds = Bounds(0 toP 0, -1 toP -1)
		public val LARGE: Bounds = (Sint.NEG_MEGA toP Sint.NEG_MEGA) toB (Sint.POS_MEGA toP Sint.POS_MEGA)
	}
}


public infix fun Point.toB(other: Point): Bounds =
	Bounds(min(this.x, other.x) toP min(this.y, other.y), max(this.x, other.x) toP max(this.y, other.y))


public operator fun Point.rangeTo(other: Point): Bounds = this toB other
public operator fun Point.rem(bounds: Bounds): Point = x % bounds.xs toP y % bounds.ys

public fun Iterable<Point>.bounds(): Bounds = Bounds(minMaxRangeOf { it.x }, minMaxRangeOf { it.y })


@OverloadResolutionByLambdaReturnType
@JvmName("printString")
public inline fun Bounds.print(transform: (Point) -> String) {
	if (true) {
		for(row in this.rows()) {
			for (p in row) {
				print(transform(p))
			}
			println()
		}
		return
	}

	if (Clock.nY != 0) {
		val yys = if (Clock.nY == -1) ys else ys.reversed()
		val xxs = if (Clock.eX == 1) xs else xs.reversed()
		for (y in yys) {
			for (x in xxs) {
				print(transform(x toP y))
			}
			println()
		}
	} else {
		val xxs = if (Clock.nX == -1) xs else xs.reversed()
		val yys = if (Clock.eY == 1) ys else ys.reversed()
		for (x in xxs) {
			for (y in yys) {
				print(transform(x toP y))
			}
			println()
		}
	}
}

@OverloadResolutionByLambdaReturnType
public inline fun Bounds.print(transform: (Point) -> Char) {
	print { "" + transform(it) }
}