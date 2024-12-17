package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.collections.list.*


public inline fun <R, T> wrap(cache: MMap<T, Pair<Boolean, Any?>>, t: T, f: () -> R): R {
	val res = cache.getOrPut(t) {
		try {
			true to f()
		} catch (e: Exception) {
			false to e
		}
	}
	if (res.first) {
		return res.second as R
	} else {
		throw res.second as Exception
	}
}


public inline fun <T, R> memoize(crossinline f: (T) -> R): (T) -> R {
	val cache = mutableMapOf<T, Pair<Boolean, Any?>>()
	return { t: T -> wrap(cache, t) { f(t) } }
}

public inline fun <T1, T2, R> memoize(crossinline f: (T1, T2) -> R): (T1, T2) -> R {
	val cache = mutableMapOf<Het2<T1, T2>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2 ->
		wrap(cache, t1 toH t2) { f(t1, t2) }
	}
}

public inline fun <T1, T2, T3, R> memoize(crossinline f: (T1, T2, T3) -> R): (T1, T2, T3) -> R {
	val cache = mutableMapOf<Het3<T1, T2, T3>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3 ->
		wrap(cache, t1 toH t2 toH t3) { f(t1, t2, t3) }
	}
}

public inline fun <T1, T2, T3, T4, R> memoize(crossinline f: (T1, T2, T3, T4) -> R): (T1, T2, T3, T4) -> R {
	val cache = mutableMapOf<Het4<T1, T2, T3, T4>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4 ->
		wrap(cache, t1 toH t2 toH t3 toH t4) { f(t1, t2, t3, t4) }
	}
}

public inline fun <T1, T2, T3, T4, T5, R> memoize(crossinline f: (T1, T2, T3, T4, T5) -> R): (T1, T2, T3, T4, T5) -> R {
	val cache = mutableMapOf<Het5<T1, T2, T3, T4, T5>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5) { f(t1, t2, t3, t4, t5) }
	}
}

public inline fun <T1, T2, T3, T4, T5, T6, R> memoize(crossinline f: (T1, T2, T3, T4, T5, T6) -> R): (T1, T2, T3, T4, T5, T6) -> R {
	val cache = mutableMapOf<Het6<T1, T2, T3, T4, T5, T6>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5 toH t6) { f(t1, t2, t3, t4, t5, t6) }
	}
}

public inline fun <T1, T2, T3, T4, T5, T6, T7, R> memoize(crossinline f: (T1, T2, T3, T4, T5, T6, T7) -> R): (T1, T2, T3, T4, T5, T6, T7) -> R {
	val cache = mutableMapOf<Het7<T1, T2, T3, T4, T5, T6, T7>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5 toH t6 toH t7) { f(t1, t2, t3, t4, t5, t6, t7) }
	}
}

public inline fun <T1, T2, T3, T4, T5, T6, T7, T8, R> memoize(crossinline f: (T1, T2, T3, T4, T5, T6, T7, T8) -> R): (T1, T2, T3, T4, T5, T6, T7, T8) -> R {
	val cache = mutableMapOf<Het8<T1, T2, T3, T4, T5, T6, T7, T8>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5 toH t6 toH t7 toH t8) { f(t1, t2, t3, t4, t5, t6, t7, t8) }
	}
}

public inline fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> memoize(crossinline f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R): (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R {
	val cache = mutableMapOf<Het9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5 toH t6 toH t7 toH t8 toH t9) { f(t1, t2, t3, t4, t5, t6, t7, t8, t9) }
	}
}

public inline fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> memoize(crossinline f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R {
	val cache = mutableMapOf<Het10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>, Pair<Boolean, Any?>>()
	return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9, t10: T10 ->
		wrap(cache, t1 toH t2 toH t3 toH t4 toH t5 toH t6 toH t7 toH t8 toH t9 toH t10) { f(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) }
	}
}