@file:OptIn(ExperimentalTypeInference::class)

package me.kroppeb.aoc.helpers.sint

import me.kroppeb.aoc.helpers.*
import java.math.BigInteger
import kotlin.experimental.ExperimentalTypeInference


public val Int.s: Sint get() = Sint(this.toLong())
public val Long.s: Sint get() = Sint(this)
public val String.s: Sint get() = this.toSint()
public fun String.toSint(): Sint = this.toLong().s


public val Iterable<Int>.s: List<Sint>
	@JvmName("IterableIntToSint")
	get() = this.map { it.s }
public val Iterable<Long>.s: List<Sint>
	@JvmName("IterableLongToSint")
	get() = this.map { it.s }

public val Iterable<Iterable<Int>>.s: List<List<Sint>>
	@JvmName("IterableIterableIntToSint")
	get() = this.map { it.s }
public val Iterable<Iterable<Long>>.s: List<List<Sint>>
	@JvmName("IterableIterableLongToSint")
	get() = this.map { it.s }

@Deprecated("UwU")
public val Sint.s: Sint get() = this


public fun Sint.toBigInteger(): BigInteger = this.l.toBigInteger()


//region overloads for Sint opp Int or Long and vice versa
public operator fun Sint.plus(other: Int): Sint = this + other.s
public operator fun Sint.plus(other: Long): Sint = this + other.s
public operator fun Int.plus(other: Sint): Sint = this.s + other
public operator fun Long.plus(other: Sint): Sint = this.s + other

public operator fun Sint.minus(other: Int): Sint = this - other.s
public operator fun Sint.minus(other: Long): Sint = this - other.s
public operator fun Int.minus(other: Sint): Sint = this.s - other
public operator fun Long.minus(other: Sint): Sint = this.s - other

public operator fun Sint.times(other: Int): Sint = this * other.s
public operator fun Sint.times(other: Long): Sint = this * other.s
public operator fun Int.times(other: Sint): Sint = this.s * other
public operator fun Long.times(other: Sint): Sint = this.s * other

public operator fun Sint.div(other: Int): Sint = this / other.s
public operator fun Sint.div(other: Long): Sint = this / other.s
public operator fun Int.div(other: Sint): Sint = this.s / other
public operator fun Long.div(other: Sint): Sint = this.s / other

public operator fun Sint.rem(other: Int): Sint = this % other.s
public operator fun Sint.rem(other: Long): Sint = this % other.s
public operator fun Int.rem(other: Sint): Sint = this.s % other
public operator fun Long.rem(other: Sint): Sint = this.s % other

public operator fun Sint.compareTo(other: Int): Int = this.compareTo(other.s)
public operator fun Sint.compareTo(other: Long): Int = this.compareTo(other.s)
public operator fun Int.compareTo(other: Sint): Int = this.s.compareTo(other)
public operator fun Long.compareTo(other: Sint): Int = this.s.compareTo(other)

public operator fun Sint.rangeTo(other: Int): SintRange = this..other.s
public operator fun Sint.rangeTo(other: Long): SintRange = this..other.s
public operator fun Int.rangeTo(other: Sint): SintRange = this.s..other
public operator fun Long.rangeTo(other: Sint): SintRange = this.s..other

public operator fun Sint.rangeUntil(other: Int): SintRange = this..<other.s
public operator fun Sint.rangeUntil(other: Long): SintRange = this..<other.s
public operator fun Int.rangeUntil(other: Sint): SintRange = this.s..<other
public operator fun Long.rangeUntil(other: Sint): SintRange = this.s..<other

public infix fun Sint.until(other: Int): SintRange = this until other.s
public infix fun Sint.until(other: Long): SintRange = this until other.s
public infix fun Int.until(other: Sint): SintRange = this.s until other
public infix fun Long.until(other: Sint): SintRange = this.s until other

public infix fun Sint.downTo(other: Int): SintProgression = this downTo other.s
public infix fun Sint.downTo(other: Long): SintProgression = this downTo other.s
public infix fun Int.downTo(other: Sint): SintProgression = this.s downTo other
public infix fun Long.downTo(other: Sint): SintProgression = this.s downTo other

public fun Sint.coerceIn(min: Int, max: Int): Sint = this.coerceIn(min.s, max.s)
public fun Sint.coerceIn(min: Sint, max: Int): Sint = this.coerceIn(min, max.s)
public fun Sint.coerceIn(min: Int, max: Sint): Sint = this.coerceIn(min.s, max)
public fun Sint.coerceIn(min: Long, max: Long): Sint = this.coerceIn(min.s, max.s)
public fun Sint.coerceIn(min: Sint, max: Long): Sint = this.coerceIn(min, max.s)
public fun Sint.coerceIn(min: Long, max: Sint): Sint = this.coerceIn(min.s, max)
public fun Int.coerceIn(min: Sint, max: Sint): Sint = this.s.coerceIn(min, max)
public fun Int.coerceIn(min: Int, max: Sint): Sint = this.s.coerceIn(min.s, max)
public fun Int.coerceIn(min: Sint, max: Int): Sint = this.s.coerceIn(min, max.s)
public fun Long.coerceIn(min: Sint, max: Sint): Sint = this.s.coerceIn(min, max)
public fun Long.coerceIn(min: Long, max: Sint): Sint = this.s.coerceIn(min.s, max)
public fun Long.coerceIn(min: Sint, max: Long): Sint = this.s.coerceIn(min, max.s)

