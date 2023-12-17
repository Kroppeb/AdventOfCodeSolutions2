@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.list.Het3
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.sint.*
import java.math.BigInteger

/**
 * lower and higher are inclusive
 */
public data class Bounds3D(
	val xs: SintRange,
	val ys: SintRange,
	val zs: SintRange,
) : BoundsN<Bounds3D, Point3D> {
	public constructor(lower: Point3D, higher: Point3D) : this(lower.x..higher.x, lower.y..higher.y, lower.z..higher.z)
	public constructor(xs: IntRange, ys: IntRange, zs: IntRange) : this(xs.s, ys.s, zs.s)

	public override val lowerCoords: Point3D get() = xs.first toP ys.first toP zs.first
	public override val higherCoords: Point3D get() = xs.last toP ys.last toP zs.last

	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	override fun intersect(other: Bounds3D): Bounds3D = Bounds3D(
		xs.intersect(other.xs),
		ys.intersect(other.ys),
		zs.intersect(other.zs),
	)

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	public override fun merge(other: Bounds3D): Bounds3D = Bounds3D(
		this.lowerCoords.min(other.lowerCoords),
		this.higherCoords.max(other.higherCoords),
	)

	override fun weight(): Sint = volume
	override fun weightB(): BigInteger = volumeB

	override fun fracture(other: Bounds3D): List<Bounds3D> = cartesianProductOf(
		xs.fracture(other.xs), ys.fracture(other.ys), zs.fracture(other.zs)
	) { x, y, z -> Bounds3D(x, y, z) }

	public fun expand(x: Sint, y: Sint, z: Sint): Bounds3D {
		val offset = x toP y toP z
		return Bounds3D(this.lowerCoords - offset, this.higherCoords + offset)
	}

	public fun expand(i: Sint): Bounds3D = expand(i, i, i)
	public fun expand(x: Int, y: Int, z: Int): Bounds3D = expand(x.s, y.s, z.s)
	public fun expand(i: Int): Bounds3D = expand(i.s)
	public fun retract(x: Sint, y: Sint, z: Sint): Bounds3D = expand(-x, -y, -z)
	public fun retract(i: Sint): Bounds3D = expand(-i)
	public fun retract(x: Int, y: Int, z: Int): Bounds3D = expand(-x, -y, -z)
	public fun retract(i: Int): Bounds3D = expand(-i)

	public fun frac(i: Int): List<Bounds3D> =
		cartesianProductOf(xs.frac(i), ys.frac(i), zs.frac(i)) { a, b, c -> Bounds3D(a, b, c) }


	val isCube: Boolean get() = xSize == ySize && ySize == zSize

	override operator fun iterator(): Iterator<Point3D> =
		xs.flatMap { x -> ys.flatMap { y -> zs.map { z -> x toP y toP z } } }.iterator()


	public val xSize: Sint get() = xs.sizeS
	public val ySize: Sint get() = ys.sizeS
	public val zSize: Sint get() = zs.sizeS
	override val sizes: Point3D get() = xSize toP ySize toP zSize
	val volume: Sint get() = xSize * ySize * zSize

	val xSizeB: BigInteger get() = xs.sizeB
	val ySizeB: BigInteger get() = ys.sizeB
	val zSizeB: BigInteger get() = zs.sizeB
	val sizesB: Het3<BigInteger, BigInteger, BigInteger> get() = xSizeB toH ySizeB toH zSizeB
	val volumeB: BigInteger get() = xSizeB * ySizeB * zSizeB

	@Deprecated("use volume", ReplaceWith("volume"))
	override val size: Int get() = volume.i

	override fun contains(element: Point3D): Boolean = element.x in xs && element.y in ys && element.z in zs

	override fun isEmpty(): Boolean = xs.isEmpty() || ys.isEmpty() || zs.isEmpty()

	override fun containsAll(elements: Collection<Point3D>): Boolean = elements.all { it in this }

	public fun exactCenter() = xs.exactCenter() toP ys.exactCenter() toP zs.exactCenter()

	public fun edges(): List<Line3D> = listOf(
		lowerCoords toL (lowerCoords.x toP lowerCoords.y toP higherCoords.z),
		lowerCoords toL (lowerCoords.x toP higherCoords.y toP lowerCoords.z),
		lowerCoords toL (higherCoords.x toP lowerCoords.y toP lowerCoords.z),

		(higherCoords.x toP lowerCoords.y toP lowerCoords.z) toL (higherCoords.x toP higherCoords.y toP lowerCoords.z),
		(higherCoords.x toP lowerCoords.y toP lowerCoords.z) toL (higherCoords.x toP lowerCoords.y toP higherCoords.z),

		(lowerCoords.x toP higherCoords.y toP lowerCoords.z) toL (higherCoords.x toP higherCoords.y toP lowerCoords.z),
		(lowerCoords.x toP higherCoords.y toP lowerCoords.z) toL (lowerCoords.x toP higherCoords.y toP higherCoords.z),

		(lowerCoords.x toP lowerCoords.y toP higherCoords.z) toL (lowerCoords.x toP higherCoords.y toP higherCoords.z),
		(lowerCoords.x toP lowerCoords.y toP higherCoords.z) toL (higherCoords.x toP lowerCoords.y toP higherCoords.z),

		(higherCoords.x toP higherCoords.y toP lowerCoords.z) toL higherCoords,
		(higherCoords.x toP lowerCoords.y toP higherCoords.z) toL higherCoords,
		(lowerCoords.x toP higherCoords.y toP higherCoords.z) toL higherCoords,
	)


	public companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		public val INFINITE: Bounds3D =
			(Sint.MIN_VALUE toP Sint.MIN_VALUE toP Sint.MIN_VALUE) toB (Sint.MAX_VALUE toP Sint.MAX_VALUE toP Sint.MAX_VALUE)
		public val EMPTY: Bounds3D = Bounds3D(0 toP 0 toP 0, -1 toP -1 toP -1)
		public val LARGE: Bounds3D =
			(Sint.NEG_MEGA toP Sint.NEG_MEGA toP Sint.NEG_MEGA) toB (Sint.POS_MEGA toP Sint.POS_MEGA toP Sint.POS_MEGA)
	}
}

