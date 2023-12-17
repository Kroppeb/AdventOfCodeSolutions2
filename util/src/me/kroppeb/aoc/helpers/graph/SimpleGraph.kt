@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.graph

// no double edges, bidirectional
public class SimpleGraph<T> {
	public val neighbours: MutableMap<T, MutableMap<T, Double>> = mutableMapOf()
	public fun connect(a: T, b: T, weight:Double = 1.0) {
		neighbours.getOrPut(a) { mutableMapOf() }[b] = weight
		neighbours.getOrPut(b) { mutableMapOf() }[a] = weight
	}

	public fun neighboursOf(node: T) = neighbours[node] ?: emptyMap()
}