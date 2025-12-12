@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d12c


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
	var inp = getLines(2025, 12)
	var hob = inp.splitOnEmpty()

	val shapes = hob.dropLast(1).map{
		it.drop(1).grid().filter { it.v == '#' }.map { it.p }.let{ ps ->
			listOf(ps, ps.map { it.x toP (2 - it.y) }).flatMap{ ps ->
				listOf(ps, ps.map{(2 toP 2) - it})
			}.flatMap { ps ->
				listOf(ps, ps.map{(it.y toP 2 - it.x)})
			}.map{it.toSet()}.toSet().toList().map{it.toList()}
		}.also{it.size log 0}
	}

	hob.last().sints().map{line ->
		val (xs, ys) = line.take(2)
		val amounts = line.drop(2)

		if (amounts.zip(shapes).map{(a, b) -> a * b[0].size}.sum() > (xs * ys)){
			false
		} else if (amounts.sum() <= (xs / 3) * (ys / 3)){
			true
		} else {
			no()
		}
	}.count { it } log 1


}


fun main() {
	println("Day 12: ")
	part1()
}
