@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d06c2


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

	val start = inp.single{it.v == '^'}

	generateSequence(start to Clock.up) { (cur, dir) ->
		when (cur.offsetOrNull(dir)?.v){
			null -> null
			'#' -> cur to dir.rotateClock()
			else -> cur.offsetOrNull(dir)!! to dir
		}
	}.map{it.first.p}.toSet().size log 1
}

private fun part2() {
	var inp = getLines(2024, 6).grid()

	val start = inp.single{it.v == '^'}

	generateSequence(start to Clock.up) { (cur, dir) ->
		when (cur.offsetOrNull(dir)?.v){
			null -> null
			'#' -> cur to dir.rotateClock()
			else -> cur.offsetOrNull(dir)!! to dir
		}
	}.map{it.first.p}.toSet().size log 1
}


fun main() {
	println("Day 6: ")
	part1()
//	part2()
}
