@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024


/*

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


fun mix(a: Sint, b:Sint) = a xor b
fun prune(a: Sint) = a mod 16777216

private fun part1() {
	var inp = getLines(2024, 22)
//	var inp = pre(22, 0)
	var hob = inp.sint()

	hob.sumOf { x ->
		var xx = x

		repeat(2000) {
			val a = prune(mix(xx, xx * 64))
			val b = prune(mix(a, a / 32))
			val c = prune(mix(b, b * 2048))
			xx = c
		}
		xx
	} log 1

}
private fun part2() {
	var inp = getLines(2024, 22)
//	var inp = pre(22, 0)
	var hob = inp.sint()

	val rr = hob.map { x ->
		val prices = listOf(x mod 10).mut()
		var xx = x

		repeat(2000) {
			val a = prune(mix(xx, xx * 64))
			val b = prune(mix(a, a / 32))
			val c = prune(mix(b, b * 2048))
			xx = c
			prices += xx mod 10
		}

		prices.windowed(2).map{(a,b) -> b-a to b}.windowed(4).map{l ->
			l.firsts to l.last().second
		}.reversed().associate { it }
	}

	val t = ((-9.s)..9.s).cartesianSquare().cartesianSquare().map{(a,b) -> listOf(a.first, a.second, b.first, b.second)}

	t.maxOf{ ll -> rr.sumOf { l -> l[ll] ?: 0.s }} log 2


}

fun main() {
	println("Day 22: ")
	part1()
	part2()
}
