@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d11c


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
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import log
import me.kroppeb.aoc.helpers.collections.list.toH


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(2025,11)
	var hob = inp.alphaNums().associate { it.first() to it.drop(1) }

	dijkstraAll("you", {it == "out"}){c ->
		hob[c]?.map{it to 0.s} ?: listOf()
	} log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 11)
	var hob = inp.alphaNums().associate { it.first() to it.drop(1) }

	dijkstraAll("svr" toH false toH false, {it == ("out" toH true toH true)}){(c, a, b) ->
		hob[c]?.map{
			(it toH (a || it == "dac") toH (b || it == "fft")) to 0.s
		} ?: listOf()
	} log 2

}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}