//class MutableBounds3D : IBounds3D {
//	override var lower: Point3D
//	override var higher: Point3D
//
//	constructor(start: Point3D) : super() {
//		this.lower = start
//		this.higher = start
//	}
//
//	constructor() : super() {
//		this.lower = Sint.MAX_VALUE toP Sint.MAX_VALUE toP Sint.MAX_VALUE
//		this.higher = Sint.MIN_VALUE toP Sint.MIN_VALUE toP Sint.MIN_VALUE
//	}
//
//	fun add(point: Point3D) {
//		lower = lower.min(point)
//		higher = higher.max(point)
//	}
//
//	fun toBounds(): Bounds3D {
//		if (lower.x > higher.x || lower.y > higher.y) return Bounds3D.EMPTY
//		return Bounds3D(lower, higher)
//	}
//
//	override val xSize: Sint
//		get() = super.xSize.coerceAtLeast(0)
//	override val ySize: Sint
//		get() = super.ySize.coerceAtLeast(0)
//
//	override val zSize: Sint
//		get() = super.zSize.coerceAtLeast(0)
//}

public infix fun Point3D.toB(other: Point3D): Bounds3D = Bounds3D(
	min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z),
	max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z),
)

public operator fun Point3D.rangeTo(other: Point3D): Bounds3D = this toB other
public operator fun Point3D.rem(bounds: Bounds3D): Point3D = x % bounds.xs toP y % bounds.ys toP z % bounds.zs

public fun Iterable<Point3D>.bounds(): Bounds3D =
	Bounds3D(minMaxRangeOf { it.x }, minMaxRangeOf { it.y }, minMaxRangeOf { it.z })

