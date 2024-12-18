@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d18.c02


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
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.point.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part2() {
	var inp = getLines(2024, 18)
//	var inp = pre(18, 0)
	var hob = inp.point()

	val t = 70 toP 70
//	val t= 6 toP 6

	val bounds = (0 toP 0) toB t

	bsFirst(0, hob.size) { i ->
		val ch = hob.take(i).toSet()

		t.bfs({ it.isZero() }) { p ->
			p.getQuadNeighbours().filter { it in bounds && it !in ch }
		} == null
	}.let { hob.take(it).last() } log 2
}

fun main() {
	println("Day 18: ")
	part2()
}