public fun Sint.coerceAtLeast(min: Int): Sint = this.coerceAtLeast(min.s)
public fun Sint.coerceAtLeast(min: Long): Sint = this.coerceAtLeast(min.s)
public fun Int.coerceAtLeast(min: Sint): Sint = this.s.coerceAtLeast(min)
public fun Long.coerceAtLeast(min: Sint): Sint = this.s.coerceAtLeast(min)

public fun Sint.coerceAtMost(max: Int): Sint = this.coerceAtMost(max.s)
public fun Sint.coerceAtMost(max: Long): Sint = this.coerceAtMost(max.s)
public fun Int.coerceAtMost(max: Sint): Sint = this.s.coerceAtMost(max)
public fun Long.coerceAtMost(max: Sint): Sint = this.s.coerceAtMost(max)

@JvmName("sintCollectionsContainsInt")
public operator fun Collection<Sint>.contains(other: Int): Boolean = this.contains(other.s)

@JvmName("sintCollectionsContainsLong")
public operator fun Collection<Sint>.contains(other: Long): Boolean = this.contains(other.s)

private var _contains_warning = false

@JvmName("intCollectionsContainsSint")
public operator fun Collection<Int>.contains(other: Sint): Boolean {
	if (other.canBeExactInt()) return this.contains(other.i)
	if (!_contains_warning) {
		_contains_warning = true
		System.err.println("Warning: You are searching for a big Sint in a collection of Ints")
	}
	return false
}

@JvmName("longCollectionsContainsSint")
public operator fun Collection<Long>.contains(other: Sint): Boolean = this.contains(other.l)

public infix fun Sint.shl(other: Int): Sint = this shl other.s
public infix fun Sint.shl(other: Long): Sint = this shl other.s
public infix fun Int.shl(other: Sint): Sint = this.s shl other
public infix fun Long.shl(other: Sint): Sint = this.s shl other

public infix fun Sint.shr(other: Int): Sint = this shr other.s
public infix fun Sint.shr(other: Long): Sint = this shr other.s
public infix fun Int.shr(other: Sint): Sint = this.s shr other
public infix fun Long.shr(other: Sint): Sint = this.s shr other

public infix fun Sint.ushr(other: Int): Sint = this ushr other.s
public infix fun Sint.ushr(other: Long): Sint = this ushr other.s
public infix fun Int.ushr(other: Sint): Sint = this.s ushr other
public infix fun Long.ushr(other: Sint): Sint = this.s ushr other

public infix fun Sint.and(other: Int): Sint = this and other.s
public infix fun Sint.and(other: Long): Sint = this and other.s
public infix fun Int.and(other: Sint): Sint = this.s and other
public infix fun Long.and(other: Sint): Sint = this.s and other

// endregion


// list stuff
public operator fun <T> List<T>.get(index: Sint): T = this[index.i]
public operator fun <T> List<T>.get(index: SintRange): List<T> = this.subList(index.start.i, index.endInclusive.i + 1)
public operator fun <T> List<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
public operator fun <T> MutableList<T>.set(index: Sint, item: T): T = this.set(index.i, item)

@JvmName("getMutable")
public operator fun <T> MutableList<T>.get(index: SintRange): List<T> = this.subList(index.start.i, index.endExclusive.i)

@JvmName("getMutable")
public operator fun <T> MutableList<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
public fun <T> MutableList<T>.add(index: Sint, item: T): Unit = this.add(index.i, item)
public fun List<*>.idx(): SintRange = 0.s..<this.size.s
public val List<*>.lastIdx: Sint get() = this.lastIndex.s

// array stuff
public operator fun <T> Array<T>.get(index: Sint): T = this[index.i]
public operator fun <T> Array<T>.get(index: SintRange): List<T> = this.slice(index.start.i..index.endInclusive.i)
public operator fun <T> Array<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
public operator fun <T> Array<T>.set(index: Sint, item: T): Unit = this.set(index.i, item)
public fun Array<*>.idx(): SintRange = 0.s until this.size.s


public operator fun String.get(index: Sint): Char = this[index.i]
public fun String.getOrNull(index: Sint): Char? = this.getOrNull(index.i)


public inline fun repeat(times: Sint, action: (Sint) -> Unit) {
//	contract { callsInPlace(action) }

	for (index in 0 until times) {
		action(index)
	}
}


