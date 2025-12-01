@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d03


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
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



private fun part1() {
	var inp = getLines("ec", 2025, 3, 1)
//	var inp = pre(19, 0)
	var hob = inp.sints().first().sortedDescending() log 0

	var t = hob[0]
	var m = hob[0]
	for (p in hob.b()) {
		if (p < m) {
			m = p
			t += p
		}
	}
	t log 1
}


private fun part2() {
	var inp = getLines("ec", 2025, 3, 2)
//	var inp = pre(19, 0)
	var hob = inp.sints().first().sorted() log 0

	var t = hob[0]
	var m = hob[0]
	var c = 1
	for (p in hob.b()) {
		if (p > m) {
			m = p
			t += p
			c++
			if (c == 20) {
				break
			}
		}
	}
	t log 1
}

private fun part3() {
	var inp = getLines("ec", 2025, 3, 3)
//	var inp = pre(19, 0)
	var hob = inp.sints().first().sorted() log 0

	var t = hob[0]
	var m = hob[0]
	var c = 1
	val left = mutableListOf<Sint>()
	for (p in hob.b()) {
//		if (p > m && c < 20){
//			m = p
//			t += p
//			c++
//		} else {
			left.add(p)
//		}
	}
	left.countEach().values.max() log 1


}

fun main() {
	println("Day  3: ")
	part1()
	part2()
	part3()
}
