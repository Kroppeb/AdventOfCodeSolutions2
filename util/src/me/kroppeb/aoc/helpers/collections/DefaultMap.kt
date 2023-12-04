package me.kroppeb.aoc.helpers.collections

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

class DefaultMap<K, V> constructor(val default: (K) -> V, val map: MutableMap<K, V>) : MutableMap<K, V> by map {
	override fun get(key: K): V = map[key] ?: default(key).also{map[key] = it}
}

fun <K, V> defaultMapOf(default: (K) -> V) = DefaultMap<K, V>(default, mutableMapOf())
fun <K, V> Map<K, V>.default(default: (K) -> V) = DefaultMap(default, this.toMutableMap())
fun <K, V> Map<K, MutableList<V>>.default(): DefaultMap<K, MutableList<V>> = default { mutableListOf() }
fun <K, V> Map<K, MutableSet<V>>.default(): DefaultMap<K, MutableSet<V>> = default { mutableSetOf() }
fun <K> Map<K, Int>.default(value:Int = 0): DefaultMap<K, Int> = default { value }
fun <K> Map<K, Long>.default(value:Long = 0L): DefaultMap<K, Long> = default { value }
fun <K> Map<K, Sint>.default(value: Sint = Sint.ZERO): DefaultMap<K, Sint> = default { value }