@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d20


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


private val xxxxx = Clock(3, 6)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines("ec", 2025, 20 , 1)
	var hob = inp.grid() log 0

	val tr = hob.asQuadGraph { l, r ->
		when{
			l.v !in "TES" -> false
			r.v !in "TES" -> false
			l.right == r -> true
			l.left == r -> true
			(l.p.x + l.p.y) mod 2 == 0.s -> l.up == r
			(l.p.x + l.p.y) mod 2 == 1.s -> l.down == r
			else -> error("")
		}
	}

	tr.neighbours.values.sumOf { it.size } / 2 log 1
}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines("ec", 2025, 20, 2)
	var hob = inp.grid() log 0

	val tr = hob.asQuadGraph { l, r ->
		when{
			l.v !in "TES" -> false
			r.v !in "TES" -> false
			l.right == r -> true
			l.left == r -> true
			(l.p.x + l.p.y) mod 2 == 0.s -> l.up == r
			(l.p.x + l.p.y) mod 2 == 1.s -> l.down == r
			else -> error("")
		}
	}

	tr.neighbours.values.sumOf { it.size } / 2 log 0


	dijkstra(tr.nodes.single { it.value.v == 'S' }, {it.value.v== 'E'}){nodes -> nodes.neighbours} log 1



}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines("ec", 2025, 20, 3)
	var hob = inp.grid() log 0

	var widrt = inp.size


	var hobx = hob.filter { it.v != '.' && it.v != '#' }
	var hob0 = hobx.associate { it.p to it.v }
	var hob1 = hob0.mapKeys { e ->
		val k = e.key
		if ((k.x + k.y mod 2) == 0.s){
			var x = (widrt * 2 - k.x - k.y)
			val y = (k.x - k.y) / 2
			x += y
			x - 2  toP y
		} else {
			var x = (widrt * 2 - k.y * 2 - 1)
			var y = (k.x - k.y - 1) / 2
			x -= y
			x - 2 toP y
		}
	}
	var hob2 = hob1.mapKeys { e ->
		val k = e.key
		if ((k.x + k.y mod 2) == 0.s){
			var x = (widrt * 2 - k.x - k.y)
			val y = (k.x - k.y) / 2
			x += y
			x - 2  toP y
		} else {
			var x = (widrt * 2 - k.y * 2 - 1)
			var y = (k.x - k.y - 1) / 2
			x -= y
			x - 2 toP y
		}
	}
	hob0.keys.bounds() log 0
	hob1.keys.bounds() log 0
	hob1.keys.bounds().print { hob1[it] ?: '.' }

	val hobs = listOf(hob0, hob1, hob2)

	dijkstra(hob0.filter { it.value == 'S' }.keys.single() to 0, {(p,i) -> hobs[i mod 3][p]!! == 'E'}){ (point, i) ->
		point to i log 0
//		hob.bounds.print { if (it == point){
//			hobs[i mod 3][it]!!.color("green")
//		} else if (it in hobs[i mod 3]){
//			hobs[i mod 3][it].toString()
//		} else if (hob[it] != '.'){
//			"#"
//		} else {
//			"."
//		}
//		}
		point.getQuadNeighbourHood().filter{
			it in hobs[i + 1 mod 3]
		}.log("f").filter {
			when {
				point == it -> true
				point.right == it -> true
				point.left == it -> true
				(point.x + point.y) mod 2 == 0.s -> point.up == it
				(point.x + point.y) mod 2 == 1.s -> point.down == it
				else -> error("")
			}
		}.map{n -> n to i + 1 to 1.s} log 0
	} log 1



}

fun main() {
	println("Day 20: ")
	part1()
	part2()
	part3()
}
