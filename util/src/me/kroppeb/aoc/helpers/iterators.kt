package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.collections.LazySet
import me.kroppeb.aoc.helpers.collections.extensions.repeat
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.sint.*
import java.math.BigInteger


public typealias MMap<K, V> = MutableMap<K, V>
public typealias MList<T> = MutableList<T>
public typealias MSet<T> = MutableSet<T>

public fun <T> Iterator<T>.getNext(): T {
	hasNext()
	return next()
}

public fun <T> Iterator<T>.getNextOrNull(): T? {
	if (hasNext()) return next()
	return null
}

public fun String.e(): List<Char> = map { it }

@JvmName("e2")
public fun Iterable<String>.e(): List<List<Char>> = map { it.e() }

@JvmName("e3")
public fun Iterable<Iterable<String>>.e(): List<List<List<Char>>> = map { it.e() }

@JvmName("e4")
public fun Iterable<Iterable<Iterable<String>>>.e(): List<List<List<List<Char>>>> = map { it.e() }

public inline fun <T, R> Iterable<Iterable<T>>.map2(convert: (T) -> R): List<List<R>> = map { it.map(convert) }

@JvmName("rleDecodeInt")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <T, R> Iterable<T>.rleDecodeI(value: (T) -> R, length: (T) -> Int): List<R> =
	flatMap { listOf(value(it)).repeat(length(it)) }


@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <T, R> Iterable<T>.rleDecode(value: (T) -> R, length: (T) -> Sint): List<R> =
	flatMap { listOf(value(it)).repeat(length(it)) }


public inline fun <T, R> Iterable<T>.rleEncode(convert: (T, Int) -> R): List<R> = blockCountsI().map { (a, b) -> convert(a, b) }


public operator fun <T> Set<T>.times(other: Set<T>): Set<T> = intersect(other)
public operator fun <T> Set<T>.plus(other: Set<T>): Set<T> = union(other)

private class SetRef<T>(val iterable: Iterable<T>) {
	override fun equals(other: Any?): Boolean {
		if (other !is SetRef<*>) return false
		if (other.iterable !== iterable) return false
		return true
	}

	override fun hashCode(): Int {
		return System.identityHashCode(iterable)
	}
}

private val seenCollectionsInAsSet = LinkedHashMap<SetRef<*>, Set<*>>()
private var seenCollectionsInAsSetWarned = mutableSetOf<SetRef<*>>()
public fun <T> Iterable<T>.asSet(): Set<T> = when (this) {
	is Set<T> -> this
	else -> {
		if (seenCollectionsInAsSetWarned.size < 5) {
			val ref = SetRef(this)
			val g = seenCollectionsInAsSet[ref]
			if (g != null && ref !in seenCollectionsInAsSetWarned) {
				if (this is Collection<T>) {
					if (this.size == g.size) {
						println("WARNING: A collection is being converted to a set multiple times, this might be slow")
						println("WARNING: Collection: ${this.toString().take(200)}")
						seenCollectionsInAsSetWarned += ref
					}
				}
			}
			if (seenCollectionsInAsSet.size >= 20) {
				// avoid memory leaking too much
				// pollFirst removes apparently
				seenCollectionsInAsSet.pollFirstEntry()
			}

			toSet().also { seenCollectionsInAsSet.putLast(ref, it) }
		} else {
			toSet()
		}
	}
}

public fun <K, A> Map<K, A>.intersect(other: Iterable<K>): Map<K, A> =
	this.keys.intersect(other.asSet()).associateWith { this[it]!! }

public fun <K, A, B> Map<K, A>.union(other: Map<K, B>): Map<K, Pair<A?, B?>> =
	this.keys.union(other.keys).associateWith { this[it] to other[it] }

public inline fun <K, A> Map<K, A>.merge(other: Map<K, A>, m: (A, A) -> A): Map<K, A> =
	this.keys.union(other.keys).associateWith {
		val x = this[it]
		val y = other[it]
		when {
			x != null && y != null -> m(x, y)
			x != null -> x
			y != null -> y
			else -> error("?")
		}
	}

public inline fun <K, A, B, R> Map<K, A>.mergeMap(other: Map<K, B>, m: (A?, B?) -> R) = this.union(other).mapValues { (_, v) ->
	m(v.first, v.second)
}

public fun <K, A, B> Map<K, A>.intersect(other: Map<K, B>): Map<K, Pair<A, B>> = this.entries.mapNotNull { (key, value) ->
	if (key in other) key to (value to other[key]!!)
	else null
}.toMap()

public inline fun <K, A, B, R> Map<K, A>.intersectMap(other: Map<K, B>, m: (A, B) -> R): Map<K, R> =
	this.entries.mapNotNull { (key, value) ->
		if (key in other) key to m(value, other[key]!!)
		else null
	}.toMap()

public fun <K, V : Comparable<V>> Map<K, V>.minByValue(): K = minBy { it.value }.key
public fun <K : Comparable<K>, V> Map<K, V>.minByKey(): V = minBy { it.key }.value

public fun <K, V : Comparable<V>> Map<K, V>.maxByValue(): K = maxBy { it.value }.key
public fun <K : Comparable<K>, V> Map<K, V>.maxByKey(): V = maxBy { it.key }.value

