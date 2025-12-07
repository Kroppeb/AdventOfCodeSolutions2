package me.kroppeb.aoc.helpers.sint

import com.sschr15.aoc.annotations.SkipOverflowChecks
import me.kroppeb.aoc.helpers.*
import java.math.BigInteger
import java.util.*
import kotlin.NoSuchElementException

private var _hasWarnedAboutToBigRange = false


/**
 * A range of values of type `Int`.
 */
public class SintRange(start: Sint, endInclusive: Sint) : SintProgression(start, endInclusive, 1.s), ClosedRange<Sint>,
	OpenEndRange<Sint> {
	init {
		if (_hasWarnedAboutToBigRange && (start + 1) > endInclusive) {
			System.err.println("Warning: A negative sized sint range was created")
			_hasWarnedAboutToBigRange = true
		}
	}

	override val start: Sint get() = first
	override val endInclusive: Sint get() = last

	override val endExclusive: Sint
		get() {
			return last + 1
		}

	public fun first(): Sint = first
	public fun last(): Sint = last

	override val sizeS: Sint get() = if (this.isEmpty()) Sint.ZERO else this.last - this.first + 1
	override val sizeB: BigInteger get() = if (this.isEmpty()) BigInteger.ZERO else this.last.toBigInteger() - this.first.toBigInteger() + BigInteger.ONE

	@Suppress("ConvertTwoComparisonsToRangeCheck") // that would literally recurse
	override fun contains(value: Sint): Boolean = first <= value && value <= last

	public fun containsAll(elements: SintProgression): Boolean = elements.first in this && elements.last in this

	override fun containsAll(elements: Collection<Sint>): Boolean = when (elements) {
		is SintProgression -> this.containsAll(elements) // faster
		else -> elements.all { it in this }
	}

	/**
	 * Checks whether the range is empty.
	 *
	 * The range is empty if its start value is greater than the end value.
	 */
	override fun isEmpty(): Boolean = first > last

	override fun equals(other: Any?): Boolean =
		other is SintRange && (isEmpty() && other.isEmpty() ||
			first == other.first && last == other.last)

	override fun hashCode(): Int =
		if (isEmpty()) -1 else (31 * first.hashCode() + last.hashCode())

	override fun toString(): String = "$first..$last"

	public companion object {
		/** An empty range of values of type Int. */
		public val EMPTY: SintRange = SintRange(1.s, 0.s)
		public val MEGA: SintRange = SintRange(Sint.NEG_MEGA, Sint.POS_MEGA)
		public val INFINITE: SintRange = SintRange(Sint.MIN_VALUE, Sint.MAX_VALUE)
	}
}


/**
 * A progression of values of type `Int`.
 */
