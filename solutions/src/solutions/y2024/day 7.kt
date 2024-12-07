@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d07


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
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 7)
//	inp = pre(7,0)

	inp.sints().filter { ll ->
		val target = ll[0]
		val rest = ll.drop(1)

		target in rest.drop(1).fold(setOf(rest[0])) { l, a ->
			l.flatMap { listOf(it + a, it * a) }.toSet()
		}
	}.sumOf { it[0] } log 1
}

private fun part2() {
	var inp = getLines(2024, 7)
//	inp = pre(7,0)

	inp.sints().filter { ll ->
		val target = ll[0]
		val rest = ll.drop(1)

		target in rest.drop(1).fold(setOf(rest[0])) { l, a ->
			l.flatMap { listOf(it + a, it * a, ("${it}${a}".sint())) }.toSet()
		}
	}.sumOf { it[0] } log 1
}


fun main() {
	println("Day 7: ")
	part1()
	part2()
}