public fun <K, V : Comparable<V>> Map<K, V>.allMinByValue(): List<K> = allMinBy { it.value }.map { it.key }
public fun <K : Comparable<K>, V> Map<K, V>.allMinByKey(): List<V> = allMinBy { it.key }.map { it.value }
public fun <K, V : Comparable<V>> Map<K, V>.allMaxByValue(): List<K> = allMaxBy { it.value }.map { it.key }
public fun <K : Comparable<K>, V> Map<K, V>.allMaxByKey(): List<V> = allMaxBy { it.key }.map { it.value }


public fun <T> generateTimes(times: Int, next: () -> T): List<T> {
	val ret = mutableListOf<T>()
	repeat(times) {
		ret.add(next())
	}
	return ret
}

public fun <T> generateTimes(times: Sint, next: () -> T): List<T> {
	val ret = mutableListOf<T>()
	repeat(times) {
		ret.add(next())
	}
	return ret
}

/**
 * Seed isn't returned, the retured list has length times
 */
public fun <T> generateTimes(times: Int, seed: T, next: (T) -> T): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		acc = next(acc)
		ret.add(acc)
	}
	return ret
}


/**
 * Seed isn't returned, the retured list has length times
 */
public fun <T> generateTimes(times: Sint, seed: T, next: (T) -> T): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		acc = next(acc)
		ret.add(acc)
	}
	return ret
}

/**
 * The returned list has length times
 */
public fun <S, T> generateStateTimes(times: Int, seed: S, next: (state: S) -> Pair<S, T>): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		val (s, t) = next(acc)
		acc = s
		ret.add(t)
	}
	return ret
}


/**
 * The retured list has length times
 */
public fun <S, T> generateStateTimes(times: Sint, seed: S, next: (state: S) -> Pair<S, T>): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		val (s, t) = next(acc)
		acc = s
		ret.add(t)
	}
	return ret
}
//
//public inline fun <T, R> Iterable<T>.scan(start: R, transform: (R, T) -> R): List<R> {
//	var acc = start
//	val ret = mutableListOf(start)
//	for (i in this) {
//		acc = transform(acc, i)
//		ret.add(acc)
//	}
//	return ret
//}

public inline fun <T, S, R> Iterable<T>.stateScan(start: S, transform: (S, T) -> Pair<S, R>): List<R> {
	var acc = start
	val ret = mutableListOf<R>()
	for (i in this) {
		val (s, r) = transform(acc, i)
		acc = s
		ret.add(r)
	}
	return ret
}

public inline fun <T> Iterable<T>.scan(transform: (T, T) -> T): List<T> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()
	var acc = iter.next()
	val ret = mutableListOf<T>(acc)
	for (i in iter) {
		acc = transform(acc, i)
		ret.add(acc)
	}
	return ret
}

public fun <T> Iterable<T>.countEachI(): Map<T, Int> {
	val counts = mutableMapOf<T, Int>()
	for (element in this) counts.merge(element, 1, Int::plus)
	return counts
}


public fun <T> Iterable<T>.countEach(): Map<T, Sint> {
	val counts = mutableMapOf<T, Sint>()
	for (element in this) counts.merge(element, 1.s, Sint::plus)
	return counts
}

public fun <T> Iterable<T>.blockCountsI(): List<Pair<T, Int>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()
	var acc = iter.next()
	var count = 1
	val ret = mutableListOf<Pair<T, Int>>()
	for (i in iter) {
		if (acc == i) count++
		else {
			ret.add(acc to count)
			acc = i
			count = 1

		}
	}
	ret.add(acc to count)
	return ret
}


public fun <T> Iterable<T>.blockCounts(): List<Pair<T, Sint>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()
	var acc = iter.next()
	var count = 1.s
	val ret = mutableListOf<Pair<T, Sint>>()
	for (i in iter) {
		if (acc == i) count++
		else {
			ret.add(acc to count)
			acc = i
			count = 1.s

		}
	}
	ret.add(acc to count)
	return ret
}

public fun <T> Iterable<T>.blocks(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()
	var acc = iter.next()
	var count = mutableListOf<T>(acc)
	val ret = mutableListOf<List<T>>()
	for (i in iter) {
		if (acc == i) count.add(i)
		else {
			ret.add(count)
			acc = i
			count = mutableListOf<T>(acc)

		}
	}
	ret.add(count)
	return ret
}

/**
 * Verifies by trying to sort
 */
public fun <T : Comparable<T>> Iterable<T>.isSorted(): Boolean = this.sorted() == this.toList()

public fun <T : Comparable<T>> Iterable<T>.isAscending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext()) return true
	var acc = iter.next()
	for (i in iter) {
		if (acc > i) return false
		acc = i
	}
	return true
}

public fun <T : Comparable<T>> Iterable<T>.isDescending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext()) return true
	var acc = iter.next()
	for (i in iter) {
		if (acc < i) return false
		acc = i
	}
	return true
}

public fun <T : Comparable<T>> Iterable<T>.isStrictAscending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext()) return true
	var acc = iter.next()
	for (i in iter) {
		if (acc >= i) return false
		acc = i
	}
	return true
}

public fun <T : Comparable<T>> Iterable<T>.isStrictDescending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext()) return true
	var acc = iter.next()
	for (i in iter) {
		if (acc <= i) return false
		acc = i
	}
	return true
}

