package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.*


data class Bounds(val xs: SintRange, val ys: SintRange) : Collection<Point> {

	constructor(lower: PointI, higher: PointI) : this(lower.sint, higher.sint)
	constructor(lower: Point, higher: Point) : this(lower.x..higher.x, lower.y..higher.y)
	constructor(xs: IntRange, ys: IntRange) : this(xs.s, ys.s)

	val lowerCoords: Point get() = xs.first toP ys.first
	val higherCoords: Point get() = xs.last toP ys.last

	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: Bounds): Bounds = Bounds(xs.intersect(other.xs), ys.intersect(other.ys))
	fun intersect(other: IBoundsI): Bounds = Bounds(xs.intersect(other.xs), ys.intersect(other.ys))


	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: Bounds): Bounds =
		Bounds(this.lowerCoords.min(other.lowerCoords), this.higherCoords.max(other.higherCoords))

	fun merge(other: IBoundsI): Bounds =
		Bounds(this.lowerCoords.min(other.lower.sint), this.higherCoords.max(other.higher.sint))

	fun weight(): Sint = area

	fun fracture(other: Bounds): List<Bounds> = cartesianProductOf(
		xs.fracture(other.xs), ys.fracture(other.ys)
	) { x, y -> Bounds(x, y) }


	fun expand(x: Sint, y: Sint): Bounds {
		val offset = x toP y
		return Bounds(this.lowerCoords - offset, this.higherCoords + offset)
	}

	fun expand(i: Sint): Bounds = expand(i, i)
	fun expand(x: Int, y: Int): Bounds = expand(x.s, y.s)
	fun expand(i: Int): Bounds = expand(i.s)
	fun retract(x: Sint, y: Sint): Bounds = expand(-x, -y)
	fun retract(i: Sint): Bounds = expand(-i)
	fun retract(x: Int, y: Int): Bounds = expand(-x, -y)
	fun retract(i: Int): Bounds = expand(-i)

	fun frac(i: Int): List<Bounds> = xs.frac(i).cartesianProduct(ys.frac(i)).map { (a, b) -> Bounds(a, b) }

	val isSquare: Boolean get() = xs.sizeS == ys.sizeS
	val ne
		get() = when (Clock.nX + Clock.eX) {
			1 -> xs.last() // x increases to the top right
			-1 -> xs.first() // x decreases to the top right
			else -> error("")
		} toP when (Clock.nY + Clock.eY) {
			1 -> ys.first() // y increases to the top right
			-1 -> ys.last() // y decreases to the top right
			else -> error("")
		}
	val nw
		get() = when (Clock.nX - Clock.eX) {
			1 -> xs.first() // x increases to the top left
			-1 -> xs.last() // x decreases to the top left
			else -> error("")
		} toP when (Clock.nY - Clock.eY) {
			1 -> ys.first() // y increases to the top left
			-1 -> ys.last() // y decreases to the top left
			else -> error("")
		}
	val se get() = higherCoords + lowerCoords - nw
	val sw get() = higherCoords + lowerCoords - ne
	val upperLeft get() = nw
	val upperRight get() = ne
	val lowerLeft get() = sw
	val lowerRight get() = se

	override operator fun iterator() = cartesianProductOf(xs, ys) { x, y -> x toP y }.iterator()

	val xSize get() = xs.sizeS
	val ySize get() = ys.sizeS
	val sizes get() = xSize toP ySize

	// Will overflow for MEGA
	val area get() = xSize * ySize

	val xSizeB get() = xs.sizeB
	val ySizeB get() = ys.sizeB
	val sizesB get() = xSizeB to ySizeB

	// Will overflow for MEGA
	val areaB get() = xSizeB * ySizeB

	@Deprecated("use area", ReplaceWith("area"))
	override val size get() = area.i
	override fun contains(element: Point): Boolean = element.x in xs && element.y in ys

	override fun isEmpty() = xs.isEmpty() || ys.isEmpty()

	override fun containsAll(elements: Collection<Point>): Boolean = elements.all { it in this }

	fun exactCenter() = xs.exactCenter() toP ys.exactCenter()

	fun leftEdge() = this.upperLeft toL this.lowerLeft
	fun rightEdge() = this.upperRight toL this.lowerRight
	fun topEdge() = this.upperLeft toL this.upperRight
	fun bottomEdge() = this.lowerLeft toL this.lowerRight

	fun northEdge() = topEdge()
	fun southEdge() = bottomEdge()
	fun eastEdge() = rightEdge()
	fun westEdge() = leftEdge()


	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE: Bounds = (Long.MIN_VALUE toP Long.MIN_VALUE) toB (Int.MAX_VALUE toPI Int.MAX_VALUE)
		val EMPTY: Bounds = Bounds(0 toPI 0, -1 toPI -1)
		val LARGE: Bounds = (Sint.NEG_MEGA toP Sint.NEG_MEGA) toB (Sint.POS_MEGA toP Sint.POS_MEGA)
	}
}


infix fun Point.toB(other: Point): Bounds =
	Bounds(min(this.x, other.x) toP min(this.y, other.y), max(this.x, other.x) toP max(this.y, other.y))

infix fun Point.toB(other: PointI): Bounds = this toB other.sint
infix fun PointI.toB(other: Point): Bounds = this.sint toB other
infix fun PointI.toB(other: PointI): Bounds = this.sint toB other.sint
infix fun Point.toB(other: PointL): Bounds = this toB other.sint
infix fun PointL.toB(other: Point): Bounds = this.sint toB other
infix fun PointL.toB(other: PointL): Bounds = this.sint toB other.sint


operator fun Point.rangeTo(other: Point) = this toB other
operator fun Point.rem(bounds: Bounds) = x % bounds.xs toP y % bounds.ys

fun Iterable<Point>.bounds(): Bounds = Bounds(minMaxRangeOf { it.x }, minMaxRangeOf { it.y })


@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("printString")
inline fun Bounds.print(transform: (Point) -> String) {
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

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
inline fun Bounds.print(transform: (Point) -> Char) {
	print { "" + transform(it) }
}


val BoundsI.sint: Bounds
	get() = Bounds(lower.sint, higher.sint)


val Bounds.upper: Point get() = higherCoords