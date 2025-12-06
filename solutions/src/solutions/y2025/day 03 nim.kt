@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d03nim


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
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import log
import kotlin.system.measureNanoTime


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(2025, 3)
	var hob = inp.sumOf { it.e().pairWise().maxOf { (a, b) -> "$a$b".sint() } } log 1
}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 3)

	// inspired by nim-ka
	var hob = inp.map{it.digits()}.sumOf { l->
		var curr = 0.s
		var idx = 0
		repeat(12) {
			var p = l.drop(idx).dropLast(11-it)
			val m = p.indices.maxBy{p[it]}
			curr *= 10
			curr += p[m]
			idx += m + 1
		}

		curr //log 0
	} //log 2


}

fun main() {
	println("Day  3: ")
	part1()
	print(
		measureNanoTime {
			repeat(1000) {
				part2()
			}
		} / 1000.0 / 10e6
	)
}
