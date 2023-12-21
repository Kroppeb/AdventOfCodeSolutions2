@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d21


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
	var data = getLines(2023, 21).grid()
	var mm = mutableMapOf<Int, List<BGPC>>()

	var s = listOf(data.find { it.v == 'S' }!!)
	var seen = s.map{it.p}.toSet().toMutableSet()
	mm[0] = s

	for (i in 1..64) {
		i to s.size log 0
//
		s = s.flatMap { it.getQuadNeighbours() }.filter{it.p !in seen}.distinct().filter{it.v != '#'}
		mm[i] = s
		seen += s.map{it.p}
	}

	(0..64 step 2).flatMap { mm[it] !!}.size log 1
}

private fun part2Help() {
	var data = getLines(2023, 21).grid()
	var mm = mutableMapOf<Int, List<Point>>()

	var s = listOf(data.find { it.v == 'S' }!!.p).toSet()
	var previous = s.tas().toSet()
	var seen = previous.tas()
	var count = 0.s

	var p1 = 0.s
	var p2 = 0.s
	var p3 = 0.s

	seen += s

	for (i in 1..26501365) {
		val x = s
		s = s.flatMap { it.getQuadNeighbours() }.filter{it !in previous}.distinct().filter{data[it % data.bounds] != '#'}.toSet()
		if (i % 2 == 1) {
			count += s.size
		}
//		seen += s

		previous = x

		if (i % 262 == 65) {
			i to count log 0
			val d1 = count - p1 log "d1"
			p1 = count

			val d2 = d1 - p2 log "d2"
			p2 = d1

			val d3 = d2 - p3 log "d3"
			p3 = d2
		}
	}

	count log 2
}

fun part2() {
	var s = 1375
	var count = 1661857.s
	var step = 572668.s + 120528

	while (s < 26501365) {
		s += 262
		count += step
		step += 120528
	}

	s log 0

	count log 1
}


fun main() {
	println("Day 21: ")
	part1()
	part2()
}
