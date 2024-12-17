@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024


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


import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.graph.floodFill
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.math.round
import kotlin.math.roundToLong


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



public inline fun bsFirst(lower: Sint? = null, upper: Sint? = null, predicate: (Sint) -> Boolean): Sint {
	var low: Sint = lower ?: 0.s
	if (lower == null)
		if (predicate(0.s)) {
			low = -1.s
			while (predicate(low)) {
				low = (low shl 2)
				// overflow
				if (low == 0.s)
					if (!predicate(Sint.MIN_VALUE))
						low = Sint.MIN_VALUE
					else
						error("predicate is even true for min int")
			}
		} else if (predicate(low))
			error("predicate succeeds on low")

	var high: Sint = upper ?: 1.s
	if (upper == null)
		while (!predicate(high)) {
			high = (high shl 2)
			// overflow
			if (high == 0.s)
				if (predicate(Sint.MAX_VALUE))
					high = Sint.MAX_VALUE
				else
					error("predicate isn't even true for max int")
		}
	else if (!predicate(high))
		error("predicate fails on high")
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(mid))
			high = mid
		else
			low = mid
	}
	return high
}

private fun part1() {
	var inp = getLines(13)
//	var inp = pre(13, 0)
	var hob = inp .splitOnEmpty()

	hob.sumOf { (s1, b1, xx) ->
		val a = s1.point() log 0
		val b = b1.point() log 0
		val t = xx.point() + (10000000000000.s toP 10000000000000.s)


		val pos = mutableSetOf<Sint>()
		for (i in 0..100) {
			for (j in 0..100){
				if (i * a + j * b == t)  {
					pos.add(i * 3 + j.s)
				}
			}
		}


		(pos.minOrNull() ?: 0.s) log "m"

		var i = t.x / a.x

//		a to b log 0

		val p = (a.x * b.y) > (a.y * b.x)

		val ir = try {
			bsFirst(0.s, i) { ii ->
				val jj = (t.x - a.x * ii + b.x - 1) / b.x

				if (p)
					(a * ii + b * jj).y <= t.y
				else
					(a * ii + b * jj).y >= t.y
			}
		}
		catch (e: Exception){
			"exc" log "oo"
			return@sumOf 0.s
		}


		var goood = setLike(0.s)

		for (ii in (ir - 10)..(ir + 10)) {
			val jj = (t.x - a.x * ii + b.x - 1) / b.x
			val nt = a * ii + b * jj

			if (nt == t) {
				goood.add(ii * 3 + jj)
			}
		}

		(goood.minOrNull() ?: 0.s) log 0





//		gcd(a.x, b.x) to gcd(a.y, b.y) log 0
//
//		val pp = mutableListOf(
//			mutableListOf(a.x, a.y, 1.s, 0.s),
//			mutableListOf(b.x, b.y, 0.s, 1.s),
//		)
//
//		if (pp[0][0] == 0.s) {
//			pp.swap(0, 1)
//		}
//
////		pp[0] = pp[0].map{it / pp[0][0]}.mut()
////		pp[1] = pp[1].map{it / pp[1][0]}.mut()
//		pp[1] = pp.transpose().map{(ax, bx) -> bx * pp[0][0] - ax * pp[1][0]}.mut()
//		pp[0] = pp.transpose().map{(ax, bx) -> ax * pp[1][1] - bx * pp[0][1]}.mut()
////		pp[1] = pp[1].map{it / pp[1][1]}.mut()
//		pp log 0

//
//
//		a * ii.roundToLong() + b * jj.roundToLong() log 0
//		val o = gcd(pp[0][0], pp[1][1]) log 0
//
//		val ii = (t.x * pp[0][2] + t.y * pp[0][3]) / (pp[0][0] / o)
//		val jj = (t.x * pp[1][2] + t.y * pp[1][3])  / (pp[1][1] / o)
//
//		val nt = (ii * a + jj * b) / o
//		nt log 0
//		t log 0


//		(pos.minOrNull() ?: 0.s) log 0
	} log 1
}


fun main() {
	println("Day 13: ")
	part1()
}
