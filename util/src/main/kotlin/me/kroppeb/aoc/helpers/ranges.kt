package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.sint.*
import kotlin.collections.drop

fun SintRange.intersect(other: SintRange): SintRange {
	val start = maxOf(this.first, other.first)
	val endInclusive = minOf(this.last, other.last)
	return start..endInclusive
}

fun IntRange.intersect(other: SintRange): SintRange = this.s.intersect(other)

fun SintRange.intersect(other: IntRange): SintRange = this.intersect(other.s)


fun SintRange.merge(other: SintRange): SintRange {
	val start = minOf(this.first, other.first)
	val endInclusive = maxOf(this.last, other.last)
	return start..endInclusive
}

fun IntRange.merge(other: SintRange): SintRange = this.s.merge(other)

fun SintRange.merge(other: IntRange): SintRange = this.merge(other.s)

fun SintRange.tryUnion(other: SintRange): SintRange? {
	if (other.last < first) return null
	if (other.first > last) return null
	return merge(other)
}

fun IntRange.tryUnion(other: SintRange): SintRange? = this.s.tryUnion(other)

fun SintRange.tryUnion(other: IntRange): SintRange? = this.tryUnion(other.s)

fun Iterable<SintRange>.intersect(): SintRange = reduce { a, b -> a.intersect(b) }
fun Iterable<SintRange>.merge(): SintRange = reduce { a, b -> a.merge(b) }
fun Iterable<SintRange>.unions(): List<SintRange> {
	val x = sortedBy { it.first }
	val y = mutableListOf(x.first())
	for (i in x.drop(1)) {
		val last = y.last()

		when(val union = last.tryUnion(i)) {
			null -> y.add(i)
			else -> y[y.lastIndex] = union
		}
	}
	return y
}


fun SintRange.fracture(other: SintRange): List<SintRange> = fractureOrNull(other).filterNotNull()
fun SintRange.fractureOrEmpty(other: SintRange): List<SintRange> = fractureOrNull(other).map { it ?: SintRange.EMPTY }


/**
 * Returns a tripple (Pre, Intersection, Post)
 */
fun SintRange.fractureOrNull(other: SintRange): List<SintRange?> {
	if (other.last < first) return listOf(other, null, null)
	if (other.first > last) return listOf(null, null, other)

	val intersect = intersect(other)

	val x = if (other.first < first) {
		if (other.last > last) {
			listOf(
				other.first until intersect.first,
				intersect,
				intersect.endExclusive..other.last,
			)
		} else {
			listOf(
				other.first until intersect.first,
				intersect,
				null,
			)
		}
	} else {
		if (other.last > last) {
			listOf(
				null,
				intersect,
				intersect.endExclusive..other.last,
			)
		} else {  // the regions are equal
			listOf(
				null,
				intersect,
				null,
			)
		}
	}

	require(x.filterNotNull().pairWise().all { (a, b) -> a.intersect(b).isEmpty() })

	return x
}

fun SintRange.exactCenter(): Sint {
	require(!isEmpty()) { "Range is empty" }
	require(first divBy 2 == last divBy 2) { "Range is not centered" }

	// avoid overflow
	return if (first <= 0 && last >= 0) {
		(first + last) / 2
	} else {
		first + (last - first) / 2
	}
}