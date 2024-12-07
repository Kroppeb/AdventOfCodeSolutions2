@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d06c1


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
	var inp = getLines(2024, 6).grid()

	var cur = inp.single{it.v == '^'}
	var dir = Clock.up

	var seen = mutableSetOf(cur)

	while (true) {
		val next = cur.offsetOrNull(dir) ?: break
		if (next.v == '#') {
			dir = dir.rotateClock()
		}else {
			seen.add(next)
			cur = next
		}
	}
	seen.size log 1
}

private fun part2() {
	var inp = getLines(2024, 6).grid()

	val cur = inp.single{it.v == '^'}

	inp.count {
		check(inp.copyWith(it, '#').getBp(cur.p))
	} log 2
}

private fun check(
	cur: BoundedGridPoint<Char>,
): Boolean {
	var cur1 = cur
	var dir = Clock.up
	var seen = mutableSetOf(cur1 to dir)

	while (true) {
		val next = cur1.offsetOrNull(dir) ?: break
		if (next.v == '#') {
			dir = dir.rotateClock()
		} else {
			if (next to dir in seen) {
				return true
			}
			seen.add(next to dir)
			cur1 = next
		}

	}
	return false
}

fun main() {
	println("Day 6: ")
	part1()
	part2()
}