/**
 *
 * @param transform called once for each item in the iterable
 */
public inline fun <T, R> Iterable<T>.blockBy(transform: (T) -> R): List<List<T>> {
	// Like group by, but groups have to be continuous
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()
	val start = iter.next()
	var key = transform(start)
	var count = mutableListOf(start)
	val result = mutableListOf<List<T>>()
	for (i in iter) {
		val ikey = transform(i)
		if (key == ikey) count.add(i)
		else {
			result.add(count)
			key = ikey
			count = mutableListOf(start)

		}
	}
	return result
}

public fun Iterable<*>.areDistinct(): Boolean {
	val seen = mutableSetOf<Any?>()
	for (i in this) if (!seen.add(i)) return false
	return true
}

//inline fun <T, R> Iterable<T>.flatMapIndexed(transform: (Int, T) -> Iterable<R>): List<R> =
//	mapIndexed(transform).flatten()


public inline fun <T, R> Iterable<T>.flatMapIdx(transform: (Sint, T) -> Iterable<R>): List<R> = mapIdx(transform).flatten()


internal fun <T> List<T>.subSetsWithLength(n: Int, i: Int): List<List<T>> {
	if (n == 0) return listOf(emptyList())
	if (i + n > this.size) return emptyList()

	val item = this[i]
	return this.subSetsWithLength(n - 1, i + 1).map { listOf(item) + it } + this.subSetsWithLength(n, i + 1)
}

public fun <T> Iterable<T>.subSetsWithLength(n: Int): List<List<T>> = toList().subSetsWithLength(n, 0)

public fun <T> Iterable<T>.subSetsWithLength(n: Sint): List<List<T>> = this.subSetsWithLength(n.i)

public fun <T> Iterable<T>.pairWise(): List<Pair<T, T>> = flatMapIndexed { i, v -> drop(i + 1).map { v to it } }
public fun <T> Iterable<T>.orderedPairWise(): List<Pair<T, T>> = flatMapIndexed { i, v ->
	filterIndexed { i2, _ -> i != i2 }.map { v to it }
}

public fun <T> Iterable<T>.selfPairWise(): List<Pair<T, T>> = flatMapIndexed { i, v -> drop(i).map { v to it } }

public fun <T> Iterable<T>.cartesianSquare(): List<Pair<T, T>> = flatMap { v -> map { v to it } }
public fun <T, R> Iterable<T>.cartesianProduct(other: Iterable<R>): List<Pair<T, R>> = flatMap { v -> other.map { v to it } }
public inline fun <T, R, S> Iterable<T>.cartesianProduct(other: Iterable<R>, transform: (T, R) -> S): List<S> =
	flatMap { v -> other.map { transform(v, it) } }

public fun <T> Iterable<T>.cartesianPower(count: Int): List<List<T>> = if (count < 1) emptyList() else cartesianPower1(count)
internal fun <T> Iterable<T>.cartesianPower1(count: Int): List<List<T>> =
	if (count == 1) this.map { listOf(it) } else cartesianPower1(count - 1).cartesianProduct(this) { a, b -> a + b }

public inline fun <T1, T2, R> cartesianProductOf(v1: Iterable<T1>, v2: Iterable<T2>, transform: (T1, T2) -> R): List<R> =
	v1.flatMap { i1 -> v2.map { i2 -> transform(i1, i2) } }

public inline fun <T1, T2, T3, R> cartesianProductOf(
	v1: Iterable<T1>, v2: Iterable<T2>, v3: Iterable<T3>, transform: (T1, T2, T3) -> R
): List<R> = v1.flatMap { i1 -> cartesianProductOf(v2, v3) { i2, i3 -> transform(i1, i2, i3) } }

public inline fun <T1, T2, T3, T4, R> cartesianProductOf(
	v1: Iterable<T1>, v2: Iterable<T2>, v3: Iterable<T3>, v4: Iterable<T4>, transform: (T1, T2, T3, T4) -> R
): List<R> = v1.flatMap { i1 -> cartesianProductOf(v2, v3, v4) { i2, i3, i4 -> transform(i1, i2, i3, i4) } }

public inline fun <T1, T2, T3, T4, T5, R> cartesianProductOf(
	v1: Iterable<T1>,
	v2: Iterable<T2>,
	v3: Iterable<T3>,
	v4: Iterable<T4>,
	v5: Iterable<T5>,
	transform: (T1, T2, T3, T4, T5) -> R
): List<R> = v1.flatMap { i1 -> cartesianProductOf(v2, v3, v4, v5) { i2, i3, i4, i5 -> transform(i1, i2, i3, i4, i5) } }

public inline fun <T1, T2, T3, T4, T5, T6, R> cartesianProductOf(
	v1: Iterable<T1>,
	v2: Iterable<T2>,
	v3: Iterable<T3>,
	v4: Iterable<T4>,
	v5: Iterable<T5>,
	v6: Iterable<T6>,
	transform: (T1, T2, T3, T4, T5, T6) -> R
): List<R> = v1.flatMap { i1 ->
	cartesianProductOf(v2, v3, v4, v5, v6) { i2, i3, i4, i5, i6 ->
		transform(
			i1, i2, i3, i4, i5, i6
		)
	}
}

