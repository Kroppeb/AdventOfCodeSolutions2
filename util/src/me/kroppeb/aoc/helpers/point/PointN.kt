package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

public interface PointN<T : PointN<T>> {
	public fun getVonNeumannNeighbours(): List<T>
	public fun getMooreNeighbours(): List<T>
	public fun getVonNeumannNeighbourHood(): List<T>
	public fun getMooreNeighbourHood(): List<T>

	public operator fun unaryMinus(): T

	public operator fun minus(other: T): T
	public operator fun plus(other: T): T

	public operator fun times(other: T): T
	public operator fun div(other: T): T
	public operator fun rem(other: T): T

	public operator fun times(other: Sint): T
	public operator fun div(other: Sint): T
	public operator fun rem(other: Sint): T
	public operator fun times(other: Int): T = this.times(other.s)
	public operator fun div(other: Int): T = this.div(other.s)
	public operator fun rem(other: Int): T = this.rem(other.s)
	public operator fun times(other: Long): T = this.times(other.s)
	public operator fun div(other: Long): T = this.div(other.s)
	public operator fun rem(other: Long): T = this.rem(other.s)

	public fun abs(): T

	public fun gcd(): Sint

	@Suppress("UNCHECKED_CAST")
	public fun discreteAngle(): T {
		if (isZero()) {
			return this as T
		}
		return this / gcd()
	}

	public fun min(other: T): T
	public fun max(other: T): T

	public fun sqrDist(): Sint
	public fun dist(): Double
	public fun manDist(): Sint
	public fun chebyshevDist(): Sint

	public fun sqrDistTo(other: T): Sint = (this - other).sqrDist()
	public fun distTo(other: T): Double = (this - other).dist()
	public fun manDistTo(other: T): Sint = (this - other).manDist()
	public fun chebyshevDistTo(other: T): Sint = (this - other).chebyshevDist()

	/**
	 * includes self
	 */
	@Suppress("UNCHECKED_CAST")
	public fun sequence(step: T): Sequence<T> = generateSequence(this as T) { it + step }
	public fun sign(): T
	public fun isZero(): Boolean
	public fun isAxisAligned(): Boolean
	public fun dot(other: T): Sint
	public fun projectOnto(other: T): T {
		require(other.manDist() == 1.s)
		return this.dot(other) * other
	}
}



public operator fun <P:PointN<P>> Sint.times(other: P): P = other.times(this)
public operator fun <P:PointN<P>> Sint.div(other: P): P = other.div(this)
public operator fun <P:PointN<P>> Sint.rem(other: P): P = other.rem(this)
public operator fun <P:PointN<P>> Int.times(other: P): P = other.times(this)
public operator fun <P:PointN<P>> Int.div(other: P): P = other.div(this)
public operator fun <P:PointN<P>> Int.rem(other: P): P = other.rem(this)
public operator fun <P:PointN<P>> Long.times(other: P): P = other.times(this)
public operator fun <P:PointN<P>> Long.div(other: P): P = other.div(this)
public operator fun <P:PointN<P>> Long.rem(other: P): P = other.rem(this)