public fun mod(a: Sint, b: Sint): Sint = Math.floorMod(a.l, b.l).s
@JvmName("modInfix")
public infix fun Sint.mod(base: Sint): Sint = mod(this, base)
public infix fun Sint.mod(base: Int): Sint = this mod base.s
public infix fun Sint.mod(base: Long): Sint = this mod base.s
public infix fun Int.mod(base: Sint): Sint = this.s mod base
public infix fun Long.mod(base: Sint): Sint = this.s mod base


public fun Iterable<Sint>.sum(): Sint = fold(0.s) { a, b -> a + b }

//@OverloadResolutionByLambdaReturnType
public fun <T> Iterable<T>.sumOf(selector: (T) -> Sint): Sint = map(selector).sum()
public fun Iterable<Sint>.cumSum(): List<Sint> = scan { a, b -> a + b }

public fun <T> Iterable<T>.cumSumOf(selector: (T) -> Sint): List<Sint> = map(selector).cumSum()

public fun Iterable<Sint>.cumSum(initial: Sint): List<Sint> = scan(initial) { a, b -> a + b }

public fun <T> Iterable<T>.cumSumOf(initial: Sint, selector: (T) -> Sint): List<Sint> = map(selector).cumSum(initial)

public fun abs(a: Sint) = if (a.l < 0) -a else a

@JvmName("absExt")
public fun Sint.abs() = abs(this)


public fun Sint.toDouble(): Double = this.l.toDouble()

public val IntRange.s: SintRange get() = SintRange(this.start.s, this.endInclusive.s)
public val LongRange.s: SintRange get() = SintRange(this.start.s, this.endInclusive.s)
public val Iterable<IntRange>.s: List<SintRange>
	@JvmName("IterableIntRangeToSintRange")
	get() = this.map { it.s }
public val Iterable<LongRange>.s: List<SintRange>
	@JvmName("IterableLongRangeToSintRange")
	get() = this.map { it.s }

public val Iterable<Iterable<IntRange>>.s: List<List<SintRange>>
	@JvmName("IterableIterableIntRangeToSintRange")
	get() = this.map { it.s }
public val Iterable<Iterable<LongRange>>.s: List<List<SintRange>>
	@JvmName("IterableIterableLongRangeToSintRange")
	get() = this.map { it.s }

public operator fun Sint.rem(range: SintRange): Sint = range.first + (this - range.first mod range.last - range.first + 1)
public operator fun Sint.rem(range: IntRange): Sint = this % range.s
public operator fun Sint.rem(range: LongRange): Sint = this % range.s
public operator fun Int.rem(range: SintRange): Sint = this.s % range
public operator fun Long.rem(range: SintRange): Sint = this.s % range
public infix fun Sint.mod(base: SintRange): Sint = this % base
public infix fun Sint.mod(base: IntRange): Sint = this % base
public infix fun Sint.mod(base: LongRange): Sint = this % base
public infix fun Int.mod(base: SintRange): Sint = this.s % base
public infix fun Long.mod(base: SintRange): Sint = this.s % base


public fun Sint.pow(x: Sint): Sint = when {
	x.l < 0 -> throw ArithmeticException("Negative exponent")
	x.l == 0L -> 1.s // pow over integer can be safely considered 1
	x.l == 1L -> this
	this.l == 0L -> 0.s
	x.l % 2 == 0L -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

public fun Sint.pow(x: Int): Sint = this.pow(x.s)
public fun Sint.pow(x: Long): Sint = this.pow(x.s)
public fun Int.pow(x: Sint): Sint = this.s.pow(x)
public fun Long.pow(x: Sint): Sint = this.s.pow(x)

public fun Sint.powMod(x: Sint, y: Sint): Sint = when {
	y.l <= 1 -> throw ArithmeticException("Bad modulus")
	x.l < 0 -> if (this.l == 0L) throw ArithmeticException("Division by zero") else this.modInv(y).powMod(-x, y)
	x.l == 0L -> 1.s // pow over integer can be safely considered 1
	this.l == 0L -> 0.s
	x.l == 1L -> this mod y
	x.l % 2 == 0L -> (this * this mod y).powMod(x / 2, y)
	else -> (this * this mod y).powMod(x / 2, y) * this mod y
}

public fun Sint.floorDiv(x: Sint): Sint = l.floorDiv(x.l).s
public fun Sint.floorDiv(x: Int): Sint = this.floorDiv(x.s)
public fun Sint.floorDiv(x: Long): Sint = this.floorDiv(x.s)
public fun Int.floorDiv(x: Sint): Sint = this.s.floorDiv(x)
public fun Long.floorDiv(x: Sint): Sint = this.s.floorDiv(x)

public fun Sint.modInv(base: Sint): Sint {
	val (g, x, _) = egcd(this, base)
	if (g != 1.s) throw IllegalArgumentException("No inverse")
	return x mod base
}

public fun Sint.modInv(base: Sint, target: Sint): Sint {
	val (g, x, _) = egcd(this, base)
	if (target divBy g) return x * (target / g) mod base
	throw java.lang.IllegalArgumentException("No inverse")
}


public fun Sint.isZero(): Boolean = this.l == 0L