public fun <T1, T2> cartesianProductOf(
	v1: Iterable<T1>, v2: Iterable<T2>
): List<Pair<T1, T2>> = cartesianProductOf(v1, v2) { i1, i2 -> i1 to i2 }

public fun <T1, T2> cartesianProductOfHet(
	v1: Iterable<T1>, v2: Iterable<T2>
): List<Het2<T1, T2>> = cartesianProductOf(v1, v2) { i1, i2 -> i1 toH i2 }

public fun <T1, T2, T3> cartesianProductOfHet(
	v1: Iterable<T1>, v2: Iterable<T2>, v3: Iterable<T3>
): List<Het3<T1, T2, T3>> = cartesianProductOf(v1, v2, v3) { i1, i2, i3 -> i1 toH i2 toH i3 }

public fun <T1, T2, T3, T4> cartesianProductOfHet(
	v1: Iterable<T1>, v2: Iterable<T2>, v3: Iterable<T3>, v4: Iterable<T4>
): List<Het4<T1, T2, T3, T4>> = cartesianProductOf(v1, v2, v3, v4) { i1, i2, i3, i4 -> i1 toH i2 toH i3 toH i4 }

public fun <T1, T2, T3, T4, T5> cartesianProductOfHet(
	v1: Iterable<T1>,
	v2: Iterable<T2>,
	v3: Iterable<T3>,
	v4: Iterable<T4>,
	v5: Iterable<T5>
): List<Het5<T1, T2, T3, T4, T5>> = cartesianProductOf(v1, v2, v3, v4, v5) { i1, i2, i3, i4, i5 -> i1 toH i2 toH i3 toH i4 toH i5 }

public fun <T1, T2, T3, T4, T5, T6> cartesianProductOfHet(
	v1: Iterable<T1>,
	v2: Iterable<T2>,
	v3: Iterable<T3>,
	v4: Iterable<T4>,
	v5: Iterable<T5>,
	v6: Iterable<T6>
): List<Het6<T1, T2, T3, T4, T5, T6>> = cartesianProductOf(v1, v2, v3, v4, v5, v6) { i1, i2, i3, i4, i5, i6 -> i1 toH i2 toH i3 toH i4 toH i5 toH i6 }


public fun <T> Iterator<T>.powerSet(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext()) return listOf(listOf())
	val pre = listOf(iter.getNext())
	val next = iter.powerSet()
	return next + next.map { pre + it }
}

public fun <T> Iterable<T>.powerSet(): List<List<T>> = iterator().powerSet()

//@Deprecated("use trait version")
public fun Iterable<Int>.cumSum(): List<Int> = scan(Int::plus)

public val <T>Iterable<Pair<T, *>>.firsts: List<T> get() = map { it.first }
public val <T>Iterable<Pair<*, T>>.seconds: List<T> get() = map { it.second }


public inline fun <T> Iterable<T>.splitOn(predicate: (T) -> Boolean): List<List<T>> {
	val d = mutableListOf<List<T>>()
	var u = mutableListOf<T>()
	for (i in this) {
		if (predicate(i)) {
			d += u
			u = mutableListOf()
		} else {
			u.add(i)
		}
	}
	d += u
	return d;
}


// transpose
public fun <T> Iterable<Iterable<T>>.transpose(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()

	val ret = mutableListOf<List<T>>()
	val iters = this.map { it.iterator() }

	while (iters.all { it.hasNext() }) {
		ret.add(iters.map { it.next() })
	}

	// check if any has items left
	if (iters.any { it.hasNext() }) throw IllegalArgumentException("Not all iterators have been exhausted")

	return ret
}

// transpose, but no throw
public fun <T> Iterable<Iterable<T>>.transposeOrStop(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()

	val ret = mutableListOf<List<T>>()
	val iters = this.map { it.iterator() }

	while (iters.all { it.hasNext() }) {
		ret.add(iters.map { it.next() })
	}

	return ret
}

//transpose or nulls
public fun <T> Iterable<Iterable<T>>.transposeOrNulls(): List<List<T?>> {
	val iter = iterator()
	if (!iter.hasNext()) return emptyList()

	val ret = mutableListOf<List<T?>>()
	val iters = this.map { it.iterator() }

	while (iters.any { it.hasNext() }) {
		ret.add(iters.map { if (it.hasNext()) it.next() else null })
	}

	return ret
}

@JvmName("stringTranspose")
public fun Iterable<String>.transpose(): List<String> = this.e().transpose().map{it.join()}


public inline fun <T, R> Iterable<T>.repeatMap(count: Int, mapping: (Int, T) -> R): List<R> =
	(0 until count).flatMap { i -> map { mapping(i, it) } }

public inline fun <T, R> Iterable<T>.repeatMap(count: Sint, mapping: (Sint, T) -> R): List<R> =
	(0 until count).flatMap { i -> map { mapping(i, it) } }

public fun Iterable<String>.splitOnEmpty(): List<List<String>> = this.splitOn { it.isEmpty() }

@JvmName("productInts")
public fun Iterable<Int>.product(): Long = map{it.s}.product().l
public fun Iterable<Long>.product(): Long = map{it.s}.product().l
public fun Iterable<Double>.product(): Double = this.fold(1.0) { acc, i -> acc * i }

