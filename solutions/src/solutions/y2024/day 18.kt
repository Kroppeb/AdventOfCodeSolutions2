@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d18


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


private fun part1() {
	var inp = getLines(18)
//	var inp = pre(18, 0)
	var hob = inp.point()

	val t = 70 toP 70
//	val t= 6 toP 6

	val bounds = (0 toP 0) toB t

	bsLast(0, hob.size + 1) { i ->
		if (i > hob.size) return@bsLast false
		val ch = hob.take(i).toSet()

		bfsOld(t, {it == 0 toP 0}){ p ->
			p.getQuadNeighbours().filter{it in bounds}.filter{it !in ch}
		}.log(hob[i] to i).state == null
	} + 1 log 2





}

fun main() {
	println("Day 18: ")
	part1()
}
