@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d10c2


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
import me.kroppeb.aoc.helpers.graph.floodFill
import me.kroppeb.aoc.helpers.grid.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 10)
//	var inp = pre(10, 0)
	var hob = inp.digitsI().grid()

	hob.filter { it.v == 0 }.sumOf { start ->
		floodFill(start) { p -> p.getQuadNeighbours().filter { it.v == p.v + 1 } }.count { it.v == 9 }
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 10)
//	var inp = pre(10, 0)
	var hob = inp.digitsI().grid()

	hob.filter { it.v == 0 }.sumOf { start ->
		floodFill(listOf(start)) { p -> p.last().getQuadNeighbours().filter { it.v == p.last().v + 1 }.map{p + listOf(it)} }.count { it.last().v == 9 }
	} log 2
}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}
