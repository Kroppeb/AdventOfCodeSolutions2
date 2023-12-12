package me.kroppeb.aoc.helpers.collections.list

import iterators.repeatingIterator
import me.kroppeb.aoc.helpers.mod

class CyclicList<out T>(private val inner:List<T>) : List<T> , InfiniteList<T> {
	override val size: Int
		get() =inner.size



	override fun get(index: Int): T = inner[index mod inner.size]

	override fun iterator() = inner.repeatingIterator()

	override fun contains(element: @UnsafeVariance T): Boolean = inner.contains(element)

	override fun containsAll(elements: Collection<@UnsafeVariance T>): Boolean = inner.containsAll(elements)

	override fun indexOf(element: @UnsafeVariance T): Int = inner.indexOf(element)

	override fun isEmpty(): Boolean = inner.isEmpty()

	override fun lastIndexOf(element: @UnsafeVariance T): Int = inner.lastIndexOf(element)

	override fun listIterator(): ListIterator<T> = listIterator(0)

	override fun listIterator(index: Int): ListIterator<T> {
		TODO("Not yet implemented")
	}

	override fun subList(fromIndex: Int, toIndex: Int): List<T> {
		val start = fromIndex mod inner.size
		val end = toIndex mod inner.size
		return if (start < end) {
			inner.subList(start, end)
		} else {
			inner.subList(start, inner.size) + inner.subList(0, end)
		}
	}
}

fun <T>List<T>.cyclic():CyclicList<T> = CyclicList(this)