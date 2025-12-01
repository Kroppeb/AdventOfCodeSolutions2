@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d08


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
	var inp = getLines("ec", 2025, 8, 1)
	var hob = inp.single().sints() log 0

	hob.zipWithNext().count{(a,b) -> (b -a).abs() == 16.s
	} log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines("ec", 2025, 8, 2)
	var hob = inp.single().sints() log 0

	var lines = mutableListOf<Pair<Sint, Sint>>()
	var count = 0

	for ((s, e) in hob.zipWithNext()) {
		val p = if (s > e) (e + 1)..<s else (s + 1)..<e
		count += lines.count { (l, r) -> (l !in listOf(s,e)) && (r !in listOf(s,e)) && ((l in p) != (r in p))} log 0
		lines += s to e
	}
	count log 2

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines("ec", 2025, 1, 3)
	var hob = inp.single().sints() log 0

//	var lines = mutableListOf<Pair<Sint, Sint>>()
	val lines = hob.zipWithNext()
	var count = 0

	(1.s..256).pairWise().maxOf { (s, e) ->
		val p = if (s > e) (e + 1)..<s else (s + 1)..<e
		lines.count { (l, r) ->  ((l !in listOf(s,e)) && (r !in listOf(s,e)) && ((l in p) != (r in p))) || (l in listOf(s, e) && r in listOf(s,e))}
	} log 3

}

fun main() {
	println("Day  8: ")
	part1()
	part2()
	part3()
}
