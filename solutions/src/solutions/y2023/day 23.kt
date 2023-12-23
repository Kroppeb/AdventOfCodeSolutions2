@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d23


/*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.ArrayDeque
import java.util.PriorityQueue
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import log
import java.util.Comparator
import java.util.ArrayDeque
import java.util.PriorityQueue
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*


private val xxxxx = Clock(6, 3)
private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var data = getLines(23).grid()

	val start = data.getBp(0 toP 1)


	val seen = mutableSetOf<Het2<Set<BGPC>, BGPC>>()
	var best = 0
	withFIFO(setOf(start) toH start){(seen, curr) ->
		if (curr.p.x == data.bounds.xs.last()) {
			best = maxOf(best, seen.size)
		} else {
			curr.getQuadNeighbours().filter{it !in seen}.filter{it.v != '#'}.filter{if(it.v == '.') true else {
				when(it.v) {
					'<' -> it.p.isLeftOf(curr.p)
					'>' -> it.p.isRightOf(curr.p)
					'v' -> it.p.isBelow(curr.p)
					'^' -> it.p.isAbove(curr.p)
					else -> error(it)
				}
			} }.map{yield(seen+  it toH it)}
		}
	}
	best - 1 log 1
}
private fun part2() {
	var data = getLines(2023, 23).grid()
	val dd = data.asQuadGraph { a, b -> a.v != '#' && b.v != '#' }

//	val d = dd.roi {
//		it.neighbours.size != 2
//	}

	val neigh = dd.neighbours.mapValues { it.value.toMutableMap() }.toMutableMap()

	for ((k,v ) in neigh) {
		if (v.size != 2) {
			continue
		}

		for ((kv, vv) in v) {
			val p = neigh[kv]!!
			p.remove(k)
			val oo = v.keys.single{it != kv}
			p[oo] = v.values.sum()
		}
		v.clear()
	}

	val d = CGraph(neigh.filterValues { it.isNotEmpty() })
	dd.nodes.size log 0
	d.nodes.size log 0

	val start = d.nodes.single { it.value.p == 0 toP 1 }
	start.neighbours log 0

	val sn = setTT(setOf(start) toH start toH 0.s)
	var best = 0.s
	withLIFO(setOf(start) toH start toH 0.s) { (seen, curr, cost) ->
//		cost log 0
//		seen.size log 0
		if (curr.value.p.x == data.bounds.xs.last()) {
			best = maxOf(best, cost)
		} else {
			curr.neighbours.filter { it.first !in seen }.map {
				val p = seen + it.first toH it.first toH cost + it.second
				if (p !in sn) {
//					sn += p
					yield(p)
				}
			}
		}
	}
	best log 1
}


fun main() {
	println("Day 23: ")
	part1()
	part2()
}
