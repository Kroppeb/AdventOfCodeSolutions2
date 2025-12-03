@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d03dp


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
private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(2025, 3)
	var hob = inp.sumOf { it.e().pairWise().maxOf { (a, b) -> "$a$b".sint() } } log 1
}

//@SkipOverflowChecks
//@SkipDestructuringChecks
@Suppress("CANDIDATE_CHOSEN_USING_OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION")
private fun part2() {
	var inp = getLines(2025, 3)

	var hob = inp.sumOf {
		yComb(it.e(), 12) { l, i ->
			if (l.size == i) {
				l.joinToString("").toSint()
			} else if (i == 0) {
				0.s
			} else {
				maxOf(
					call(l.drop(1), i),
					call(l.drop(1), i - 1) + l.h().toSint() * 10.s.pow(i - 1),
				)
			}
		}
	} log 2


	inp.sumOf {
		memoize<List<Char>, Int, Sint> { l, i ->
			if (l.size == i) {
				l.joinToString("").toSint()
			} else if (i == 0) {
				0.s
			} else {
				maxOf(
					this(l.drop(1), i),
					this(l.drop(1), i - 1) + l.h().toSint() * 10.s.pow(i - 1),
				)
			}
		}(it.e(), 12)
	} log 2


	inp.sumOf {
		yComb(it.e().reversed(), 0) { l, i ->
			if (l.isEmpty()) {
				0.s
			} else if(i == 12) {
				0.s
			} else{
				maxOf(
					call(l.drop(1), i),
					 call(l.drop(1), i + 1) * 10 + l.h().toSint(),
				)
			}
		}
	} log 2
}

fun main() {
	println("Day  3: ")
	part1()
	part2()
}