public open class SintProgression
internal constructor
	(
	start: Sint,
	endInclusive: Sint,
	step: Sint
) : Set<Sint> {
	init {
		if (step == 0.s) throw kotlin.IllegalArgumentException("Step must be non-zero.")
	}

	/**
	 * The first element in the progression.
	 */
	public val first: Sint = start

	/**
	 * The last element in the progression.
	 */
	public val last: Sint = getProgressionLastElement(start, endInclusive, step)

	/**
	 * The step of the progression.
	 */
	public val step: Sint = step

	override fun iterator(): SintIterator = SintProgressionIterator(first, last, step)

	/**
	 * Checks if the progression is empty.
	 *
	 * Progression with a positive step is empty if its first element is greater than the last element.
	 * Progression with a negative step is empty if its first element is less than the last element.
	 */
	override public open fun isEmpty(): Boolean = if (step > 0) first > last else first < last

	@Deprecated("", ReplaceWith("sizeS"))
	override val size: Int
		get() = sizeS.i

	// FIXME: this can trigger overflow when it does still fit :[
	public open val sizeS: Sint
		get() = ((
			if (step > 0) maxOf(0.s, last - first + step)
			else minOf(0.s, last - first + step)
			) / step)

	public open val sizeB: BigInteger
		get() = ((
			if (step > 0) maxOf(BigInteger.ZERO, last.toBigInteger() - first.toBigInteger() + step.toBigInteger())
			else minOf(BigInteger.ZERO, last.toBigInteger() - first.toBigInteger() + step.toBigInteger())
			) / step.toBigInteger())

	override fun containsAll(elements: Collection<Sint>): Boolean {
		return elements.all { it in this }
	}

	override fun contains(element: Sint): Boolean {
		if (step > 0) {
			if (element < first) return false
			if (element > last) return false
		} else {
			if (element > first) return false
			if (element < last) return false
		}
		return (element - first) divBy step
	}

	override fun equals(other: Any?): Boolean =
		other is SintProgression && (isEmpty() && other.isEmpty() ||
			first == other.first && last == other.last && step == other.step)

	override fun hashCode(): Int =
		if (isEmpty()) -1 else (31 * (31 * first.hashCode() + last.hashCode()) + step.hashCode())

	override fun toString(): String = if (step > 0) "$first..$last step $step" else "$first downTo $last step ${-step}"

	public companion object {
		/**
		 * Creates IntProgression within the specified bounds of a closed range.
		 *
		 * The progression starts with the [rangeStart] value and goes toward the [rangeEnd] value not excluding it, with the specified [step].
		 * In order to go backwards the [step] must be negative.
		 *
		 * [step] must be greater than `Int.MIN_VALUE` and not equal to zero.
		 */
		public fun fromClosedRange(rangeStart: Sint, rangeEnd: Sint, step: Sint): SintProgression =
			SintProgression(rangeStart, rangeEnd, step)
	}
}

internal fun getProgressionLastElement(start: Sint, end: Sint, step: Sint): Sint = when {
	step > 0 -> if (start >= end) end else end - differenceModulo(end, start, step)
	step < 0 -> if (start <= end) end else end + differenceModulo(start, end, -step)
	else -> throw kotlin.IllegalArgumentException("Step is zero.")
}


// (a - b) mod c
private fun differenceModulo(a: Sint, b: Sint, c: Sint): Sint {
	return mod(mod(a, c) - mod(b, c), c)
}


private var hasWarnedAboutToBigIterator = false

/**
 * An iterator over a progression of values of type `Int`.
 * @property step the number by which the value is incremented on each step.
 */
internal class SintProgressionIterator(first: Sint, last: Sint, val step: Sint) : SintIterator {
	private val finalElement: Sint = last
	private var hasNext: Boolean = if (step > 0) first <= last else first >= last
	private var next: Sint = if (hasNext) first else finalElement


	init {
		extractedInit(first, last)
	}

	@SkipOverflowChecks
	private fun extractedInit(first: Sint, last: Sint) {
		if (!hasWarnedAboutToBigIterator) {
			var f = (first.l / step.l).s  // To avoid sschr15's warnings
			var l = (last.l / step.l).s
			if (f < Int.MIN_VALUE && l > Int.MAX_VALUE) {
				System.err.println("Warning: You are using a SintProgressionIterator with a massive range")
				error("a")
				hasWarnedAboutToBigIterator = true
			} else if ((l - f) > Int.MAX_VALUE) {
				System.err.println("Warning: You are using a SintProgressionIterator with a massive range")
				error("a")
				hasWarnedAboutToBigIterator = true
			}
		}
	}

	override fun hasNext(): Boolean = hasNext

	override fun nextSint(): Sint {
		val value = next
		if (value == finalElement) {
			if (!hasNext) throw kotlin.NoSuchElementException()
			hasNext = false
		} else {
			next += step
		}
		return value
	}
}


/** An iterator over a sequence of values of type `Sint`. */
public interface SintIterator : Iterator<Sint> {
	override fun next(): Sint = nextSint()

	/** Returns the next value in the sequence without boxing. */
	public fun nextSint(): Sint
}

public infix fun SintProgression.step(step: Sint): SintProgression {
	checkStepIsPositive(step > 0, step.l)
	return SintProgression.fromClosedRange(first, last, if (this.step > 0) step else -step)
}

public infix fun SintProgression.step(step: Int): SintProgression = step(step.s)
public infix fun SintProgression.step(step: Long): SintProgression = step(step.s)

