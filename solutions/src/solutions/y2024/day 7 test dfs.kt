@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d07.tdfs


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
import kotlin.system.measureTimeMillis


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 7)
//	inp = pre(7,0)

	inp.sints().filter { ll ->
		val target = ll[0]
		val rest = ll.drop(1)

		yComb(target, rest.size - 1) { curr, ind ->
			if (ind < 0) return@yComb (curr == 0.s)
			val a = rest[ind]

			curr >= a && call(curr - a, ind - 1) || curr % a == 0.s && call(curr / a, ind - 1)
		}
	}.sumOf { it[0] } log 1
}

fun smallestPowerOf10GreaterThan(x: Sint): Sint {
	var power = 1.s
	while (power <= x) {
		power *= 10.s
	}
	return power
}

private fun part2() {
	var inp = getLines(2024, 7)
//	inp = pre(7,0)

	inp.sints().filter { ll ->
		val target = ll[0]
		val rest = ll.drop(1)

		yComb(target, rest.size - 1) { curr, ind ->
			if (ind < 0) return@yComb (curr == 0.s)
			val a = rest[ind]
			val p = smallestPowerOf10GreaterThan(a)

			curr >= a && call(curr - a, ind - 1) ||
				curr % a == 0.s && call(curr / a, ind - 1) ||
				curr % p == a && call(curr / p, ind - 1)
		}
	}.sumOf { it[0] } log 1
}


fun main() {
	println("Day 7: ")
	println(measureTimeMillis {
		part1()
		part2()
	})
	println(measureTimeMillis {
		part1()
		part2()
	})
}
