@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d12.clean


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
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 12)
//	var inp = pre(11, 0)
	var hob = inp.grid()

	val regions = hob.map {
		floodFill(it) {
			it.getQuadNeighbours()
				.filter { a -> a.v == it.v }
		}
	}.toSet()

	regions.map2 { it.p }.sumOf {
		it.size.s * it.sumOf { a -> a.getQuadNeighbours().count { b -> b !in it } }
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 12)
//	var inp = pre(11, 0)
	var hob = inp.grid()

	val regions = hob.map {
		floodFill(it) {
			it.getQuadNeighbours()
				.filter { a -> a.v == it.v }
		}
	}.toSet()


	regions.map2 { it.p }.sumOf {
		it.size.s * it.sumOf { a -> a.getQuadNeighbours().count { b -> (b !in it && (a + (b - a).rotateClock() !in it || b + (b - a).rotateClock() in it)) } }
	} log 2
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}
