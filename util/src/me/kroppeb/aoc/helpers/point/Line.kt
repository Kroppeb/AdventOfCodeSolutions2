@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.*

public typealias Line = LineN<Point>
public typealias Line3D = LineN<Point3D>

public data class LineN<P : PointN<P>>(val start: P, val end: P) : Collection<P> {
	public val diff: P = end - start;
	public val step: P = diff.discreteAngle()
	public val length: Sint = if (step.manDist().isZero()) Sint.ZERO else diff.manDist() / step.manDist()
	override val size: Int = (length + 1).i

	override fun toString(): String {
		return "LineN(start=$start, end=$end, diff=$diff, step=$step, length=$length)"
	}

	override operator fun contains(element: P): Boolean = when (element) {
		start -> true
		end -> true
		// if it's on the extension of the line, but not on the line itself
		//  then one of these 2 discrete angles will be the opposite of the step
		else -> (element - start).discreteAngle() == step &&
			(end - element).discreteAngle() == step
	}

	override fun containsAll(elements: Collection<P>): Boolean = elements.all { it in this }

	override fun isEmpty(): Boolean = false

	override fun iterator(): Iterator<P> = object : Iterator<P> {
		private var prev: P? = null
		private var next = start

		override fun hasNext(): Boolean = this.prev != end
		override fun next(): P {
			val result = this.next
			this.prev = result
			this.next += step
			return result
		}
	}
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