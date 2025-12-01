@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d01


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
	var inp = getLines("ec", 2025, 1, 1)
//	var inp = pre(19, 0)
//	var hob = inp log 0
	var (names_r, _, steps_r) = inp
	val names = names_r.split(",") log 0
	steps_r.split(",").map{(if (it[0] == 'L') -1 else 1) * it.drop(1).sint()}.scan(0.s){ a, i ->
		val p = a + i
		p.coerceAtLeast(0).coerceAtMost(names.lastIndex)
	}.last().let{names[it]} log 0
}

private fun part2() {
	var inp = getLines("ec", 2025, 1, 2)
//	var inp = pre(19, 0)
//	var hob = inp log 0
	var (names_r, _, steps_r) = inp
	val names = names_r.split(",") log 0
	steps_r.split(",").map{(if (it[0] == 'L') -1 else 1) * it.drop(1).sint()}.scan(0.s){ a, i ->
		val p = a + i
		p
	}.last().let{names[it % (0..names.lastIndex)]} log 0
}


private fun part3() {
	var inp = getLines("ec", 2025, 1, 3)
//	var inp = pre(19, 0)
//	var hob = inp log 0
	var (names_r, _, steps_r) = inp
	val names = names_r.split(",").mut() log 0
	steps_r.split(",").map{(if (it[0] == 'L') -1 else 1) * it.drop(1).sint()}.forEach{ i ->
		names.swap(0, i % (0..names.lastIndex))
	}
	names[0] log 0
}

fun main() {
	println("Day  1: ")
	part1()
	part2()
	part3()
}
