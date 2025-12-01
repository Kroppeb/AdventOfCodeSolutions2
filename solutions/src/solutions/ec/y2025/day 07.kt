@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d07


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


private val xxxxx = Clock(3, 12)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines("ec", 2025, 7, 1)
	var hob = inp.splitOnEmpty() log 0

	val ns = hob[0][0].split(",")
	val rules = hob[1].associate { it[0] to it.drop(4).split(",").map{it.single()} }


	ns.single { it.zipWithNext { a, b -> a !in rules || b in rules[a]!! }.all{it} } log 1




}
//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines("ec", 2025, 7, 2)
	var hob = inp.splitOnEmpty() log 0

	val ns = hob[0][0].split(",")
	val rules = hob[1].associate { it[0] to it.drop(4).split(",").map{it.single()} }


	ns.mapIndexedNotNull { i, it -> if(it.zipWithNext { a, b -> a !in rules || b in rules[a]!! }.all{it}) i + 1 else null }.sum() log 2




}


//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines("ec", 2025, 7, 3)
	var hob = inp.splitOnEmpty() log 0

	var ns = hob[0][0].split(",")
	val rules = hob[1].associate { it[0] to it.drop(4).split(",").map{it.single()} }

//	ns = ns.filter{it.zipWithNext { a, b -> a in rules && b in rules[a]!! }.all { it }} log "z"
//	ns = ns.distinct()
//	ns = ns.filter{!ns.any { o -> it != o && it.startsWith(o) log 0 }}.log("f")

	val mx = ns.minOf { it.length }

	var genS = ns.map{it.last()}.toSet().flatMap{ rules[it]?: listOf()}.map { it.g() }.distinct()
	val genn = genS.toMutableList()
	genS.size log 0
	repeat(10 - mx) {
		genS = genS.flatMap { (rules[it.last()] ?: listOf()).map{l -> it + listOf(l)} }
		genn += genS
		genS.take(10) log "a"
		genS.size log 0
	}
	genn.size log "?"

	ns = ns.filter{it.zipWithNext { a, b -> a in rules && b in rules[a]!! }.all { it }} log "z"
	ns = ns.distinct()
	ns = ns.filter{!ns.any { o -> it != o && it.startsWith(o) log 0 }}.log("f")


	ns.sumOf { n ->
		genn.map { n + it.joinToString("")}.filter{it.length in 7..11}.count { it.zipWithNext { a, b -> a in rules && b in rules[a]!! }.all { it } } log 0
	}log 3





}

fun main() {
	println("Day  7: ")
	part1()
	part2()
	part3()
}
