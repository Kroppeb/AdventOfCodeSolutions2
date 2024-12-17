@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024


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
import me.kroppeb.aoc.helpers.graph.floodFill
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.math.round
import kotlin.math.roundToLong


private val xxxxx = Clock(3, 6)
//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 14)
//	var inp = pre(14, 0)
	var hob = inp.points()

//	var bounds = (0 toP 0) toB (Clock.down * 102 + Clock.right * 100)
	var bounds = (0 toP 0) toB (Clock.down * 102 + Clock.right * 100)

	val ll = hob.map{(p, v) -> (p + v * 100) % bounds} log 0

	var q1 = ll.filter{it.isAbove(bounds.exactCenter()) && it.isLeftOf(bounds.exactCenter())} log 0
	var q2 = ll.filter{it.isAbove(bounds.exactCenter()) && it.isRightOf(bounds.exactCenter())} log 0
	var q3 = ll.filter{it.isBelow(bounds.exactCenter()) && it.isLeftOf(bounds.exactCenter())} log 0
	var q4 = ll.filter{it.isBelow(bounds.exactCenter()) && it.isRightOf(bounds.exactCenter())} log 0

	q1.size * q2.size * q3.size * q4.size log 1
}

private fun part2() {
	var inp = getLines(2024, 14)
//	var inp = pre(14, 0)
	var hob = inp.points()

//	var bounds = (0 toP 0) toB (Clock.down * 102 + Clock.right * 100)
	var bounds = (0 toP 0) toB (Clock.down * 102 + Clock.right * 100)


	var n = 0

	loop { i ->
		val ll = hob.map { (p, v) -> (p + v * i) % bounds }.toSet()
//		bounds.print { if (it in ll) "#" else " "}
		if (ll.size > n) {
			bounds.print { if (it in ll) "#" else " " }
			i to ll.size log 0
			n = ll.size
		}
//		System.`in`.read()
	}
}


fun main() {
	println("Day 14: ")
	part1()
//	part2()
}
