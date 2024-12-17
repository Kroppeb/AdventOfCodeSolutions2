@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d16


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


import itertools.repeat
import itertools.s
import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.graph.dijkstra
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import java.util.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.math.round
import kotlin.math.roundToLong


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


inline fun <S, State:List<S>> dijkstraLL(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): MutableList<DijkstraResult<State>> {
	val costs = mutableMapOf(start.last() to 0.s)
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += start to 0.s
	val back = mutableMapOf<State, State>()
	var knowS: Sint? = null
	val res = mutableListOf<DijkstraResult<State>>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current.last()] != currentCost) continue
		if (knowS != null && currentCost > knowS) continue
		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			knowS = currentCost
			res.add(DijkstraResult(path, currentCost))
		}

		for ((next, cost) in next(current)) {
			val newCost = currentCost + cost
			val previousCost = costs[next.last()]
			if (previousCost == null || newCost <= previousCost) {
				costs[next.last()] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return res
}



private fun part1() {
	var inp = getLines(2024, 16)
//	var inp = pre(16, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' } to Clock.east

	dijkstra(start, { it.first.v == 'E' }) { (p, d) ->
		listOf(
			p + d to d to 1.s,
			p to d.rotateClock() to 1000.s,
			p to d.rotateAntiClock() to 1000.s
		).filter { (a, _) -> a.first.v != '#' }
	} log 1


}

private fun part2() {
	var inp = getLines(2024, 16)
//	var inp = pre(16, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' } to Clock.east

	val ll = dijkstraLL(listOf(start), { it.last().first.v == 'E' }) { l ->
		val (p, d) = l.last()
		listOf(
			p + d to d to 1.s,
			p to d.rotateClock() to 1000.s,
			p to d.rotateAntiClock() to 1000.s
		).filter { (a, _) -> a.first.v != '#' }.map{(it, c) -> l + listOf(it) to c}
	}

	ll.size log 0


	val p = ll.flatMap{it.path.first().map{it.first.p}}.toSet()

//	hob.bounds.print { if (it in p) 'O' else (hob[it]) }

	p.size log 2


}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}
