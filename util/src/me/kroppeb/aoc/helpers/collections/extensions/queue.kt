package me.kroppeb.aoc.helpers.collections.extensions

public fun <T> dequeOf(vararg items: T): ArrayDeque<T> = ArrayDeque(items.toList())
public fun <T> toDeque(items: Iterable<T>): ArrayDeque<T> = ArrayDeque(items.toList())


public inline fun <T> ArrayDeque<T>.drainFirst(block: (T) -> Unit) {
	while (isNotEmpty()) {
		block(removeFirst())
	}
}

public inline fun <T> ArrayDeque<T>.drainLast(block: (T) -> Unit) {
	while (isNotEmpty()) {
		block(removeLast())
	}
}


public fun interface Yielder<T> {
	public fun yield(value: T): T {
		____yield(value)
		return value
	}

	public fun yieldFrom(values: Iterable<T>) {
		values.forEach { yield(it) }
	}

	public fun yieldAll(vararg values: T) {
		values.forEach { yield(it) }
	}

	public fun ____yield(value: T)
}

public typealias Yr<T> = Yielder<T>.(T) -> Unit

public inline fun <T> withFIFO(start: T, block: Yr<T>) {
	val queue = dequeOf(start)
	queue.drainFirst { item -> block(Yielder { queue.addLast(it) }, item) }
}

public inline fun <T> withLIFO(start: T, block: Yr<T>) {
	val queue = dequeOf(start)
	queue.drainLast { item -> block(Yielder { queue.addLast(it) }, item) }
}

public inline fun <T> withFIFOList(start: Iterable<T>, block: Yr<T>) {
	val queue = toDeque(start)
	queue.drainFirst { item -> block(Yielder { queue.addLast(it) }, item) }
}

public inline fun <T> withLIFOList(start: Iterable<T>, block: Yr<T>) {
	val queue = toDeque(start)
	queue.drainLast { item -> block(Yielder { queue.addLast(it) }, item) }
}