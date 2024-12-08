@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d08


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
	var inp = getLines(2024, 8)
//	inp = pre(8, 0)

	val g = inp.grid().groupBy { it.v }.filterKeys { it != '.' }
	val ant = mutableSetOf<Point>()

	for ((k, v) in g.entries) {
		k log 0
		v log 0
		for ((a, b) in v.pairWise()) {
			val s = (a - b) log 0
			ant.add(a.p + s)
			ant.add(b.p - s)
		}
	}

	ant.filter { it in inp.grid().bounds }.size log 1
}

private fun part2() {
	var inp = getLines(2024, 8)
//	inp = pre(8, 0)

	val gg = inp.grid()
	val g = gg.groupBy { it.v }.filterKeys { it != '.' }
	val ant = mutableSetOf<Point>()

	for ((k, v) in g.entries) {
		k log 0
		v log 0
		for ((a, b) in v.pairWise()) {
			val s = (a - b)
			var xx = a.p
			while (xx in gg.bounds){
				ant.add(xx log 0)
				xx += s
			}
			xx = b.p
			while (xx in gg.bounds){
				ant.add(xx log 0)
				xx -= s
			}
		}
	}

	ant.size log 1
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}