@JvmName("productSints")
public fun Iterable<Sint>.product(): Sint = this.fold(1.s) { acc, i -> acc * i }


@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("productOfInts")
public inline fun <T> Iterable<T>.productOf(transform: (T) -> Int): Long = this.fold(1.s) { acc, i -> acc * transform(i) }.l

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("productOfLongs")
public inline fun <T> Iterable<T>.productOf(transform: (T) -> Long): Long = this.fold(1.s) { acc, i -> acc * transform(i) }.l

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <T> Iterable<T>.productOf(transform: (T) -> Double): Double = this.fold(1.0) { acc, i -> acc * transform(i) }

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <T> Iterable<T>.productOf(transform: (T) -> Sint): Sint = this.fold(1.s) { acc, i -> acc * transform(i) }

public fun Iterable<Char>.join(): String = this.joinToString("")

@JvmName("joinStrings")
public fun Iterable<String>.join(): String = this.joinToString("")

public fun <T : Comparable<T>> Iterable<T>.medianOdd(): T {
	val count = this.count()
	require(count % 2 == 1)
	return this.sorted()[count / 2]
}

public fun <T : Comparable<T>> Iterable<T>.medianEven(): Pair<T, T> {
	val count = this.count()
	require(count % 2 == 0)
	val sorted = this.sorted()
	return sorted[count / 2 - 1] to sorted[count / 2]
}


public fun <T> List<T>.permutations(): List<List<T>> = if (this.isEmpty()) listOf(emptyList()) else this.indices.flatMap {
	(this.subList(0, it) + this.subList(it + 1, this.size)).permutations().map { l -> listOf(this[it]) + l }
}

public fun <T> Iterable<T>.permutations() = toList().permutations()

public fun <T : Comparable<T>> Iterable<T>.max(n: Int): List<T> = this.sortedDescending().take(n)
public fun <T : Comparable<T>> Iterable<T>.min(n: Int): List<T> = this.sorted().take(n)
public fun <T : Comparable<T>> Iterable<T>.max(n: Sint): List<T> = this.sortedDescending().take(n)
public fun <T : Comparable<T>> Iterable<T>.min(n: Sint): List<T> = this.sorted().take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.maxBy(n: Int, crossinline selector: (T) -> C): List<T> =
	this.sortedByDescending(selector).take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.minBy(n: Int, crossinline selector: (T) -> C): List<T> =
	this.sortedBy(selector).take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.maxBy(n: Sint, crossinline selector: (T) -> C): List<T> =
	this.sortedByDescending(selector).take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.minBy(n: Sint, crossinline selector: (T) -> C): List<T> =
	this.sortedBy(selector).take(n)


public inline fun <T, C : Comparable<C>> Iterable<T>.maxOf(n: Int, selector: (T) -> C): List<C> =
	this.map(selector).sortedDescending().take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.minOf(n: Int, selector: (T) -> C): List<C> = this.map(selector).sorted().take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.maxOf(n: Sint, selector: (T) -> C): List<C> =
	this.map(selector).sortedDescending().take(n)

public inline fun <T, C : Comparable<C>> Iterable<T>.minOf(n: Sint, selector: (T) -> C): List<C> = this.map(selector).sorted().take(n)

//region String destructors
public operator fun String.component1(): Char = this[0]
public operator fun String.component2(): Char = this[1]
public operator fun String.component3(): Char = this[2]
public operator fun String.component4(): Char = this[3]
public operator fun String.component5(): Char = this[4]
public operator fun String.component6(): Char = this[5]
public operator fun String.component7(): Char = this[6]
public operator fun String.component8(): Char = this[7]
public operator fun String.component9(): Char = this[8]
public operator fun String.component10(): Char = this[9]
public operator fun String.component11(): Char = this[10]
public operator fun String.component12(): Char = this[11]
public operator fun String.component13(): Char = this[12]
public operator fun String.component14(): Char = this[13]
public operator fun String.component15(): Char = this[14]
public operator fun String.component16(): Char = this[15]
public operator fun String.component17(): Char = this[16]
public operator fun String.component18(): Char = this[17]
public operator fun String.component19(): Char = this[18]
public operator fun String.component20(): Char = this[19]
public operator fun String.component21(): Char = this[20]
public operator fun String.component22(): Char = this[21]
public operator fun String.component23(): Char = this[22]
public operator fun String.component24(): Char = this[23]
public operator fun String.component25(): Char = this[24]
public operator fun String.component26(): Char = this[25]
public operator fun String.component27(): Char = this[26]
public operator fun String.component28(): Char = this[27]
public operator fun String.component29(): Char = this[28]
public operator fun String.component30(): Char = this[29]

public operator fun String.get(indexes: IntRange): String = substring(indexes.first, indexes.last + 1)
public operator fun String.get(indexes: SintRange): String = substring(indexes.first.i, (indexes.last + 1).i)

public operator fun String.get(indexes: IntProgression): List<Char> = indexes.map { this[it] }
public operator fun String.get(indexes: SintProgression): List<Char> = indexes.map { this[it.i] }

//endregion


public infix fun <T> Collection<T>.splitIn(n: Int): List<List<T>> {
	val length = this.size
	require(size divBy n)
	return chunked(length / n)
}


