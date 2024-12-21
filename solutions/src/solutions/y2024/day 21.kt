@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d21


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



import com.sschr15.aoc.annotations.Memoize
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


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 21)
//	var inp = pre(21, 0)
	var hob = inp

	hob.sumOf { tt ->
//		val tt = "029A"
		val step1 = minimizeA(tt.e())
		val step1R = step1.allMinBy { it.size }

		step1.size to step1R.size log 0

		val step2 = step1R.flatMap { minimizeB(it) }
		val step2R = step2.allMinBy { it.size }

		step2.size to step2R.size log 0

		val step3 = step2R.flatMap { minimizeB(it) }
		val step3R = step3.allMinBy { it.size }

		step3.size to step3R.size log 0

		tt.sint() * step3R[0].size log 0
	} log 2
}

private fun part2() {
	var inp = getLines(2024, 21)
//	var inp = pre(21, 0)
	var hob = inp

	hob.sumOf { tt ->

//		val tt = "029A"
		var steps = minimizeA(tt.e())
		var stepsR = steps.allMinBy { it.size }

		steps.size to stepsR.size log 0

//		repeat(2) { i ->
//			steps = stepsR.flatMap { minimizeB(it) }
//			stepsR = steps.allMinBy { it.size }
//			steps.size to stepsR.size log "hello" + i
//		}
//		tt.sint() * stepsR[0].size log 0

		val rr = (stepsR.map{listOf(0 toP 0) + it}).map{ll -> ll.windowed(2).map{(a,b) -> minimizeB2(a, b, 25)}}.minOf{it.sum()}
		val endCount = rr

		tt.sint() * endCount log 0
	} log 2
}

val cpA = mapOf(
	'A' to (0 toP 0),
	'0' to (1 toP 0),

	'1' to (2 toP 1),
	'2' to (1 toP 1),
	'3' to (0 toP 1),

	'4' to (2 toP 2),
	'5' to (1 toP 2),
	'6' to (0 toP 2),

	'7' to (2 toP 3),
	'8' to (1 toP 3),
	'9' to (0 toP 3),
)

val cpB = mapOf(
	(0 toP 0) to (0 toP 0),
	(0 toP 1) to (1 toP 0),
	(1 toP 0) to (2 toP -1),
	(0 toP -1) to (1 toP -1),
	(-1 toP 0) to (0 toP -1),
)

@Memoize
fun pathsX(diff: Point): List<List<Point>> {
	if (diff.isZero()) return listOf(emptyList())

	val newX = diff.x - diff.x.sign()
	val newY = diff.y - diff.y.sign()

	if (abs(diff.x) != 0.s) {
		if (abs(diff.y) != 0.s) {
			return pathsX(newX toP diff.y).map { listOf(diff.x.sign() toP 0) + it } +
				pathsX(diff.x toP newY).map { listOf(0 toP diff.y.sign()) + it }
		} else {
			return pathsX(newX toP diff.y).map { listOf(diff.x.sign() toP 0) + it }
		}
	} else {
		return pathsX(diff.x toP newY).map { listOf(0 toP diff.y.sign()) + it }
	}
}

@Memoize
fun paths(start: Point, end: Point): List<List<Point>> {
	val t = pathsX(end - start)
	return t.filter { ll ->
		!ll.scan(start) { a, b -> a + b }.any { it == 2 toP 0 }
	}.allMinBy { it.windowed(2).count { (a, b) -> a != b } }
}


fun minimizeA(inp: List<Char>): List<List<Point>> {
	val diffs = (listOf('A') + inp).windowed(2).map { (s, e) ->
		paths(cpA[s]!!, cpA[e]!!,)
	}

	diffs.map { it.size } log 0

	return diffs.map2 { it + listOf(0 toP 0) }
		.fold(listOf(emptyList<Point>())) { c, l -> l.flatMap { c.map { cc -> cc + it } } }
}

fun minimizeB(inp: List<Point>): List<List<Point>> {
	val diffs = (listOf(0 toP 0) + inp).windowed(2).map { (s, e) ->
		paths( cpB[s]!!, cpB[e]!!)
	}

	return diffs.map2 { it + listOf(0 toP 0) }
		.fold(listOf(emptyList<Point>())) { c, l -> l.flatMap { c.map { cc -> cc + it } } }
}

@Memoize
fun minimizeB2(start: Point, end:Point, depth: Int): Sint {
	depth log "h"
	if (depth == 0){
		return 1.s
	}
	val diffs = paths( cpB[start]!!, cpB[end]!!)
	depth to diffs.first().size log "ssssss"

	val paths = diffs.map { listOf(0 toP 0) + it + listOf(0 toP 0) }

	return paths.minOf{path -> path.windowed(2).sumOf { (a,b) -> minimizeB2(a, b, depth-1) log "??"} log "hh" }
}

fun main() {
	println("Day 21: ")
	part1()
	part2()
}
