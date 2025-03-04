@file:OptIn(ExperimentalTypeInference::class)

package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import me.kroppeb.aoc.helpers.sint.sumOf
import kotlin.experimental.ExperimentalTypeInference

public object YCombSettings {
	internal var useMemoization = false

	public fun useMemoization(use: Boolean) {
		useMemoization = use
	}
}

public class RetException(public val value: Any?, public val target: Any) : Exception()

public abstract class YCombinatorBase<R> {
	@MarkKeyword
	public fun ret(value: R): Nothing = throw RetException(value, this)

	protected inline fun handle(block: () -> R): R {
		try {
			return block()
		} catch (e: RetException) {
			if (e.target == this) {
				@Suppress("UNCHECKED_CAST") return e.value as R
			} else {
				throw e
			}
		}
	}

	@MarkedC
	public fun uret(value: () -> Any?): R {
		return handle { value() as R }
	}
}


public class YCombinatorX1<T1, R>(f: YCombX1<T1, R>) : YCombinatorBase<R>() {
	public val f: YCombX1<T1, R>

	init {
		if (!YCombSettings.useMemoization) {
			this.f = f
		} else {
			val mem = memoize { t1: T1 -> f(t1) }
			this.f = { t1 -> mem(t1) }
		}
	}


	public operator fun invoke(value: T1): R {
		return handle { f(this, value) }
	}

	@MarkedC
	public fun call(value: T1): R = this(value)

	@MarkedC
	public fun retCall(value: T1): Nothing = ret(this(value))
}

public class YCombinatorX2<T1, T2, R>(f: YCombX2<T1, T2, R>) : YCombinatorBase<R>() {
	public val f: YCombX2<T1, T2, R>

	init {
		if (!YCombSettings.useMemoization) {
			this.f = f
		} else {
			val mem = memoize { t1: T1, t2: T2 ->
				f(t1, t2)
			}
			this.f = { t1, t2 -> mem(t1, t2) }
		}
	}

	public operator fun invoke(value1: T1, value2: T2): R {
		return handle { f(this, value1, value2) }
	}

	@MarkedC
	public fun call(value1: T1, value2: T2): R = this(value1, value2)

	@MarkedC
	public fun retCall(value1: T1, value2: T2): Nothing = ret(this(value1, value2))
}

public class YCombinatorX3<T1, T2, T3, R>(f: YCombX3<T1, T2, T3, R>) : YCombinatorBase<R>() {
	public val f: YCombX3<T1, T2, T3, R>

	init {
		if (!YCombSettings.useMemoization) {
			this.f = f
		} else {
			val mem = memoize { t1: T1, t2: T2, t3: T3 ->
				f(t1, t2, t3)
			}
			this.f = { t1, t2, t3 -> mem(t1, t2, t3) }
		}
	}

	public operator fun invoke(value1: T1, value2: T2, value3: T3): R {
		return handle { f(this, value1, value2, value3) }
	}

	@MarkedC
	public fun call(value1: T1, value2: T2, value3: T3): R = this(value1, value2, value3)

	@MarkedC
	public fun retCall(value1: T1, value2: T2, value3: T3): Nothing = ret(this(value1, value2, value3))
}

public class YCombinatorX4<T1, T2, T3, T4, R>(f: YCombX4<T1, T2, T3, T4, R>) : YCombinatorBase<R>() {
	public val f: YCombX4<T1, T2, T3, T4, R>

	init {
		if (!YCombSettings.useMemoization) {
			this.f = f
		} else {
			val mem = memoize { t1: T1, t2: T2, t3: T3, t4: T4 ->
				f(t1, t2, t3, t4)
			}
			this.f = { t1, t2, t3, t4 -> mem(t1, t2, t3, t4) }
		}
	}

	public operator fun invoke(value1: T1, value2: T2, value3: T3, value4: T4): R {
		return handle { f(this, value1, value2, value3, value4) }
	}

	@MarkedC
	public fun call(value1: T1, value2: T2, value3: T3, value4: T4): R = this(value1, value2, value3, value4)

	@MarkedC
	public fun retCall(value1: T1, value2: T2, value3: T3, value4: T4): Nothing = ret(this(value1, value2, value3, value4))
}

