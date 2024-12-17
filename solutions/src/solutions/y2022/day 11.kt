@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2022


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
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(11) log 0

	var hob = inp.splitOnEmpty().map { ls ->
//		val start = ls[1].sint()
		val opp: (Sint) -> Sint
		when {
			ls[2].contains("old * old") -> opp = { it * it }
			ls[2].contains('*') -> ls[2].sint().let { a -> opp = { it * a } }
			ls[2].contains('+') -> ls[2].sint().let { a -> opp = { it + a } }
			else -> error(ls[2])
		}
		val div = ls[3].sint()
		val ifTrue = ls[4].sint()
		val ifFalse = ls[5].sint()

		opp toH div toH ifTrue toH ifFalse
	}

	val cur = inp.splitOnEmpty().map { ls ->
		ls[1].sints()
	}.map { it.mut() }

	var used = cur.map { 0.s }.mut()

	for (_aaa in 1..10000) {
		for (i in hob.indices) {
			val monk = hob[i]
			val start = cur[i].toList() log 0
			cur[i].clear()

			for (x in start) {
				used[i]++
				val y = monk.a(x) / 3
				if (y % monk.b == 0.s) {
					cur[monk.c].add(y)
				} else {
					cur[monk.d].add(y)
				}
			}
		}
	}

	used log 0
	used.max(2).product() log 1
}


private fun part2() {
	var inp = getLines(11) log 0
	var div = 11.s * 5 * 7 * 2 * 17 * 13 * 3 * 19
	var hob = inp.splitOnEmpty().map { ls ->
//		val start = ls[1].sint()
		val opp: (Sint) -> Sint
		when {
			ls[2].contains("old * old") -> opp = { it * it % div }
			ls[2].contains('*') -> ls[2].sint().let { a -> opp = { it * a % div } }
			ls[2].contains('+') -> ls[2].sint().let { a -> opp = { (it + a) % div } }
			else -> error(ls[2])
		}
		val div = ls[3].sint()
		val ifTrue = ls[4].sint()
		val ifFalse = ls[5].sint()

		opp toH div toH ifTrue toH ifFalse
	}

	val cur = inp.splitOnEmpty().map { ls ->
		ls[1].sints()
	}.map { it.mut() }

	var used = cur.map { 0.s }.mut()

	for (_aaa in 1..10000) {
		for (i in hob.indices) {
			val monk = hob[i]
			val start = cur[i].toList() log 0
			cur[i].clear()

			for (x in start) {
				used[i]++
				val y = monk.a(x)
				if (y % monk.b == 0.s) {
					cur[monk.c].add(y)
				} else {
					cur[monk.d].add(y)
				}
			}
		}
	}

	used log 0
	used.max(2).product() log 1
}


fun main() {
	println("Day 8: ")
	part1()
}
