package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.Sint
import java.math.BigInteger

public interface BoundsN<B : BoundsN<B, P>, P : PointN<P>> : Collection<P> {
	public val lowerCoords: P
	public val higherCoords: P
	public fun intersect(other: B): B
	public val sizes: P
	public fun weight(): Sint
	public fun weightB(): BigInteger

	public fun doesIntersect(other: B): Boolean {
		return !intersect(other).isEmpty()
	}

	public fun merge(other: B): B

	public operator fun contains(other: B): Boolean {
		return other.isEmpty() || intersect(other) == other
	}

	/**
	 * Fractures the bounds into a set of bounds that are not overlapping and each bound is either fully contained
	 * in these bounds or fully outside of these bounds. The fractures together cover the same area as these bounds.
	 */
	public fun fracture(other: B): Collection<B>

	public override fun equals(other: Any?): Boolean
	public override fun hashCode(): Int
}