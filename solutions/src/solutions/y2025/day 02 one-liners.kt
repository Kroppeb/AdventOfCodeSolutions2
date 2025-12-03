@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d02ol


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
	getLines(2025, 2).single().posSints().chunked(2).flatMap { (s,e) -> (s..e).map{it.toString()}}.filter{ (it.length divBy  2) && it.splitIn(2).allEqual()}.sumOf { it.sint() } log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	getLines(2025, 2).single().posSints().chunked(2).flatMap { (s,e) -> (s..e).map{it.toString()}}.filter{ (2..(it.length)).any { i-> (it.length divBy i) && it.splitIn(i).allEqual() } } .sumOf { it.sint() } log 2

}

fun main() {
	println("Day  2: ")
	part1()
	part2()
}