public infix fun String.splitIn(n: Int): List<String> {
	val length = this.length
	require(length divBy n)
	return chunked(length / n)
}

public fun <T, R> Collection<T>.splitIn(n: Int, transform: (List<T>) -> R): List<R> {
	val length = this.size
	require(size divBy n)
	return chunked(length / n, transform)
}

public infix fun <T> Collection<T>.splitIn(n: Sint): List<List<T>> {
	val length = this.size
	require(size divBy n)
	return chunked(length / n)
}


public infix fun String.splitIn(n: Sint): List<String> {
	val length = this.length
	require(length divBy n)
	return e().chunked(length / n).map { it.join() }
}

public fun <T, R> Collection<T>.splitIn(n: Sint, transform: (List<T>) -> R): List<R> {
	val length = this.size
	require(size divBy n)
	return chunked(length / n, transform)
}

public fun <T> Collection<T>.splitIn2(): Pair<List<T>, List<T>> = splitIn(2).let { (a, b) -> a to b }
public fun String.splitIn2(): Pair<String, String> = splitIn(2).let { (a, b) -> a to b }
public infix fun <T, R> Collection<T>.splitIn2(transform: (List<T>) -> R): Pair<R, R> =
	splitIn(2, transform).let { (a, b) -> a to b }


public fun <T> Iterable<Iterable<T>>.union(): Set<T> = this.reduce(Iterable<T>::union).asSet()
public fun <T> Iterable<Iterable<T>>.intersect(): Set<T> = this.reduce(Iterable<T>::intersect).asSet()

public fun <T> Iterable<Collection<T>>.unionLazy(): Collection<T> {
	val p = this.toList()
	return when(p.size) {
		0 -> emptySet()
		1 -> p.first()
		else -> p.reduce(LazySet.Companion::union)
	}
}

public fun <T> Iterable<Collection<T>>.intersectLazy(): Collection<T> {
	val p = this.toList()
	return when(p.size) {
		0 -> emptySet()
		1 -> p.first()
		else -> p.reduce(LazySet.Companion::union)
	}
}

public fun <T> Iterable<Set<T>>.unionLazy(): Set<T> {
	val p = this.toList()
	return when(p.size) {
		0 -> emptySet()
		1 -> p.first()
		else -> p.reduce(LazySet.Companion::union)
	}
}

public fun <T> Iterable<Set<T>>.intersectLazy(): Set<T> {
	val p = this.toList()
	return when(p.size) {
		0 -> emptySet()
		1 -> p.first()
		else -> p.reduce(LazySet.Companion::union)
	}
}


public fun <T> Pair<Iterable<T>, Iterable<T>>.union(): Set<T> = first or second

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
public fun <T, R, V> Pair<Iterable<T>, Iterable<R>>.intersect(): Set<V> where V : T, V : R = first and second as Iterable<V>

public infix fun <T> Iterable<T>.notIn(other: Iterable<*>): Set<T> = LazySet.difference(this, other)

// intersection, but better types
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
public fun <T, R, V> Iterable<T>.onlyIn(other: Iterable<R>): Set<V> where V : T, V : R = LazySet.intersection(this, other)


// onlyIn but it's infix
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
public infix fun <T, R, V> Iterable<T>.inter(other: Iterable<R>): Set<V> where V : T, V : R = this.onlyIn(other)

// symdiff
public infix fun <T> Iterable<T>.symDiff(other: Iterable<T>): Set<T> =
	LazySet.difference(this, other) or LazySet.difference(other, this)

// union, but it's infix
public infix fun <T> Iterable<T>.or(other: Iterable<T>): Set<T> = LazySet.union(this, other)

// onlyIn but it's infix
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
public infix fun <T, R, V> Iterable<T>.and(other: Iterable<R>): Set<V> where V : T, V : R = this.onlyIn(other)

// aka: does this intersect
public infix fun <T> Iterable<T>.anyIn(other: Iterable<T>): Boolean {
	val o = other.asSet()
	return any { it in o }
}

// aka: is this a subset of
public infix fun <T> Iterable<T>.allIn(other: Iterable<T>): Boolean {
	val o = other.asSet()
	return all { it in o }
}

// aka: are these fully distinct
public infix fun <T> Iterable<T>.noneIn(other: Iterable<T>): Boolean {
	val o = other.asSet()
	return none { it in o }
}

// aka: is this a superset of
public infix fun <T> Iterable<T>.containsAllOf(other: Iterable<T>) = other.allIn(this)

public fun <T, R> Pair<Iterable<T>, Iterable<R>>.zipped() = first.zip(second)
public inline fun <T, R, V> Pair<Iterable<T>, Iterable<R>>.zipped(transform: (T, R) -> V) = first.zip(second, transform)


public fun <T> Iterable<T>.rotateLeft(i: Int): List<T> {
	val list = this.toList()
	val shift = i mod list.size

	return list.splitAt(shift).on { l, r -> r + l }
}

public fun <T> Iterable<T>.rotateRight(i: Int): List<T> = rotateLeft(-i)


public fun <T> Iterable<T>.rotateLeft(i: Sint): List<T> {
	val list = this.toList()
	val shift = i mod list.size

	return list.splitAt(shift).on { l, r -> r + l }
}

