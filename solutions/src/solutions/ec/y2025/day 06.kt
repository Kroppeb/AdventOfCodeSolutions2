@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d06


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
import kotlin.system.exitProcess
import kotlin.text.*
import com.sschr15.aoc.annotations.SkipOverflowChecks
import com.sschr15.aoc.annotations.Memoize
import com.sschr15.aoc.annotations.SkipDestructuringChecks


private val xxxxx = Clock(3, 12)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines("ec", 2025, 6, 1)
	var hob = inp.single() log 0


	val A = hob.mapIndexedNotNull { i, c -> if (c == 'A') i else null }
	val a = hob.mapIndexedNotNull { i, c -> if (c == 'a') i else null }

	a.sumOf { A.count { x -> it > x } } log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines("ec", 2025, 6, 2)
	var hob = inp.single() log 0


	var sum = 0
	for (t in 'a'..'z') {
		val T = t.uppercaseChar()
		val A = hob.mapIndexedNotNull { i, c -> if (c == T) i else null }
		val a = hob.mapIndexedNotNull { i, c -> if (c == t) i else null }

		sum += a.sumOf { A.count { x -> it > x } } log 0
	}

	sum log 2



}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines("ec", 2025, 6, 3)
	var hob = inp.single() log 0
	val ll = hob.length log 0


	var sum = 0.s
	for (t in 'a'..'z') {
		val T = t.uppercaseChar()
		val A = hob.mapIndexedNotNull { i, c -> if (c == T) i else null }
		val a = hob.mapIndexedNotNull { i, c -> if (c == t) i else null }

		sum += a.sumOf { A.map { x ->
			val dif = (x - it).absoluteValue
			val odif = ll - dif.absoluteValue
			if (dif <= 1000){
				1000.s
			} else if (odif <= 1000) {
				999.s
			}else {
				0.s
			}
		}.sum() } log 0
	}

	sum log 3



}

fun main() {
	println("Day  6: ")
	part1()
	part2()
	part3()
}
