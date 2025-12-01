@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d19


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


private val xxxxx = Clock(3, 12)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines("ec", 2025, 19, 1).sints()

	var hob = inp.associate { (a,b,c) -> a to b..<(b+c) } log 0
	val e = hob.keys.max()

	dijkstra(0 toP 0, {it.x == e}){c ->
		listOf(c.right.up to 1.s, c.right.down to 0.s).filter{(c, _) ->
			c.y > 0 && (c.x !in hob.keys || c.y in hob[c.x]!!)
		}log 0
	} log 1


}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines("ec", 2025, 19, 2).sints()

	var hob = inp.map { (a,b,c) -> a to b..<(b+c) }.groupBy { it.first }.mapValues { it.value.map{it.second} } log 0
	val e = hob.keys.max()

	dijkstra(0 toP 0, {it.x == e}){c ->
		listOf(c.right.up to 1.s, c.right.down to 0.s).filter{(c, _) ->
			c.y > 0 && (c.x !in hob.keys || hob[c.x]!!.any{c.y in it})
		}log 0
	} log 1


}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines("ec", 2025, 19, 3).sints()

	var hob = inp.map { (a,b,c) -> a to b..<(b+c) }.groupBy { it.first }.mapValues { it.value.map{it.second} } log 0
	val keys = listOf(0.s) + hob.keys.sorted()
	val hh = hob.keys.sorted().map{hob[it]!!}


	dijkstra(0 to 0.s, {it.first == keys.lastIndex}){(i, y) ->
		val br = keys[i + 1] - keys[i]
		val gates = hh[i]
		(-br..br step 2).filter{dy ->
			val ny = dy + y
			ny > 0 && gates.any { ny in it }
		}.map{dy -> (i + 1) to (dy + y) to (dy + br) / 2}
	} log 1


}

fun main() {
	println("Day 19: ")
	part1()
	part2()
	part3()
}
