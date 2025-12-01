@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d02


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
import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.math.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


fun mul(a: List<Sint>, b: List<Sint>): List<Sint> {
	return listOf(
		a[0] * b[0] - b[1] * a[1],
		a[0] * b[1] + b[0] * a[1],
	)
}

private fun eval(s: List<Sint>): Boolean {
	var hob = s.map{0.s}
	repeat(100) {
		hob = mul(hob, hob)
		hob = hob.map{it / 100_000}
		hob = hob.zip(s){a, b -> a + b}
		if (hob.any{it > 1_000_000 || it < -1_000_000}) {
			return false
		}
	}
	return true
}

private fun part1() {
	var inp = getLines("ec", 2025, 2, 1)
//	var inp = pre(19, 0)
	var hob = inp.sints().first() log 0
	val s = hob
	hob = hob.map{0.s}

	fun mul(a: List<Sint>, b: List<Sint>): List<Sint> {
		return listOf(
			a[0] * b[0] - b[1] * a[1],
			a[0] * b[1] + b[0] * a[1],
		)
	}

	repeat(3) {
		hob = mul(hob, hob)
		hob = hob.map{it / 1_000_000}
		hob = hob.zip(s){a, b -> a + b}
	}

	hob log 0

}

private fun part2() {
	var inp = getLines("ec", 2025, 2, 2)
//	var inp = pre(19, 0)
	var hob = inp.sints().first() log 0

	(0..1000.s step 10).cartesianProduct((0..1000.s step 10)).count{(a,b) ->
		eval(listOf(hob[0] + a,hob[1] + b))
	} log 0

}

private fun part3() {
	var inp = getLines("ec", 2025, 2, 3)
//	var inp = pre(19, 0)
	var hob = inp.sints().first() log 0

	(0..1000.s step 1).cartesianProduct((0..1000.s step 1)).count{(a,b) ->
		eval(listOf(hob[0] + a,hob[1] + b))
	} log 0

}

fun main() {
	println("Day  2: ")
	part1()
	part2()
	part3()
}