public class YCombinatorX5<T1, T2, T3, T4, T5, R>(f: YCombX5<T1, T2, T3, T4, T5, R>) : YCombinatorBase<R>() {
	public val f: YCombX5<T1, T2, T3, T4, T5, R>

	init {
		if (!YCombSettings.useMemoization) {
			this.f = f
		} else {
			val mem = memoize { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 ->
				f(t1, t2, t3, t4, t5)
			}
			this.f = { t1, t2, t3, t4, t5 -> mem(t1, t2, t3, t4, t5) }
		}
	}

	public operator fun invoke(value1: T1, value2: T2, value3: T3, value4: T4, value5: T5): R {
		return handle { f(this, value1, value2, value3, value4, value5) }
	}

	@MarkedC
	public fun call(value1: T1, value2: T2, value3: T3, value4: T4, value5: T5): R =
		this(value1, value2, value3, value4, value5)

	@MarkedC
	public fun retCall(value1: T1, value2: T2, value3: T3, value4: T4, value5: T5): Nothing =
		ret(this(value1, value2, value3, value4, value5))
}


public typealias YCombX1<T1, R> = YCombinatorX1<T1, R>.(T1) -> R
public typealias YCombX2<T1, T2, R> = YCombinatorX2<T1, T2, R>.(T1, T2) -> R
public typealias YCombX3<T1, T2, T3, R> = YCombinatorX3<T1, T2, T3, R>.(T1, T2, T3) -> R
public typealias YCombX4<T1, T2, T3, T4, R> = YCombinatorX4<T1, T2, T3, T4, R>.(T1, T2, T3, T4) -> R
public typealias YCombX5<T1, T2, T3, T4, T5, R> = YCombinatorX5<T1, T2, T3, T4, T5, R>.(T1, T2, T3, T4, T5) -> R


public fun <T1, R> yComb(value: T1, @BuilderInference f: YCombX1<T1, R>): R = YCombinatorX1(f).call(value)
public fun <T1, T2, R> yComb(value1: T1, value2: T2, @BuilderInference f: YCombX2<T1, T2, R>): R =
	YCombinatorX2(f).call(value1, value2)

public fun <T1, T2, T3, R> yComb(value1: T1, value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, R>): R =
	YCombinatorX3(f).call(value1, value2, value3)

public fun <T1, T2, T3, T4, R> yComb(
	value1: T1, value2: T2, value3: T3, value4: T4,
	@BuilderInference f: YCombX4<T1, T2, T3, T4, R>
): R = YCombinatorX4(f).call(value1, value2, value3, value4)

public fun <T1, T2, T3, T4, T5, R> yComb(
	value1: T1, value2: T2, value3: T3, value4: T4, value5: T5,
	@BuilderInference f: YCombX5<T1, T2, T3, T4, T5, R>
): R = YCombinatorX5(f).call(value1, value2, value3, value4, value5)

@JvmName("YCombReceiver")
public fun <T1, R> T1.yComb(
	@BuilderInference f: YCombX1<T1, R>
): R = yComb(this, f)

@JvmName("YCombReceiver")
public fun <T1, T2, R> T1.yComb(
	value2: T2, @BuilderInference f: YCombX2<T1, T2, R>
): R = yComb(this, value2, f)

@JvmName("YCombReceiver")
public fun <T1, T2, T3, R> T1.yComb(
	value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, R>
): R = yComb(this, value2, value3, f)

@JvmName("YCombReceiver")
public fun <T1, T2, T3, T4, R> T1.yComb(
	value2: T2, value3: T3, value4: T4, @BuilderInference f: YCombX4<T1, T2, T3, T4, R>
): R = yComb(this, value2, value3, value4, f)

@JvmName("YCombReceiver")
public fun <T1, T2, T3, T4, T5, R> T1.yComb(
	value2: T2, value3: T3, value4: T4, value5: T5, @BuilderInference f: YCombX5<T1, T2, T3, T4, T5, R>
): R = yComb(this, value2, value3, value4, value5, f)


