@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.divBy
import me.kroppeb.aoc.helpers.sint
import me.kroppeb.aoc.helpers.sint.*
import java.util.*

public typealias Line = LineN<Point>
public typealias Line3D = LineN<Point3D>

public data class LineN<P : PointN<P>>(
	val start: P,
	val end: P,
	val step: P = (end - start).discreteAngle(),
) : Set<P>, List<P>, RandomAccess {
	public val diff: P = end - start;
	public val length: Sint = if (step.manDist().isZero()) Sint.ZERO else diff.manDist() / step.manDist()
	public val sizeS: Sint get() = length + 1
	override val size: Int get() = sizeS.i


	override fun toString(): String {
		return "LineN(start=$start, end=$end, diff=$diff, step=$step, length=$length)"
	}

	override operator fun contains(element: P): Boolean = when {
		element == start -> true
		element == end -> true
		// if it's on the extension of the line, but not on the line itself
		//  then one of these 2 discrete angles will be the opposite of the step
		(element - start).discreteAngle() != step -> false
		(end - element).discreteAngle() != step -> false
		else -> (element - start).manDist() divBy step.manDist()
	}

	override fun containsAll(elements: Collection<P>): Boolean = elements.all { it in this }

	override fun isEmpty(): Boolean = false

	override operator fun get(index: Int): P = this[index.s]
	public operator fun get(index: Long): P = this[index.s]
	public operator fun get(index: Sint): P {
		checkElementIndex(index, sizeS)
		return start + index * step
	}

	public fun reversed(): LineN<P> = LineN(end, start, -step)

	public operator fun get(slice: IntProgression): LineN<P> = this[slice.sint()]
	public operator fun get(slice: LongProgression): LineN<P> = this[slice.sint()]
	public operator fun get(slice: SintProgression): LineN<P> {
		checkElementIndex(slice.first, sizeS)
		checkElementIndex(slice.last, sizeS)
		return LineN(
			start + step * slice.first,
			start + step * slice.last,
			step * slice.step,
		)
	}

	public infix fun step(step: Sint): LineN<P> {
		checkStepIsPositive(step > 0, step.l)
		return LineN(this.start, this.start + this.step * (this.length / step * step), this.step * step)
	}

	override fun indexOf(element: P): Int = idxOf(element).i

	public fun idxOf(element: P): Sint {
		if (element !in this) return -1.s
		val diff = element - start
		return diff.manDist() / step.manDist()
	}

	override fun lastIndexOf(element: P): Int = indexOf(element)

	override fun listIterator(): ListIterator<P> = listIterator(0)

	override fun listIterator(index: Int): ListIterator<P> = listIterator(index.s)
	public fun listIterator(index: Sint): ListIterator<P> = object : ListIterator<P> {
		init {
			checkElementIndex(index, sizeS)
		}

		private var cursor = start + index * step
		private var index = index

		override fun hasNext(): Boolean = index < sizeS
		override fun nextIndex(): Int = index.i
		override fun next(): P {
			val result = this.cursor
			this.cursor += step
			this.index++
			return result
		}

		override fun hasPrevious(): Boolean = index > 0
		override fun previousIndex(): Int = (index - 1).i
		override fun previous(): P {
			this.cursor -= step
			this.index--
			return this.cursor
		}
	}

	override fun subList(fromIndex: Int, toIndex: Int): List<P> = subList(fromIndex.s, toIndex.s)

	public fun subList(fromIndex: Sint, toIndex: Sint): List<P> = this[fromIndex..<toIndex]

	override fun iterator(): Iterator<P> = listIterator()

	override fun spliterator(): Spliterator<P> = super<List>.spliterator()
}

public infix fun <P : PointN<P>> P.toL(other: P): LineN<P> = LineN(this, other)


@JvmName("lineIsHorizontal")
public fun Line.isHorizontal(): Boolean = diff.isHorizontal()

@JvmName("lineIsVertical")
public fun Line.isVertical(): Boolean = diff.isVertical()

@JvmName("lineIsAxisAligned")
public fun Line.isAxisAligned(): Boolean = diff.isAxisAligned()

@JvmName("line3DIsAxisAligned")
public fun Line3D.isAxisAligned(): Boolean = diff.isAxisAligned()