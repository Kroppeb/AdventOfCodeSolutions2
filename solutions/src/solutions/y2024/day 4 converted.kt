@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d04c1


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

//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 4).e().grid().sumOf {
		Point.ZERO.getOctNeighbours().count { step -> it.sequenceInc(step).take(4).map { it.v } == "XMAS".e() }
	} log 1
}

private fun part1Early() {
	var inp = getLines(2024, 4).e().grid()
	inp.map { it.p }.sumOf {
		Point.ZERO.getOctNeighbours()
			.count { step -> it.sequenceInc(step).take(4).map { inp.getBpOrNull(it)?.v }.toList() == "XMAS".e() }
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 4).e().grid().count { c ->
		c.v == 'A' && listOfNotNull(
			c.northWest?.v,
			c.northEast?.v,
			c.southEast?.v,
			c.southWest?.v,
		).let { it.join() in listOf("MMSS", "SMMS", "SSMM", "MSSM") }
	} log 2
}

fun main() {
	println("Day 4: ")
	part1()
	part1Early()
	part2()
}