public fun <T1, R> Iterable<T1>.mapY(@BuilderInference f: YCombX1<T1, R>): List<R> = map { yComb(it, f) }
// TODO: Needs to be more cursed
public fun <T1, R> Iterable<T1>.flatMapY(@BuilderInference f: YCombX1<T1, List<R>>): List<R> = flatMap { yComb(it, f) }
public fun <T1> Iterable<T1>.filterY(@BuilderInference f: YCombX1<T1, Boolean>): List<T1> = filter { yComb(it, f) }
public fun <T1> Iterable<T1>.sumOfY(@BuilderInference f: YCombX1<T1, Sint>): Sint = sumOf { yComb(it, f) }
public fun <T1, C : Comparable<C>> Iterable<T1>.maxByY(@BuilderInference f: YCombX1<T1, C>): T1 = maxBy { yComb(it, f) }
public fun <T1, C : Comparable<C>> Iterable<T1>.minByY(@BuilderInference f: YCombX1<T1, C>): T1 = minBy { yComb(it, f) }
public fun <T1, C : Comparable<C>> Iterable<T1>.maxOfY(@BuilderInference f: YCombX1<T1, C>): C = maxOf { yComb(it, f) }
public fun <T1, C : Comparable<C>> Iterable<T1>.minOfY(@BuilderInference f: YCombX1<T1, C>): C = minOf { yComb(it, f) }
public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxByY(@BuilderInference f: YCombX1<T1, C>): List<T1> =
	allMaxBy { yComb(it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMinByY(@BuilderInference f: YCombX1<T1, C>): List<T1> =
	allMinBy { yComb(it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxOfY(@BuilderInference f: YCombX1<T1, C>): List<C> =
	allMaxOf { yComb(it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMinOfY(@BuilderInference f: YCombX1<T1, C>): List<C> =
	allMinOf { yComb(it, f) }

public fun <T1, R> Iterable<T1>.mapIndexedY(@BuilderInference f: YCombX2<Sint, T1, R>): List<R> = mapIndexed { i, it -> yComb(i.s, it, f) }
// TODO: Needs to be more cursed
public fun <T1, R> Iterable<T1>.flatMapIndexedY(@BuilderInference f: YCombX2<Sint, T1, List<R>>): List<R> = flatMapIndexed { i, it -> yComb(i.s, it, f) }
public fun <T1, R> Iterable<T1>.filterIndexedY(@BuilderInference f: YCombX2<Sint, T1, Boolean>): List<T1> = filterIndexed { i, it -> yComb(i.s, it, f) }
public fun <T1> Iterable<T1>.sumIndexedOfY(f: YCombX2<Sint, T1, Sint>): Sint =
	withIndex().sumOf { (i, it) -> yComb(i.s, it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.maxByIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): T1 =
	withIndex().maxBy { (i, it) -> yComb(i.s, it, f) }.value

public fun <T1, C : Comparable<C>> Iterable<T1>.minByIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): T1 =
	withIndex().minBy { (i, it) -> yComb(i.s, it, f) }.value

public fun <T1, C : Comparable<C>> Iterable<T1>.maxOfIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): C =
	withIndex().maxOf { (i, it) -> yComb(i.s, it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.minOfIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): C =
	withIndex().minOf { (i, it) -> yComb(i.s, it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxByIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): List<T1> =
	withIndex().allMaxBy { (i, it) -> yComb(i.s, it, f) }.map { it.value }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMinByIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): List<T1> =
	withIndex().allMinBy { (i, it) -> yComb(i.s, it, f) }.map { it.value }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxOfIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): List<C> =
	withIndex().allMaxOf { (i, it) -> yComb(i.s, it, f) }

public fun <T1, C : Comparable<C>> Iterable<T1>.allMinOfIndexedY(@BuilderInference f: YCombX2<Sint, T1, C>): List<C> =
	withIndex().allMinOf { (i, it) -> yComb(i.s, it, f) }


public fun <T1, T2, R> Iterable<T1>.mapY(value2: T2, @BuilderInference f: YCombX2<T1, T2, R>): List<R> = map { yComb(it, value2, f) }
public fun <T1, T2, R> Iterable<T1>.flatMapY(value2: T2, @BuilderInference f: YCombX2<T1, T2, List<R>>): List<R> = flatMap { yComb(it, value2, f) }
public fun <T1, T2> Iterable<T1>.sumOfY(value2: T2, @BuilderInference f: YCombX2<T1, T2, Sint>): Sint = sumOf { yComb(it, value2, f) }
public fun <T1, T2> Iterable<T1>.filterY(value2: T2, @BuilderInference f: YCombX2<T1, T2, Boolean>): List<T1> = filter { yComb(it, value2, f) }
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.maxByY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): T1 =
	maxBy { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.minByY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): T1 =
	minBy { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.maxOfY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): C =
	maxOf { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.minOfY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): C =
	minOf { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMaxByY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): List<T1> =
	allMaxBy { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMinByY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): List<T1> =
	allMinBy { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMaxOfY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): List<C> =
	allMaxOf { yComb(it, value2, f) }

public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMinOfY(value2: T2, @BuilderInference f: YCombX2<T1, T2, C>): List<C> =
	allMinOf { yComb(it, value2, f) }

public fun <T1, T2, T3, R> Iterable<T1>.mapY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, R>): List<R> =
	map { yComb(it, value2, value3, f) }
// TODO: needs to be more cursed
public fun <T1, T2, T3, R> Iterable<T1>.flatMapY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, List<R>>): List<R> =
	flatMap { yComb(it, value2, value3, f) }
public fun <T1, T2, T3> Iterable<T1>.filterY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, Boolean>): List<T1> =
	filter { yComb(it, value2, value3, f) }

public fun <T1, T2, T3> Iterable<T1>.sumOfY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, Sint>): Sint =
	sumOf { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.maxByY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>): T1 =
	maxBy { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.minByY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>): T1 =
	minBy { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.maxOfY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>): C =
	maxOf { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.minOfY(value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>): C =
	minOf { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.allMaxByY(
	value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>
): List<T1> = allMaxBy { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.allMinByY(
	value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>
): List<T1> = allMinBy { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.allMaxOfY(
	value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>
): List<C> = allMaxOf { yComb(it, value2, value3, f) }

public fun <T1, T2, T3, C : Comparable<C>> Iterable<T1>.allMinOfY(
	value2: T2, value3: T3, @BuilderInference f: YCombX3<T1, T2, T3, C>
): List<C> = allMinOf { yComb(it, value2, value3, f) }


public fun <T1, T2, R> Iterable<T1>.zipY(value2: Iterable<T2>, @BuilderInference f: YCombX2<T1, T2, R>): List<R> =
	zip(value2) { a, b -> yComb(a, b, f) }


context(YCombinatorX1<T1, R>)
@MarkedC
public fun <T1, R> Iterable<T1>.mapC(): List<R> = map { call(it) }

context(YCombinatorX1<T1, List<R>>)
@MarkedC
public fun <T1, R> Iterable<T1>.flatMapC(): List<R> = flatMap { call(it) }

context(YCombinatorX1<T1, Boolean>)
@MarkedC
public fun <T1> Iterable<T1>.filterC(): List<T1> = filter { call(it) }

context(YCombinatorX1<T1, Sint>)
@MarkedC
public fun <T1> Iterable<T1>.sumOfC(): Sint = sumOf { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.maxByC(): T1 = maxBy { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.minByC(): T1 = minBy { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.maxOfC(): C = maxOf { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.minOfC(): C = minOf { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxByC(): List<T1> = allMaxBy { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.allMinByC(): List<T1> = allMinBy { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.allMaxOfC(): List<C> = allMaxOf { call(it) }

context(YCombinatorX1<T1, C>)
@MarkedC
public fun <T1, C : Comparable<C>> Iterable<T1>.allMinOfC(): List<C> = allMinOf { call(it) }


context(YCombinatorX2<T1, T2, R>)
@MarkedC
public fun <T1, T2, R> Iterable<T1>.mapC(value2: T2): List<R> = map { call(it, value2) }

context(YCombinatorX2<T1, T2, Sint>)
@MarkedC
public fun <T1, T2> Iterable<T1>.sumOfC(value2: T2): Sint = sumOf { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.maxByC(value2: T2): T1 = maxBy { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.minByC(value2: T2): T1 = minBy { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.maxOfC(value2: T2): C = maxOf { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.minOfC(value2: T2): C = minOf { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMaxByC(value2: T2): List<T1> = allMaxBy { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMinByC(value2: T2): List<T1> = allMinBy { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMaxOfC(value2: T2): List<C> = allMaxOf { call(it, value2) }

context(YCombinatorX2<T1, T2, C>)
@MarkedC
public fun <T1, T2, C : Comparable<C>> Iterable<T1>.allMinOfC(value2: T2): List<C> = allMinOf { call(it, value2) }