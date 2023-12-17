@file:Suppress("MemberVisibilityCanBePrivate", "DeprecatedCallableAddReplaceWith")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.gcd
import me.kroppeb.aoc.helpers.max
import me.kroppeb.aoc.helpers.min
import me.kroppeb.aoc.helpers.sint.*
import kotlin.math.sqrt

/**
 * righthanded rotations for now
 */
public data class Point3D(val x: Sint, val y: Sint, val z: Sint) : PointN<Point3D> {
	public val right: Point3D by lazy { (x toP y) + Clock.right toP z }
	public val left: Point3D by lazy { (x toP y) + Clock.left toP z }
	public val down: Point3D by lazy { (x toP y) + Clock.down toP z }
	public val up: Point3D by lazy { (x toP y) + Clock.up toP z }
	public val front: Point3D by lazy { x toP y toP z + 1 }
	public val back: Point3D by lazy { x toP y toP z - 1 }

	@Deprecated("Is always right handed")
	public fun rotateClockX(): Point3D = x toP -z toP y

	@Deprecated("Is always right handed")
	public fun rotateAntiClockX(): Point3D = x toP z toP -y

	@Deprecated("Is always right handed")
	public fun rotateClockY(): Point3D = z toP y toP -x

	@Deprecated("Is always right handed")
	public fun rotateAntiClockY(): Point3D = -z toP y toP x

	@Deprecated("Is always right handed")
	public fun rotateClockZ(): Point3D = -y toP x toP z

	@Deprecated("Is always right handed")
	public fun rotateAntiClockZ(): Point3D = y toP -x toP z

	public fun getHexNeighbours(): List<Point3D> = listOf(right, left, up, down, front, back)

	// Ordered by (left, right), (down, up), (back, front)
	public fun get2DDiagonalNeighbours(): List<Point3D> = listOf(
		left.down, left.back, left.front, left.up,
		down.back, down.front, up.back, up.front,
		right.down, right.back, right.front, right.up,
	)

	// Ordered by (left, right), (down, up), (back, front)
	public fun get3DDiagonalNeighbours(): List<Point3D> = listOf(
		left.down.back, left.down.front, left.up.back, left.up.front,
		right.down.back, right.down.front, right.up.back, right.up.front,
	)

	// Ordered by (left, right), (down, up), (back, front)
	public fun getIcosiHeptaNeighbours(): List<Point3D> = listOf(
		left.down.back, left.down, left.down.front,
		left.back, left, left.front,
		left.up.back, left.up, left.up.front,

		down.back, down, down.front,
		back, /*this,*/ front,
		up.back, up, up.front,

		right.down.back, right.down, right.down.front,
		right.back, right, right.front,
		right.up.back, right.up, right.up.front,
	)

	public fun getHexNeighbourHood(): List<Point3D> = listOf(this, right, left, up, down, front, back)

	// Ordered by (left, right), (down, up), (back, front)
	public fun get2DDiagonalNeighbourHood(): List<Point3D> = listOf(
		left.down, left.back, left.front, left.up,
		down.back, down.front, this, up.back, up.front,
		right.down, right.back, right.front, right.up,
	)

	// Ordered by (left, right), (down, up), (back, front)
	public fun get3DDiagonalNeighbourHood(): List<Point3D> = listOf(
		left.down.back, left.down.front, left.up.back, left.up.front,
		this,
		right.down.back, right.down.front, right.up.back, right.up.front,
	)

	// Ordered by (left, right), (down, up), (back, front)
	public fun getIcosiHeptaNeighbourHood(): List<Point3D> = listOf(
		left.down.back, left.down, left.down.front,
		left.back, left, left.front,
		left.up.back, left.up, left.up.front,

		down.back, down, down.front,
		back, this, front,
		up.back, up, up.front,

		right.down.back, right.down, right.down.front,
		right.back, right, right.front,
		right.up.back, right.up, right.up.front
	)


	override fun getMooreNeighbours(): List<Point3D> = getIcosiHeptaNeighbours()
	override fun getVonNeumannNeighbours(): List<Point3D> = getHexNeighbours()
	override fun getVonNeumannNeighbourHood(): List<Point3D> = getHexNeighbourHood()

	override fun getMooreNeighbourHood(): List<Point3D> = getIcosiHeptaNeighbourHood()

	override operator fun unaryMinus(): Point3D = -x toP -y toP -z

	override operator fun minus(other: Point3D): Point3D = x - other.x toP y - other.y toP z - other.z
	override operator fun plus(other: Point3D): Point3D = x + other.x toP y + other.y toP z + other.z

	override operator fun times(other: Point3D): Point3D = x * other.x toP y * other.y toP z * other.z
	override operator fun div(other: Point3D): Point3D = x / other.x toP y / other.y toP z / other.z
	override operator fun rem(other: Point3D): Point3D = x % other.x toP y % other.y toP z % other.z

	override operator fun times(other: Sint): Point3D = x * other toP y * other toP z * other
	override operator fun div(other: Sint): Point3D = x / other toP y / other toP z / other
	override operator fun rem(other: Sint): Point3D = x % other toP y % other toP z % other

	override fun abs(): Point3D = abs(x) toP abs(y) toP abs(z)

	override fun sqrDist(): Sint = x * x + y * y + z * z
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Sint = abs(x) + abs(y) + abs(z)
	override fun chebyshevDist(): Sint = maxOf(abs(x), abs(y), abs(z))

	override fun gcd(): Sint = gcd(abs(x), gcd(abs(y), abs(z)))

	override fun min(other: Point3D): Point3D =
		min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z)

	override fun max(other: Point3D): Point3D =
		max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z)

	override fun dot(other: Point3D): Sint = this.x * other.x + this.y * other.y + this.z * other.z

	override fun sign(): Point3D = x.sign() toP y.sign() toP z.sign()
	override fun isZero(): Boolean = x.isZero() && y.isZero() && z.isZero()
	override fun isAxisAligned(): Boolean = (x.isZero() || y.isZero()) && (x.isZero() || z.isZero()) && (y.isZero() || z.isZero())


	public companion object {
		public val ZERO: Point3D = 0 toP 0 toP 0
		public val DIRS: List<Point3D> get() = ZERO.getHexNeighbours()
	}
}