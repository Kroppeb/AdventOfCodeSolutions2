@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d18


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


import itertools.*
import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import java.util.Comparator
import java.util.ArrayDeque
import java.util.PriorityQueue
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.math.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.system.exitProcess
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines("ec", 2025, 1, 1)

	var hob = inp.splitOnEmpty()
	val plants = hob.associate{d ->
		val (id, t) = d.first().sints()
		var conns = d.b().sints()
		if (conns.size == 1 && conns[0].size == 1) {
			id to (t toH listOf(0.s to 1.s))
		} else {
			id to (t toH conns.map{(a,b) -> a to b})
		}
	}


	val scores = mutableMapOf(0.s to 1.s)
	val outs = mutableMapOf<Sint, MutableList<Sint>>()

	plants.forEach { t, u ->
		u.b.forEach { (id, _) ->
			outs.getOrPut(id){ mutableListOf()}.add(t)
		}
	}
	outs log 0
	val updates = toDeque(outs[0.s]!!)

	while (updates.size > 0) {
		updates log 0
		val u = updates.removeFirst()

		val (t, ll) = plants[u]!!
		val s = ll.sumOf { (p, pt) -> (scores[p] ?: 0.s) * pt }
		if (s >= t) {
			val oldScore = scores[u]
			scores[u] = s
			if (oldScore != s) {
				updates.addAll(outs[t]?: listOf()) log 0
			}
		}
	}

//	val oo = plants.keys.filter{ (outs[it] ?: listOf()).size == 0 && plants[it]!!.b} log 0
	val oo = 19.s
	scores log 0

	val (t, ll) = plants[19.s]!!
	ll.sumOf { (p, pt) -> (scores[p] ?: 0.s) * pt } log 1
}

private fun part2() {
	var inp = getLines("ec", 2025, 1, 2)

	var (hob, tests) = inp.splitOnEmpty().splitOn { it.isEmpty() }
	tests log 0
	val plants = hob.associate{d ->
		val (id, t) = d.first().sints()
		var conns = d.b().sints()
		if (conns.size == 1 && conns[0].size == 1) {
			id to (t toH listOf(0.s to 1.s))
		} else {
			id to (t toH conns.map{(a,b) -> a to b})
		}
	}
	val starts = plants.filter { it.value.b.all{it.first == 0.s} }.map{it.key}.sorted()

	fun doTest(test: List<Sint>): Sint {
		val scores = mutableMapOf(0.s to 1.s)
		val outs = mutableMapOf<Sint, MutableList<Sint>>()

		plants.forEach { t, u ->
			u.b.forEach { (id, _) ->
				outs.getOrPut(id) { mutableListOf() }.add(t)
			}
		}
		outs log 0
		val updates = toDeque(test)

		while (updates.size > 0) {
			val u = updates.removeFirst()

			val (t, ll) = plants[u]!!
			val s = ll.sumOf { (p, pt) -> (scores[p] ?: 0.s) * pt }
			if (s >= t) {
				val oldScore = scores[u]
				scores[u] = s
				if (oldScore != s) {
					updates.addAll(outs[u] ?: listOf())
				}
			} else {
				val oldScore = scores[u]
				scores.remove(u)
				if (oldScore != null) {
					updates.addAll(outs[u] ?: listOf())
				}
			}
		}

		val oo = plants.keys.single { (outs[it] ?: listOf()).size == 0 && plants[it]!!.b.none { it.first == 0.s } } log 0

		scores log 0

		return scores[oo]?:0.s log "res"
	}

	val tt = tests.single()
	tt.map{test -> test.sints().zip(starts).filter{it.first == 1.s}.map{it.second}.let{doTest(it)}}.sum() log 2
}

private fun part3() {
	var inp = getLines("ec", 2025, 1, 3)

	var (hob, tests) = inp.splitOnEmpty().splitOn { it.isEmpty() }
	tests log 0
	val plants = hob.associate{d ->
		val (id, t) = d.first().sints()
		var conns = d.b().sints()
		if (conns.size == 1 && conns[0].size == 1) {
			id to (t toH listOf(0.s to 1.s))
		} else {
			id to (t toH conns.map{(a,b) -> a to b})
		}
	}
	var starts = plants.filter { it.value.b.all{it.first == 0.s} }.map{it.key}.sorted()

	val outs = mutableMapOf<Sint, MutableList<Sint>>()

	plants.forEach { t, u ->
		u.b.forEach { (id, _) ->
			outs.getOrPut(id) { mutableListOf() }.add(t)
		}
	}
	outs log 0
	val oo = plants.keys.single { (outs[it] ?: listOf()).size == 0 && plants[it]!!.b.none { it.first == 0.s } } log 0


	val forceStarts = (1..81.s).filter { i ->
		outs[i]!!.map{p -> plants[p]!!.b.single { it.first == i }.second}.all { it > 0.s }
	} log "force"
	val maybeStarts = (1..81.s).filter { i ->
		val r =outs[i]!!.map{p -> plants[p]!!.b.single { it.first == i }.second}
		r.any { it > 0 } && r.any { it < 0 }
	} log "maybe"




	fun doTest(test: List<Sint>): Sint {
		test log "start"
		val scores = mutableMapOf(0.s to 1.s)

		val updates = toDeque(test)

		while (updates.size > 0) {
			val u = updates.removeFirst()

			val (t, ll) = plants[u]!!
			val s = ll.sumOf { (p, pt) -> (scores[p] ?: 0.s) * pt }
			if (s >= t) {
				val oldScore = scores[u]
				scores[u] = s
				if (oldScore != s) {
					updates.addAll(outs[u] ?: listOf())
				}
			} else {
				val oldScore = scores[u]
				scores.remove(u)
				if (oldScore != null) {
					updates.addAll(outs[u] ?: listOf())
				}
			}
		}



		scores log 0

		return (scores[oo]?:0.s) log "res"
	}

	val best = (0..maybeStarts.size.s).flatMap { maybeStarts.subSetsWithLength(it) }.map{
		doTest(it + forceStarts)
	}.max() log "best"

	val tt = tests.single()
	tt.map{test -> test.sints().zip(starts).filter{it.first == 1.s}.map{it.second}.let{doTest(it)}}.map{
		if(it == 0.s) 0.s
		else best - it
	}.sum() log 3
}

fun main() {
	println("Day 18: ")
	part1()
	part2()
	part3()
}
