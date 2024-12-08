@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d08.c1


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


import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 8)
//	inp = pre(8, 0)

	val g = inp.grid()
	val gs = g.groupBy { it.v }.filterKeys { it != '.' }

	gs.values.flatMap { v ->
		v.pairWise().flatMap { (a, b) ->
			val d = b - a
			listOfNotNull(
				a.offsetOrNull(-d),
				b.offsetOrNull(d)
			)
		}
	}.toSet().size log 1
}

private fun part2() {
	var inp = getLines(2024, 8)
//	inp = pre(8, 0)

	val g = inp.grid()
	val gs = g.groupBy { it.v }.filterKeys { it != '.' }


	gs.values.flatMap { v ->
		v.pairWise().flatMap { (a, b) ->
			val d = b - a
			a.p..b.p
			a.sequenceInc(-d) + b.sequenceInc(d)
		}
	}.toSet().size log 2
}


private fun oneliners() {
	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> listOfNotNull(a.offsetOrNull(a - b), b.offsetOrNull(b - a)) } }.toSet().size log 1
	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> a.sequenceInc(a-b) + a.sequenceInc(b-a) } }.toSet().size log 2
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}