public fun <T> Iterable<T>.rotateRight(i: Sint): List<T> = rotateLeft(-i)

public fun <T> Iterable<T>.splitAt(i: Int): Pair<List<T>, List<T>> = take(i) to drop(i)
public fun <T> Iterable<T>.splitAt(i: Sint): Pair<List<T>, List<T>> = take(i) to drop(i)
public fun String.splitAt(i: Int): Pair<String, String> = take(i) to drop(i)
public fun String.splitAt(i: Sint): Pair<String, String> = take(i) to drop(i)


public fun <T> Iterable<T>.split2(item: T): Pair<List<T>, List<T>> {
	val list = this.toList()
	val idx = list.indexOf(item)
	return take(idx) to drop(idx + 1)
}

public fun String.split2(item: String): Pair<String, String> {
	val idx = indexOf(item)
	return take(idx) to drop(idx + item.length)
}


public inline fun <T> Iterable<T>.split2On(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
	val before = mutableListOf<T>()
	val iterator = this.iterator()
	for (i in iterator) {
		if (!predicate(i)) {
			before.add(i)
		} else {
			break
		}
	}
	val after = mutableListOf<T>()
	for (i in iterator) {
		after.add(i)
	}

	return before to after
}

public fun <T> Iterable<T>.takeUntilInc(predicate: (T) -> Boolean): List<T> = buildList {
	for (i in this@takeUntilInc) {
		add(i)
		if (predicate(i)) {
			break
		}
	}
}

public inline fun <T> Iterable<T>.partitionIndexed(predicate: (Int, T) -> Boolean) =
	withIndex().partition { (i, value) -> predicate(i, value) }.map { l -> l.map { it.value } }


public inline fun <T> Iterable<T>.partitionIdx(predicate: (Sint, T) -> Boolean) =
	withIdx().partition { (i, value) -> predicate(i, value) }.map { l -> l.map { it.value } }

public inline fun <T, R : T> T.applyNTimes(n: Int, action: (T) -> R): R {
	require(n > 0)
	return action(this).applyNTimesOr0(n - 1, action)
}

public inline fun <T> T.applyNTimesOr0(n: Int, action: (T) -> T): T {
	var cur = this;
	repeat(n) {
		cur = action(cur)
	}
	return cur
}


public inline fun <T, R : T> T.applyNTimes(n: Sint, action: (T) -> R): R {
	require(n > 0)
	return action(this).applyNTimesOr0(n - 1, action)
}

public inline fun <T> T.applyNTimesOr0(n: Sint, action: (T) -> T): T {
	var cur = this;
	repeat(n) {
		cur = action(cur)
	}
	return cur
}

public fun Iterable<Iterable<Boolean>>.printCrt() {
	for (i in this) {
		println(i.joinToString("") { if (it) "##" else "  " })
	}
}


@Suppress("UNCHECKED_CAST")
public fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> = filterValues { it != null } as Map<K, V>

@Suppress("UNCHECKED_CAST")
public fun <K, V> Map<K?, V>.filterNotNullKeys(): Map<K, V> = filterKeys { it != null } as Map<K, V>


public inline fun <T, K> Iterable<T>.associateByNotNull(keySelector: (T) -> K?): Map<K, T> =
	associateBy(keySelector).filterNotNullKeys()

public inline fun <T, K, V> Iterable<T>.associateByNotNull(keySelector: (T) -> K?, valueTransform: (T) -> V?): Map<K, V> =
	associateBy(keySelector, valueTransform).filterNotNullKeys().filterNotNullValues()

public inline fun <K, V> Iterable<K>.associateWithNotNull(valueSelector: (K) -> V?): Map<K, V> =
	associateWith(valueSelector).filterNotNullValues()

@Suppress("UnusedReceiverParameter")
public fun <T> List<T>.tas(): MList<T> = mutableListOf()
@Suppress("UnusedReceiverParameter")
public fun <T> Set<T>.tas(): MSet<T> = mutableSetOf()
@Suppress("UnusedReceiverParameter")
public fun <K, V> Map<K, V>.tas(): MMap<K, V> = mutableMapOf()
@Suppress("UnusedReceiverParameter")
public fun <T> List<T>.emptyLike(): MList<T> = mutableListOf()
@Suppress("UnusedReceiverParameter")
public fun <T> Set<T>.emptyLike(): MSet<T> = mutableSetOf()
@Suppress("UnusedReceiverParameter")
public fun <K, V> Map<K, V>.emptyLike(): MMap<K, V> = mutableMapOf()

@Suppress("UNUSED_PARAMETER")
public fun <T> listTT(example: T): MList<T> = mutableListOf()
@Suppress("UNUSED_PARAMETER")
public fun <T> setTT(example: T): MSet<T> = mutableSetOf()
@Suppress("UNUSED_PARAMETER")
public fun <K, V> mapTT(example: Pair<K, V>): MMap<K, V> = mutableMapOf()
@Suppress("UNUSED_PARAMETER")
public fun <K, V> mapTT(exampleKey: K, exampleValue: V): MMap<K, V> = mutableMapOf()
@Suppress("UNUSED_PARAMETER")
public fun <T> listLike(example: T): MList<T> = mutableListOf()
@Suppress("UNUSED_PARAMETER")
public fun <T> setLike(example: T): MSet<T> = mutableSetOf()
@Suppress("UNUSED_PARAMETER")
public fun <K, V> mapLike(example: Pair<K, V>): MMap<K, V> = mutableMapOf()
@Suppress("UNUSED_PARAMETER")
public fun <K, V> mapLike(exampleKey: K, exampleValue: V): MMap<K, V> = mutableMapOf()

