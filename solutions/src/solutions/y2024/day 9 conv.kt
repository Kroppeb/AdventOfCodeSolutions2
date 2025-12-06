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


import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.extensions.repeat
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 9)
//	var inp = pre(9, 0)
	var hob = inp.digitsI()[0]

	var file = hob.flatMapIndexed { i, d -> listOf(if (i divBy 2) i / 2.s else null).repeat(d) }.mut()


	var right = file.size - 1
	var left = 0

	while (left <= right) {
		if (file[left] != null) {
			left++
			continue
		}
		if (file[right] == null) {
			right--
			continue
		}
		file.swap(left, right)
		file log 0
	}

	file.take(right + 1).withIndex().sumOf { (i, v) ->
		i * v!!
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 9)
//	var inp = pre(9, 0)
	var hob = inp.digitsI()[0]

	var file = hob.mapIndexed { i, d -> (if (i divBy 2) i / 2.s else null) to d }.mut()

	file log 0
	var current = file.mut()

	for (i in file.reversed()) {
		if (i.first == null) continue
//		current log 0
		for (ix in current.indices) {
			if (current[ix] == i) {
				break
			}

			var a = current[ix]
			if (a.first != null) {
				continue
			}

			if (a.second >= i.second) {
				val id = current.indexOf(i)
				current[id] = i
				current[ix] = i
				current.add(ix + 1, null to a.second - i.second)
				break
			}
		}
		current.flatMap { (i, c) -> listOf(i).repeat(c) }.map{it?.toString()?:"."}.join() log 0
	}

	current log 0

	current.flatMap { (i, c) -> listOf(i).repeat(c) }.withIndex().sumOf { (i, v) ->
		i * (v ?: 0.s)
	} log 2
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}