internal fun checkStepIsPositive(isPositive: Boolean, step: Number) {
	if (!isPositive) throw IllegalArgumentException("Step must be positive, was: $step.")
}

public class SintRangeSet private constructor(
	public val ranges: TreeMap<Sint, SintRange>
) : Set<Sint> {
	public val sizeS: Sint
		get() = ranges.values.sumOf { it.sizeS }
	public val sizeB: BigInteger
		get() = ranges.values.map { it.sizeB }.reduceOrNull(BigInteger::plus) ?: BigInteger.ZERO

	override val size: Int get() = sizeS.i

	override fun isEmpty(): Boolean = ranges.isEmpty()

	override fun iterator(): Iterator<Sint> = object : SintIterator {
		val treeIterator: Iterator<SintRange> = ranges.values.iterator()
		var rangeIterator: Iterator<Sint>? = null

		override fun hasNext(): Boolean {
			// ranges are never empty
			return (rangeIterator?.hasNext() ?: false) || treeIterator.hasNext()
		}

		override fun nextSint(): Sint {
			rangeIterator?.takeIf { it.hasNext() }?.next()?.let { return it }
			if (!treeIterator.hasNext()) {
				throw NoSuchElementException("Ran out")
			}
			val newIter = treeIterator.next().iterator()
			rangeIterator = newIter
			require(newIter.hasNext()) { throw IllegalStateException("SintRangeSet in illegal state") }
			return newIter.nextSint()
		}
	}

	public fun containsAll(elements: SintProgression): Boolean {
		val (_, range) = ranges.floorEntry(elements.first) ?: return false
		return range.containsAll(elements)
	}

	public fun containsAll(elements: SintRangeSet): Boolean {
		// this is o n log n, it could be o n, but I can't be arsed
		return elements.ranges.values.all { this.containsAll(it) }
	}

	override fun containsAll(elements: Collection<Sint>): Boolean = when (elements) {
		is SintProgression -> containsAll(elements) // faster implementation
		is SintRangeSet -> containsAll(elements) // faster implementation
		else -> elements.all { it in this }
	}

	override fun contains(element: Sint): Boolean {
		val (_, range) = ranges.floorEntry(element) ?: return false
		return element in range
	}

	public fun union(other: SintRangeSet): SintRangeSet {
		val ourRanges = this.ranges.values.iterator()
		val othRanges = other.ranges.values.iterator()

		var ourNext: SintRange? = null
		var othNext: SintRange? = null

		var curRange: SintRange? = null
		val outRanges = TreeMap<Sint, SintRange>()

		while (true) {

			if (ourNext == null && ourRanges.hasNext())
				ourNext = ourRanges.next()

			if (othNext == null && othRanges.hasNext())
				othNext = othRanges.next()


			val lowest = when {
				ourNext == null -> othNext?.also { othNext = null } ?: break
				othNext == null -> ourNext.also { ourNext = null }
				othNext!!.first < ourNext!!.first -> othNext.also { othNext = null }!!
				else -> ourNext.also { ourNext = null }!!
			}


			when(val merged = curRange?.tryUnion(lowest)) {
				null -> {
					if (curRange != null) {
						outRanges[curRange.first] = curRange
					}
					curRange = lowest
				}
				else -> curRange = merged
			}
		}
		if (curRange != null){
			outRanges[curRange.first] = curRange
		}
		return SintRangeSet(outRanges)
	}


	public companion object {
		public operator fun invoke(ranges: Iterable<SintRange>): SintRangeSet {
			val tree = TreeMap<Sint, SintRange>()

			for (range in ranges) {
				if (range.isEmpty()) continue
				// will have the same start as range
				var checkRange = tree.floorEntry(range.first)?.value?.tryUnion(range) ?: range

				while (true) {
					val (k, v) = tree.higherEntry(checkRange.first) ?: break
					if (k > checkRange.last) break
					tree.remove(k)
					checkRange = checkRange.tryUnion(v)!!
				}
				tree[checkRange.first] = checkRange
			}

			return SintRangeSet(tree)
		}
	}

}