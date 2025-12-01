@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d04


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
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)




private fun part1() {
	var inp = getLines("ec", 2025, 4, 1)
//	var inp = pre(19, 0)
	var hob = inp.sint() log 0

	hob[0] * 2025 / hob.last() log 1



}

private fun part2() {
	var inp = getLines("ec", 2025, 4, 2)
//	var inp = pre(19, 0)
	var hob = inp.sint() log 0

	(hob.last() * 10000000000000 + hob.first() - 1)  / hob.first() log 2



}
private fun part3() {
	var inp = getLines("ec", 2025, 4, 3)
//	var inp = pre(19, 0)
	var hob = inp.drop(1).dropLast(1).sints() log 0

	var curr = 100 * inp.first().sint()

	for ((a, b) in hob) {
		curr *= b
		curr /= a
	}

	curr / inp.last().sint() log 3



}

fun main() {
	println("Day  4: ")
	part1()
	part2()
	part3()
}
