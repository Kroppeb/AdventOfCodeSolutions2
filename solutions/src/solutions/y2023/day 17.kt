@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d17


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
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2023, 17).digitsI().grid()

	dijkstra((inp.bounds.upperLeft) toH Clock.down toH 0, {it.a == inp.bounds.lowerRight}) {(pos, dir, leng) ->
		listOf(
			pos.r toH Clock.right,
			pos.l toH Clock.left,
			pos.d toH Clock.down,
			pos.u toH Clock.up,
		).filter{it.a in inp.bounds}.filter{it.b.dot(dir) >= 0}.filter{if (it.b == dir && leng != 3) false else true}.map{
			it.a toH it.b toH (if (it.b == dir) leng + 1 else 1)
		}.map{it to inp[it.a].s}
	} log 1
}

private fun part2() {
	var inp = getLines(2023, 17).digitsI().grid()

	dijkstra((inp.bounds.upperLeft) toH (0 toP 0) toH 0, {it.a == inp.bounds.lowerRight && it.c >= 4.s}) {(pos, dir, leng) ->
		listOf(
			pos.r toH Clock.right,
			pos.l toH Clock.left,
			pos.d toH Clock.down,
			pos.u toH Clock.up,
		).filter{it.a in inp.bounds}.filter{it.b.dot(dir) >= 0}.filter{if (it.b == dir)
			leng < 10  else
				leng >= 4 || leng == 0}.map{
			it.a toH it.b toH (if (it.b == dir) leng + 1 else 1)
		}.map{it to inp[it.a].s}
	} log 2
}


fun main() {
	println("Day 17: ")
	part1()
	part2()
}
