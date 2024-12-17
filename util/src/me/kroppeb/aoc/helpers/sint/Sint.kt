package me.kroppeb.aoc.helpers.sint

import kotlin.jvm.JvmInline
import kotlin.math.sign

private const val SAFE_INT_MAX = (1L shl 52) - 1
private const val SAFE_INT_MIN = -SAFE_INT_MAX
private var has_warned_negative_division_round = false
private var has_warned_negative_rem = false

@JvmInline
public value class Sint(private val inner: Long) : Comparable<Sint> {
	public val l: Long get() = inner
	public val i: Int get() = Math.toIntExact(inner)
	public val d: Double
		get() {
			if (l in SAFE_INT_MIN..SAFE_INT_MAX) {
				return l.toDouble()
			}
			throw ArithmeticException("Sint overflow: $this")
		}

	public fun canBeExactInt() = inner in Int.MIN_VALUE..Int.MAX_VALUE

	public operator fun plus(other: Sint): Sint = Sint(Math.addExact(inner, other.inner))
	public operator fun minus(other: Sint): Sint = Sint(Math.subtractExact(inner, other.inner))
	public operator fun times(other: Sint): Sint = Sint(Math.multiplyExact(inner, other.inner))

	// TODO: consider whether I want floorDiv or div
	public operator fun div(other: Sint): Sint {
		if (!has_warned_negative_division_round && inner < 0 && inner % other.inner != 0L) {
			// warn
			has_warned_negative_division_round = true
			System.err.println("Warning: negative division rounding")
		}
		return Sint(inner / other.inner)
	}

	public operator fun rem(other: Sint): Sint {
		if (!has_warned_negative_rem && inner < 0 && inner % other.inner != 0L) {
			// warn
			has_warned_negative_rem = true
			System.err.println("Warning: negative remainder")
		}
		return Sint(inner % other.inner)
	}

	public operator fun unaryMinus(): Sint = Sint(Math.negateExact(inner))

	public operator fun inc(): Sint = Sint(Math.incrementExact(inner))
	public operator fun dec(): Sint = Sint(Math.decrementExact(inner))

	override operator fun compareTo(other: Sint): Int = inner.compareTo(other.inner)

	override fun toString(): String = inner.toString()

	public fun sign(): Sint = Sint(inner.sign.toLong())

	public operator fun rangeTo(other: Sint): SintRange = SintRange(this, other)
	public operator fun rangeUntil(other: Sint): SintRange = SintRange(this, other - 1.s)
	public infix fun until(other: Sint): SintRange = SintRange(this, other - 1.s)
	public infix fun downTo(other: Sint): SintProgression = SintProgression(this, other, -1.s)

	public infix fun shl(other: Sint): Sint = Sint(inner shl other.inner.toInt())
	public infix fun shr(other: Sint): Sint = Sint(inner shr other.inner.toInt())
	public infix fun ushr(other: Sint): Sint = Sint(inner ushr other.inner.toInt())
	public infix fun and(other: Sint): Sint = Sint(inner and other.inner)
	public infix fun or(other: Sint): Sint = Sint(inner or other.inner)
	public infix fun xor(other: Sint): Sint = Sint(inner xor other.inner)
	public fun inv(): Sint = Sint(inner.inv())

	public companion object {
		public val ZERO: Sint = Sint(0)
		public val ONE: Sint = Sint(1)

		public val MAX_VALUE: Sint = Sint(Long.MAX_VALUE)
		public val MIN_VALUE: Sint = Sint(Long.MIN_VALUE)

		// can be used as "infinities" but with a lower chance of overflowing
		// max and min are about 9.2 times larger than mega
		public val NEG_MEGA: Sint = Sint(-1_000_000_000_000_000_000L)
		public val POS_MEGA: Sint = Sint(1_000_000_000_000_000_000L)
	}
}