public val IntRange.size: Int get() = (this.last.s - this.first + 1).i
public val LongRange.size: Long get() = (this.last.s - this.first + 1).l

public val LongRange.sizeB: BigInteger get() = this.last.toBigInteger() - this.first.toBigInteger() + BigInteger.ONE


public fun IntRange.sint(): SintRange = this.first.s..this.last.s
public fun LongRange.sint(): SintRange = this.first.s..this.last.s
public fun SintRange.sint(): SintRange = this.first..this.last

public fun SintRange.int(): IntRange = this.first.i..this.last.i
public fun SintRange.long(): LongRange = this.first.l..this.last.l

public fun IntProgression.sint(): SintProgression = this.first.s..this.last.s step this.step
public fun LongProgression.sint(): SintProgression = this.first.s..this.last.s step this.step
public fun SintProgression.sint(): SintProgression = this.first..this.last step this.step

public fun SintProgression.int(): IntProgression = this.first.i..this.last.i step this.step.i
public fun SintProgression.long(): LongProgression = this.first.l..this.last.l step this.step.l

public fun IntRange.frac(n: Int): List<IntRange> = this.sint().frac(n).map { it.int() }
public fun LongRange.frac(n: Int): List<LongRange> = this.sint().frac(n).map { it.long() }

public fun SintRange.frac(n: Sint): List<SintRange> = frac(n.i)

public fun SintRange.frac(n: Int): List<SintRange> {
	if (this.sizeS <= n) return this.map { it..it }
	val step = this.sizeS / n
	val rem = this.sizeS % n

	val ret = mutableListOf<SintRange>()
	for (i in 0 until n) {
		val start = this.first + i * step + min(i.s, rem)
		val end = start + step + if (i < rem) 1.s else 0.s
		ret.add(start until end)
	}
	return ret
}

public val IntProgression.size: Int get() = ((this.last.s - this.first) / this.step + 1).i
public val LongProgression.size: Long get() = ((this.last.s - this.first) / this.step + 1).l

public fun IntProgression.frac(n: Int): List<IntProgression> {
	if (this.size <= n) return this.map { it..it step this.step }
	val step = this.size / n * this.step
	val rem = this.size % n

	val ret = mutableListOf<IntProgression>()
	for (i in 0 until n) {
		val start = this.first + i * step + min(i, rem) * this.step
		val end = start + step + if (i < rem) 1 else 0
		ret.add(start until end step this.step)
	}
	return ret
}

public fun LongProgression.frac(n: Int): List<LongProgression> {
	if (this.size <= n) return this.map { it..it step this.step }
	val step = this.size / n * this.step
	val rem = this.size % n

	val ret = mutableListOf<LongProgression>()
	for (i in 0 until n) {
		val start = this.first + i * step + min(i.toLong(), rem) * this.step
		val end = start + step + if (i < rem) 1 else 0
		ret.add(start until end step this.step)
	}
	return ret
}

public fun SintProgression.frac(n: Sint): List<SintProgression> = frac(n.i)

public fun SintProgression.frac(n: Int): List<SintProgression> {
	if (this.sizeS <= n) return this.map { it..it step this.step }
	val step = this.sizeS / n * this.step
	val rem = this.sizeS % n

	val ret = mutableListOf<SintProgression>()
	for (i in 0 until n) {
		val start = this.first + i * step + min(i.s, rem) * this.step
		val end = start + step + if (i < rem) 1 else 0
		ret.add(start until end step this.step)
	}
	return ret
}


public fun Iterable<*>.isEmpty(): Boolean = !this.iterator().hasNext()

public fun Iterable<*>.allEqual(): Boolean {
	val iter = this.iterator()
	if (!iter.hasNext()) return true // empty
	val first = iter.next()
	for (i in iter) {
		if (i != first) return false
	}
	return true
}

public fun <T> MutableList<T>.swap(indexA: Int, indexB: Int) {
	val temp = this[indexA]
	this[indexA] = this[indexB]
	this[indexB] = temp
}

public fun <T> MutableList<T>.swap(indexA: Sint, indexB: Int) {
	val temp = this[indexA]
	this[indexA] = this[indexB]
	this[indexB] = temp
}


public fun <T> MutableList<T>.swap(indexA: Int, indexB: Sint) {
	val temp = this[indexA]
	this[indexA] = this[indexB]
	this[indexB] = temp
}

public fun <T> MutableList<T>.swap(indexA: Sint, indexB: Sint) {
	val temp = this[indexA]
	this[indexA] = this[indexB]
	this[indexB] = temp
}

public fun <K, V> MutableMap<K, V>.swap(keyA: K, keyB: K) {
	val temp = this.getOrElse(keyA) { throw IllegalArgumentException("Key $keyA not found") }
	this[keyA] = this.getOrElse(keyB) { throw IllegalArgumentException("Key $keyB not found") }
	this[keyB] = temp
}