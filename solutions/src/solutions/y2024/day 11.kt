@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d11


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
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.graph.floodFill
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
	var inp = getLines(2024, 11)
//	var inp = pre(11, 0)
	var hob = inp.sints()[0]

	repeat(25) {
		hob = hob.flatMap {
			val s = it.toString()
			when {
				it == 0.s -> listOf(1.s)
				s.length divBy 2 -> s.splitIn2().toList().sint()
				else -> listOf(it * 2024)
			}
		}
	}

	hob.size log 1
}

private fun part2() {
	var inp = getLines(2024, 11)
//	var inp = pre(11, 0)
	var hob = inp.sints()[0]

	hob.sumOf { check(it, 75) } log 2

//	hob.size log 1
}

val call = memoize { a: Sint, b: Int -> check(a, b) }

fun check(inp: Sint, count: Int): Sint {
	if (count == 0) {
		return 1.s
	}

	if (inp == 0.s) {
		return call(1.s, count - 1) as Sint
	}

	val s = inp.toString()
	if (s.length divBy 2) {
		val (a, b) = s.splitIn2()
		return call(a.sint(), count - 1) as Sint + call(b.sint(), count - 1) as Sint
	}


	return call(inp * 2024, count - 1) as Sint
}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}
