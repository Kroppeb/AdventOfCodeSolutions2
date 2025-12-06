@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d06c1


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
import kotlin.text.*
import log


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2025, 6)

	var p = inp.dropLast(1).sints().transpose()

	var q = inp.last().split(" ").filter{it.isNotEmpty()}.zip(p).map{ (a, b) ->
		if (a == "+") {
			b.sum()
		} else{
			b.product()
		}
	}.sum() log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 6)

	var p = inp.dropLast(1).e().transpose().splitOn { it.all { it == ' ' } }.map2{it.join().sint()} log 0
	// could also have worked
	var p2 = inp.k().e().transpose().map{it.join()}.splitOn{it.trim().isEmpty()}.log(0).sint() log 0
	// added a transpose on strings
	var p3 = inp.k().transpose().splitOn{it.trim().isEmpty()}.sint() log 0

	var q = inp.last().split(" ").filter{it.isNotEmpty()}
	var q2 = inp.last().split(Regex(" +"))
	q.zip(p3).map{ (a, b) ->
		if (a == "+") {
			b.sum()
		} else{
			b.product()
		}
	}.sum() log 1

}


fun main() {
	println("Day  6: ")
	part1()
	part2()
}
