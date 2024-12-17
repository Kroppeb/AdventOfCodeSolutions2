@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d16.c2


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




private fun part1() {
	var inp = getLines(2024, 16)
//	var inp = pre(16, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' } to Clock.east

	dijkstraY(start, { it.first.v == 'E' }) { (p, d) ->
		if (p.offset(d).v != '#') yield(p + d to d to 1.s)
		yield(p to d.rotateClock() to 1000.s)
		yield(p to d.rotateAntiClock() to 1000.s)
	} log 1


}

private fun part2() {
	var inp = getLines(2024, 16)
//	var inp = pre(16, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' } to Clock.east

	val ll = dijkstraYAll(start, { it.first.v == 'E' }) { (p, d) ->
		if (p.offset(d).v != '#') yield(p + d to d to 1.s)
		yield(p to d.rotateClock() to 1000.s)
		yield(p to d.rotateAntiClock() to 1000.s)
	}

	ll log 0

	ll.counts.keys.map{it.first.p}.toSet().size log 2
	ll.getAllPaths().flatten().map{it.first.p}.toSet().size log 2
//	hob.bounds.print { if (it in p) 'O' else (hob[it]) }


}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}
