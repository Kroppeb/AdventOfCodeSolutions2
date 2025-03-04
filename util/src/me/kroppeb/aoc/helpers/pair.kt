package me.kroppeb.aoc.helpers

public inline fun <A, B> Pair<A, A>.map(mapping: (A) -> B): Pair<B, B> = mapping(first) to mapping(second)

public inline fun <A, B, R> Pair<A, B>.on(block: (A, B) -> R): R = block(first, second)

