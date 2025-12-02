@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d01r1


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
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*
import log


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	// This was my initial solve, but with the proper start of "50" instead of "0"
	var inp = getLines(2025, 1)
	var hob = inp.map { it.first().toPoint() * it.sint() } log 0

	hob.scan(Clock.right * 50) { i, p -> i + p mod (Point.ZERO toB Clock.right * 99) }.count { it == Point.ZERO } log 1
}


//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 1)
	// This was my initial solve, but with the proper start of "50" instead of "0"
	var hob = inp.map { it.first() to it.sint() }.rleDecodeI({it.first to 1.s}, {it.second.i})

//	hob.scan(50.s) { i, (a, b) -> if (a == 'L') i - b else i + b }.map { it mod 0..99 }.count { it == 0.s } log 1
}

fun main() {
	println("Day 1: ")
	part1()
	part2()
}
