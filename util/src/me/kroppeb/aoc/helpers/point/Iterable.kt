@file:OptIn(ExperimentalTypeInference::class)

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.scan
import kotlin.experimental.ExperimentalTypeInference

public fun <P: PointN<P>> Iterable<P>.sum(): P = reduce { a, b -> a + b }

@OverloadResolutionByLambdaReturnType
public fun <R, P: PointN<P>> Iterable<R>.sumOf(selector: (R) -> P): P = map(selector).sum()

public fun <T: PointN<T>>  Iterable<T>.cumSum(): List<T> = scan { a, b -> a + b }

@OverloadResolutionByLambdaReturnType
public fun <R, T: PointN<T>>  Iterable<R>.cumSumOf(selector: (R) -> T): List<T> = map(selector).cumSum()

public fun <T: PointN<T>>  Iterable<T>.cumSum(initial:T): List<T> = scan(initial) { a, b -> a + b }

@OverloadResolutionByLambdaReturnType
public fun <R, T: PointN<T>>  Iterable<R>.cumSumOf(initial:T, selector: (R) -> T): List<T> = map(selector).cumSum(initial)



