package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.sint.Sint


public operator fun <T> List<List<T>>.get(first: Int, second: Int): T = this[first][second]
public operator fun <T> List<List<T>>.get(first: Int, second: Sint): T = this[first, second.i]
public operator fun <T> List<List<T>>.get(first: Sint, second: Int): T = this[first.i, second]
public operator fun <T> List<List<T>>.get(first: Sint, second: Sint): T = this[first.i, second.i]

public operator fun <T> List<MutableList<T>>.set(first: Int, second: Int, value: T): T = this[first].set(second, value)
@JvmName("setSintInt")
public operator fun <T> List<MutableList<T>>.set(first: Int, second: Sint, value: T): T =
	this.set(first, second.i, value)

@JvmName("setIntSint")
public operator fun <T> List<MutableList<T>>.set(first: Sint, second: Int, value: T): T =
	this.set(first.i, second, value)

@JvmName("setSintSint")
public operator fun <T> List<MutableList<T>>.set(first: Sint, second: Sint, value: T): T =
	this.set(first.i, second.i, value)

public operator fun <T> List<List<T>>.get(index: Pair<Int, Int>): T = this[index.first, index.second]
@JvmName("getSintInt")
public operator fun <T> List<List<T>>.get(index: Pair<Int, Sint>): T = this[index.first, index.second]
@JvmName("getIntSint")
public operator fun <T> List<List<T>>.get(index: Pair<Sint, Int>): T = this[index.first, index.second]
@JvmName("getSintSint")
public operator fun <T> List<List<T>>.get(index: Pair<Sint, Sint>): T = this[index.first, index.second]

public operator fun <T> List<MutableList<T>>.set(index: Pair<Int, Int>, value: T): T =
	this.set(index.first, index.second, value)

@JvmName("setIntSint")
public operator fun <T> List<MutableList<T>>.set(index: Pair<Int, Sint>, value: T): T =
	this.set(index.first, index.second, value)

@JvmName("setSintInt")
public operator fun <T> List<MutableList<T>>.set(index: Pair<Sint, Int>, value: T): T =
	this.set(index.first, index.second, value)

@JvmName("setSintSint")
public operator fun <T> List<MutableList<T>>.set(index: Pair<Sint, Sint>, value: T): T =
	this.set(index.first, index.second, value)

public fun <T> List<List<T>>.biIndexed(): List<Pair<Pair<Int, Int>, T>> =
	this.mapIndexed { i, row -> row.mapIndexed { j, t -> Pair(i, j) to t } }.flatten()

public fun <T, R> List<List<T>>.map2Indexed(map: (Pair<Int, Int>, T) -> R): List<List<R>> =
	this.mapIndexed { i: Int, row: List<T> ->
		row.mapIndexed { j: Int, t: T ->
			map(i to j, t)
		}
	}

