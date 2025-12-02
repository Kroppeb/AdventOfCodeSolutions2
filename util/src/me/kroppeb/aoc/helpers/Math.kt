package me.kroppeb.aoc.helpers


import me.kroppeb.aoc.helpers.sint.*
import java.lang.IllegalArgumentException
import kotlin.math.ceil
import kotlin.math.sqrt


public fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
public fun gcd(a: Long, b: Long): Long = if (a == 0L) b else gcd(b % a, a)
public fun gcd(a: Sint, b: Sint): Sint = if (a.isZero()) b else gcd(b % a, a)

public fun egcd(a: Int, b: Int): Triple<Int, Int, Int> {
	if (a == 0) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

public fun egcd(a: Long, b: Long): Triple<Long, Long, Long> {
	if (a == 0L) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

public fun egcd(a: Sint, b: Sint): Triple<Sint, Sint, Sint> {
	if (a.isZero()) return Triple(b, 0.s, 1.s)
	val (g, x, y) = egcd(b mod a, a)
	return Triple(g, y - (b.floorDiv(a)) * x, x)
}

@JvmName("intGcd")
public fun Iterable<Int>.gcd(): Int = reduce(::gcd)

@JvmName("longGcd")
public fun Iterable<Long>.gcd(): Long = reduce(::gcd)

@JvmName("sintGcd")
public fun Iterable<Sint>.gcd(): Sint = reduce(::gcd)

public fun gcd(vararg e: Int): Int = e.toList().gcd()
public fun gcd(vararg e: Long): Long = e.toList().gcd()

@Suppress("FINAL_UPPER_BOUND")
public fun <TSint : Sint> gcd(vararg e: TSint): Sint = e.toList().gcd()

public fun lcm(a: Int, b: Int): Int = lcm(a.s, b.s).i
public fun lcm(a: Long, b: Long): Long = lcm(a.s, b.s).l
public fun lcm(a: Sint, b: Sint): Sint = a / gcd(a, b) * b


@JvmName("intLcm")
public fun Iterable<Int>.lcm(): Int = reduce(::lcm)

@JvmName("longLcm")
public fun Iterable<Long>.lcm(): Long = reduce(::lcm)

@JvmName("sintLcm")
public fun Iterable<Sint>.lcm(): Sint = reduce(::lcm)

public fun lcm(vararg e: Int): Int = e.toList().lcm()
public fun lcm(vararg e: Long): Long = e.toList().lcm()

@Suppress("FINAL_UPPER_BOUND")
public fun <TSint : Sint> lcm(vararg e: TSint): Sint = e.toList().lcm()


public fun IntArray.min(): Int = minOrNull()!!
public fun IntArray.max(): Int = maxOrNull()!!

public fun LongArray.min(): Long = minOrNull()!!
public fun LongArray.max(): Long = maxOrNull()!!

public fun DoubleArray.min(): Double = minOrNull()!!
public fun DoubleArray.max(): Double = maxOrNull()!!


public inline fun <C, T : Comparable<T>> Iterable<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <C, T : Comparable<T>> Iterable<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

public fun <T : Comparable<T>> Iterable<T>.allMin(): List<T> = allMinBy { it }
public fun <T : Comparable<T>> Iterable<T>.allMax(): List<T> = allMaxBy { it }
public inline fun <C, T : Comparable<T>> Iterable<C>.allMinOf(b: (C) -> T): List<T> = map(b).allMin()
public inline fun <C, T : Comparable<T>> Iterable<C>.allMaxOf(b: (C) -> T): List<T> = map(b).allMax()

public inline fun <C, T : Comparable<T>> Array<C>.minBy(b: (C) -> T): C = minByOrNull(b)!!
public inline fun <C, T : Comparable<T>> Array<C>.maxBy(b: (C) -> T): C = maxByOrNull(b)!!

public inline fun <C, T : Comparable<T>> Array<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <C, T : Comparable<T>> Array<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

public inline fun <C : Comparable<C>> IntArray.minBy(b: (Int) -> C): Int = minByOrNull(b)!!
public inline fun <C : Comparable<C>> IntArray.maxBy(b: (Int) -> C): Int = maxByOrNull(b)!!

public inline fun <C : Comparable<C>> IntArray.allMinBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <C : Comparable<C>> IntArray.allMaxBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

public inline fun <C : Comparable<C>> LongArray.minBy(b: (Long) -> C): Long = minByOrNull(b)!!
public inline fun <C : Comparable<C>> LongArray.maxBy(b: (Long) -> C): Long = maxByOrNull(b)!!

public inline fun <C : Comparable<C>> LongArray.allMinBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <C : Comparable<C>> LongArray.allMaxBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

public inline fun <C : Comparable<C>> DoubleArray.minBy(b: (Double) -> C): Double = minByOrNull(b)!!
public inline fun <C : Comparable<C>> DoubleArray.maxBy(b: (Double) -> C): Double = maxByOrNull(b)!!

public inline fun <C : Comparable<C>> DoubleArray.allMinBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <C : Comparable<C>> DoubleArray.allMaxBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

public inline fun <K, V, T : Comparable<T>> Map<K, V>.minBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = minByOrNull(b)!!
public inline fun <K, V, T : Comparable<T>> Map<K, V>.maxBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = maxByOrNull(b)!!

public inline fun <K, V, T : Comparable<T>> Map<K, V>.allMinBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

public inline fun <K, V, T : Comparable<T>> Map<K, V>.allMaxBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

@JvmName("minMaxVararg")
public fun <T : Comparable<T>> minMax(vararg e: T): Pair<T, T> = e.min() to e.max()
public fun <T : Comparable<T>> Iterable<T>.minMax(): Pair<T, T> = min() to max()
public fun <T : Comparable<T>> Array<T>.minMax(): Pair<T, T> = min() to max()
public fun IntArray.minMax(): Pair<Int, Int> = min() to max()
public fun LongArray.minMax(): Pair<Long, Long> = min() to max()
public fun DoubleArray.minMax(): Pair<Double, Double> = min() to max()

@JvmName("minMaxIRIntsVararg")
public fun minMaxRange(vararg e: Int): IntRange = e.min()..e.max()

@JvmName("minMaxIRInts")
public fun Iterable<Int>.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRInts")
public fun Array<Int>.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRInts")
public fun IntArray.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRLongsVararg")
public fun minMaxRange(vararg e: Long): LongRange = e.min()..e.max()

@JvmName("minMaxIRLongs")
public fun Iterable<Long>.minMaxRange(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
public fun Array<Long>.minMaxRange(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
public fun LongArray.minMaxRange(): LongRange = min()..max()


@Suppress("FINAL_UPPER_BOUND")
@JvmName("minMaxIRSintsVararg")
public fun <TSint : Sint> minMaxRange(vararg e: TSint): SintRange = e.min()..e.max()

@JvmName("minMaxIRSints")
public fun Iterable<Sint>.minMaxRange(): SintRange = min()..max()

@JvmName("minMaxIRSints")
public fun Array<Sint>.minMaxRange(): SintRange = min()..max()

public inline fun <T> Iterable<T>.minMaxRangeOf(mapping: (T) -> Sint): SintRange = minOf(mapping)..maxOf(mapping)

public fun Int.pow(x: Int): Int = this.s.pow(x).i

public fun Long.pow(x: Int): Long = this.s.pow(x.s).l
public fun Long.pow(x: Long): Long = this.s.pow(x.s).l

public fun Long.powMod(x: Int, y: Long): Long = this.s.powMod(x.s, y.s).l

public fun Int.primeFactors(): List<Pair<Int, Int>> {
	val factors = mutableListOf<Pair<Int, Int>>()
	var n = this
	for (i in listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97) + (101..ceil(1 + sqrt(n.toDouble())).toInt() step 2)) {
		var count = 0
		while (n % i == 0) {
			count++
			n /= i
		}
		if (count > 0) factors.add(i to count)
	}
	return factors
}

public fun Int.allDivisors(): List<Int> {
	val factors = primeFactors()
	var divisors = listOf(1)

	for ((factor, count) in factors) {
		divisors = divisors.flatMap { d -> (0..count).map { d * factor.pow(it) } }
	}

	return divisors
}

public infix fun Int.mod(base: Int): Int = (this.s mod base.s).i
public infix fun Long.mod(base: Int): Int = (this.s mod base.s).i
public infix fun Long.mod(base: Long): Long = (this.s mod base.s).l


public fun Int.modInv(base: Int): Int {
	val (g, x, _) = egcd(this, base)
	if (g != 1) throw IllegalArgumentException("No inverse")
	return x mod base
}

public fun Int.modInv(base: Int, target: Int): Int {
	val (g, x, _) = egcd(this, base)
	if (target divBy g) return x * (target / g) mod base
	throw IllegalArgumentException("No inverse")
}

public fun Long.modInv(base: Long): Long {
	val (g, x, _) = egcd(this, base)
	if (g != 1L) throw IllegalArgumentException("No inverse")
	return x mod base
}

public fun Long.modInv(base: Long, target: Long): Long {
	val (g, x, _) = egcd(this, base)
	if (target divBy g) return x * (target / g) mod base
	throw IllegalArgumentException("No inverse")
}

public infix fun Int.divBy(other: Int): Boolean = this mod other == 0
public infix fun Int.divBy(other: Long): Boolean = this.toLong() divBy other
public infix fun Int.divBy(other: Sint): Boolean = this.s divBy other
public infix fun Long.divBy(other: Int): Boolean = this divBy other.toLong()
public infix fun Long.divBy(other: Long): Boolean = this mod other == 0L
public infix fun Long.divBy(other: Sint): Boolean = this.s divBy other
public infix fun Sint.divBy(other: Int): Boolean = this divBy other.s
public infix fun Sint.divBy(other: Long): Boolean = this divBy other.s
public infix fun Sint.divBy(other: Sint): Boolean = this.l divBy other.l


private var crtWarning = false

@JvmName("crtIntInt")
public fun crt(values: List<Pair<Int, Int>>): Sint = crt(values.map { it.first.s to it.second.s })

@JvmName("crtSintInt")
public fun crt(values: List<Pair<Sint, Int>>): Sint = crt(values.map { it.first to it.second.s })

@JvmName("crtIntSint")
public fun crt(values: List<Pair<Int, Sint>>): Sint = crt(values.map { it.first.s to it.second })
public fun crt(values: List<Pair<Sint, Sint>>): Sint = crtE(values).first

public fun crtE(values: List<Pair<Sint, Sint>>): Pair<Sint, Sint> {
	if (!crtWarning) {
		for ((a, b) in values) {
			if (a !in 0 until b) {
				println("CRT Warning: $a !in 0 until $b")
				crtWarning = true
				break
			}
		}
	}

//	require(values.pairWise().all { gcd(it.first.second, it.second.second) == 1.s }) { "Not pairwise coprime" }
	val simplified_values = simplifyCrt(values) ?: throw IllegalArgumentException("Not solvable coprime")

	val md = simplified_values.map { it.second }.product()
	val mds = simplified_values.map { md / it.second }
	val ys = mds.zip(simplified_values) { mul, value -> value.first * mul.modInv(value.second) * mul }
	val sum = ys.sum()
	return sum mod md to md
}

public fun simplifyCrt(values: List<Pair<Sint, Sint>>): List<Pair<Sint, Sint>>? {
	// split into coprime divisors. if that is impossible (eg 2 mod 4 and 3 mod 6) return null
	val res = mutableListOf<Pair<Sint, Sint>>()

	val queue = ArrayDeque(values)

	while (queue.isNotEmpty()) {
		var (a, b) = queue.removeFirst()

		for (i in res.indices) {
			var (c, d) = res[i]
			if (d == 1.s) continue
			if (a == c && b == d) {
				b = 1.s
				break
			}
			val gcd = gcd(b, d)
			if (gcd == 1.s) continue
			if (a % gcd != c % gcd) return null // no solution
			b /= gcd
			d /= gcd
			queue.add(a % gcd to gcd)
			res[i] = c % d to d
			if (b == 1.s) break
			queue.add(a % b to b)
		}

		if (b != 1.s) {
			res.add(a to b)
		}
	}

	return res.filter { it.second != 1.s }.map { (a, b) -> a % b to b }
}

/**
 * The biggest integer r such that `r * r <= value`
 */
public fun Sint.rootFloor(): Sint {
	require(this >= 0)
	return bsLast(lower = 0.s) { it * it <= this }
}

/**
 * The smallest integer r such that `r * r >= value`
 */
public fun Sint.rootCeil(): Sint {
	require(this >= 0)
	return bsFirst(lower = 0.s) { it * it >= this }
}
