package me.kroppeb.aoc.helpers.collections.extensions

fun <T> dequeOf(vararg items: T): ArrayDeque<T> = ArrayDeque(items.toList())
fun <T> toDeque(items: Iterable<T>): ArrayDeque<T> = ArrayDeque(items.toList())


inline fun <T> ArrayDeque<T>.drainFirst(block: (T) -> Unit) {
	while (isNotEmpty()) {
		block(removeFirst())
	}
}

inline fun <T> ArrayDeque<T>.drainLast(block: (T) -> Unit) {
	while (isNotEmpty()) {
		block(removeLast())
	}
}