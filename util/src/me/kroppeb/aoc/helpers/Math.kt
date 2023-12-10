package me.kroppeb.aoc.helpers


import me.kroppeb.aoc.helpers.sint.*
import java.lang.IllegalArgumentException


fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
fun gcd(a: Long, b: Long): Long = if (a == 0L) b else gcd(b % a, a)
fun gcd(a: Sint, b: Sint): Sint = if (a.isZero()) b else gcd(b % a, a)

fun egcd(a: Int, b: Int): Triple<Int, Int, Int> {
	if (a == 0) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

fun egcd(a: Long, b: Long): Triple<Long, Long, Long> {
	if (a == 0L) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

fun egcd(a: Sint, b: Sint): Triple<Sint, Sint, Sint> {
	if (a.isZero()) return Triple(b, 0.s, 1.s)
	val (g, x, y) = egcd(b mod a, a)
	return Triple(g, y - (b.floorDiv(a)) * x, x)
}

@JvmName("intGcd")
fun Iterable<Int>.gcd(): Int = reduce(::gcd)

@JvmName("longGcd")
fun Iterable<Long>.gcd(): Long = reduce(::gcd)

@JvmName("sintGcd")
fun Iterable<Sint>.gcd(): Sint = reduce(::gcd)

fun gcd(vararg e: Int) = e.toList().gcd()
fun gcd(vararg e: Long) = e.toList().gcd()
//fun gcd(vararg e:Sint) = e.toList().gcd()

fun lcm(a: Int, b: Int): Int = a / gcd(a, b) * b
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b
fun lcm(a: Sint, b: Sint): Sint = a / gcd(a, b) * b


@JvmName("intLcm")
fun Iterable<Int>.lcm(): Int = reduce(::lcm)

@JvmName("longLcm")
fun Iterable<Long>.lcm(): Long = reduce(::lcm)

@JvmName("sintLcm")
fun Iterable<Sint>.lcm(): Sint = reduce(::lcm)

fun lcm(vararg e: Int) = e.toList().lcm()
fun lcm(vararg e: Long) = e.toList().lcm()
//fun lcm(vararg e:Sint) = e.toList().lcm()


fun IntArray.min(): Int = minOrNull()!!
fun IntArray.max(): Int = maxOrNull()!!

fun LongArray.min(): Long = minOrNull()!!
fun LongArray.max(): Long = maxOrNull()!!

fun DoubleArray.min(): Double = minOrNull()!!
fun DoubleArray.max(): Double = maxOrNull()!!


inline fun <C, T : Comparable<T>> Iterable<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C, T : Comparable<T>> Iterable<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

fun <T : Comparable<T>> Iterable<T>.allMin(): List<T> = allMinBy { it }
fun <T : Comparable<T>> Iterable<T>.allMax(): List<T> = allMaxBy { it }
inline fun <C, T : Comparable<T>> Iterable<C>.allMinOf(b: (C) -> T): List<T> = map(b).allMin()
inline fun <C, T : Comparable<T>> Iterable<C>.allMaxOf(b: (C) -> T): List<T> = map(b).allMax()

inline fun <C, T : Comparable<T>> Array<C>.minBy(b: (C) -> T): C = minByOrNull(b)!!
inline fun <C, T : Comparable<T>> Array<C>.maxBy(b: (C) -> T): C = maxByOrNull(b)!!

inline fun <C, T : Comparable<T>> Array<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C, T : Comparable<T>> Array<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> IntArray.minBy(b: (Int) -> C): Int = minByOrNull(b)!!
inline fun <C : Comparable<C>> IntArray.maxBy(b: (Int) -> C): Int = maxByOrNull(b)!!

inline fun <C : Comparable<C>> IntArray.allMinBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> IntArray.allMaxBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> LongArray.minBy(b: (Long) -> C): Long = minByOrNull(b)!!
inline fun <C : Comparable<C>> LongArray.maxBy(b: (Long) -> C): Long = maxByOrNull(b)!!

inline fun <C : Comparable<C>> LongArray.allMinBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> LongArray.allMaxBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> DoubleArray.minBy(b: (Double) -> C): Double = minByOrNull(b)!!
inline fun <C : Comparable<C>> DoubleArray.maxBy(b: (Double) -> C): Double = maxByOrNull(b)!!

inline fun <C : Comparable<C>> DoubleArray.allMinBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> DoubleArray.allMaxBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <K, V, T : Comparable<T>> Map<K, V>.minBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = minByOrNull(b)!!
inline fun <K, V, T : Comparable<T>> Map<K, V>.maxBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = maxByOrNull(b)!!

inline fun <K, V, T : Comparable<T>> Map<K, V>.allMinBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <K, V, T : Comparable<T>> Map<K, V>.allMaxBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

@JvmName("minMaxVararg")
fun <T : Comparable<T>> minMax(vararg e: T): Pair<T, T> = e.min() to e.max()
fun <T : Comparable<T>> Iterable<T>.minMax(): Pair<T, T> = min() to max()
fun <T : Comparable<T>> Array<T>.minMax(): Pair<T, T> = min() to max()
fun IntArray.minMax(): Pair<Int, Int> = min() to max()
fun LongArray.minMax(): Pair<Long, Long> = min() to max()
fun DoubleArray.minMax(): Pair<Double, Double> = min() to max()

@JvmName("minMaxIRIntsVararg")
fun minMaxRange(vararg e: Int): IntRange = e.min()..e.max()

@JvmName("minMaxIRInts")
fun Iterable<Int>.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRInts")
fun Array<Int>.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRInts")
fun IntArray.minMaxRange(): IntRange = min()..max()

@JvmName("minMaxIRLongsVararg")
fun minMaxRange(vararg e: Long): LongRange = e.min()..e.max()

@JvmName("minMaxIRLongs")
fun Iterable<Long>.minMaxRange(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
fun Array<Long>.minMaxRange(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
fun LongArray.minMaxRange(): LongRange = min()..max()


@Suppress("FINAL_UPPER_BOUND")
@JvmName("minMaxIRSintsVararg")
fun <TSint : Sint> minMaxRange(vararg e: TSint): SintRange = e.min()..e.max()

@JvmName("minMaxIRSints")
fun Iterable<Sint>.minMaxRange(): SintRange = min()..max()

@JvmName("minMaxIRSints")
fun Array<Sint>.minMaxRange(): SintRange = min()..max()

inline fun <T> Iterable<T>.minMaxRangeOf(mapping: (T) -> Sint): SintRange = minOf(mapping)..maxOf(mapping)

fun Int.pow(x: Int): Int = when {
	x == 0 -> 1
	x == 1 -> this
	x % 2 == 0 -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

fun Long.pow(x: Int): Long = when {
	x == 0 -> 1
	x == 1 -> this
	x % 2 == 0 -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

fun Long.powMod(x: Int, y: Long): Long = when {
	x < 0 -> if (this == 0L) throw IllegalArgumentException("0^$x mod $y") else this.modInv(y).powMod(-x, y)
	x == 0 -> 1
	x == 1 -> this mod y
	x % 2 == 0 -> (this * this % y).powMod(x / 2, y)
	else -> (this * this % y).powMod(x / 2, y) * this % y
}

fun Int.primeFactors(): List<Pair<Int, Int>> {
	val factors = mutableListOf<Pair<Int, Int>>()
	var n = this
	for (i in 2..n) {
		var count = 0
		while (n % i == 0) {
			count++
			n /= i
		}
		if (count > 0) factors.add(i to count)
	}
	return factors
}

fun Int.allDivisors(): List<Int> {
	val factors = primeFactors()
	var divisors = listOf(1)

	for ((factor, count) in factors) {
		divisors = divisors.flatMap { d -> (0..count).map { d * factor.pow(it) } }
	}

	return divisors
}

infix fun Int.mod(base: Int) = Math.floorMod(this, base)
infix fun Long.mod(base: Int) = Math.floorMod(this, base)
infix fun Long.mod(base: Long) = Math.floorMod(this, base)


fun Int.modInv(base: Int): Int {
	val (g, x, _) = egcd(this, base)
	if (g != 1) throw IllegalArgumentException("No inverse")
	return x mod base
}

fun Long.modInv(base: Long): Long {
	val (g, x, _) = egcd(this, base)
	if (g != 1L) throw IllegalArgumentException("No inverse")
	return x mod base
}

infix fun Int.divBy(other: Int): Boolean = this % other == 0
infix fun Int.divBy(other: Long): Boolean = this.toLong() divBy other
infix fun Int.divBy(other: Sint): Boolean = this.s divBy other
infix fun Long.divBy(other: Int): Boolean = this divBy other.toLong()
infix fun Long.divBy(other: Long): Boolean = this % other == 0L
infix fun Long.divBy(other: Sint): Boolean = this.s divBy other
infix fun Sint.divBy(other: Int): Boolean = this divBy other.s
infix fun Sint.divBy(other: Long): Boolean = this divBy other.s
infix fun Sint.divBy(other: Sint): Boolean = this.l divBy other.l


private var crtWarning = false

@JvmName("crtIntInt")
fun crt(values: List<Pair<Int, Int>>): Sint = crt(values.map { it.first.s to it.second.s })
@JvmName("crtSintInt")
fun crt(values: List<Pair<Sint, Int>>): Sint = crt(values.map { it.first to it.second.s })
@JvmName("crtIntSint")
fun crt(values: List<Pair<Int, Sint>>): Sint = crt(values.map { it.first.s to it.second })
fun crt(values: List<Pair<Sint, Sint>>): Sint {
	if (!crtWarning) {
		for ((a,b) in values) {
			if (a !in 0 until b) {
				println("CRT Warning: $a !in 0 until $b")
				crtWarning = true
				break
			}
		}
	}

	require(values.pairWise().all{gcd(it.first.second, it.second.second) == 1.s}){"Not pairwise coprime"}

	val md = values.map { it.second }.product()
	val mds = values.map { md / it.second }
	val ys = mds.zip(values) { mul, value -> value.first * mul.modInv(value.second) * mul }
	val sum = ys.sum()
	return sum mod md

}