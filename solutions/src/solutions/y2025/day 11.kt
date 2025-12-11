@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d11


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


import itertools.*
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import java.util.Comparator
import java.util.ArrayDeque
import java.util.PriorityQueue
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.math.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.system.exitProcess
import kotlin.text.*
import com.sschr15.aoc.annotations.SkipOverflowChecks
import com.sschr15.aoc.annotations.Memoize
import com.sschr15.aoc.annotations.SkipDestructuringChecks
import log


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(2025,11)
	var hob = inp.alphaNums().associate { it.first() to it.drop(1) }

	dijkstraAll("you", {it == "out"}){c ->
		hob[c]?.map{it to 0.s} ?: listOf()
	} log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 11)
	var hob = inp.alphaNums().associate { it.first() to it.drop(1) }

	dijkstraAll("svr" to (false to false), {it.first == "out" && it.second.first && it.second.second}){(c, s) ->
		hob[c]?.map{
			(it to ((s.first || it == "dac") to (s.second || it == "fft"))) to 0.s
		} ?: listOf()
	} log 1

}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}
