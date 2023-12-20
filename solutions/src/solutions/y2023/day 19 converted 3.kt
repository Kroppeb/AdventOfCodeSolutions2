@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d19c3


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


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var (rules, parts) = getLines(2023, 19).splitOnEmpty()

	var pas = parts.map { "xmas".e().zip(it.sints()).associate { (a, b) -> a to b } }

	val rus = rules.associate { it.substringBefore('{') to (it.substringAfter('{').dropLast(1).split(',')) }

	pas.filterY("in") { part, rule ->
		part to rule // log 0
		if (rule == "A") ret(true)
		if (rule == "R") ret(false)
		val r = rus[rule] ?: error(rule)

		for (i in r.dropLast(1)) {
			val (prp, res) = i.split2(":")
			val score = part[prp[0]]!!
			val pp = prp.sint()
			if (if ('<' in i) score < pp else score > pp) {
				retCall(part, res)
			}
		}
		retCall(part, r.last())
	}.sumOf { it.values.sum() } log 1
}

private fun part2() {
	var (rules, parts) = getLines(2023, 19).splitOnEmpty()

	val rus = rules.associate { it.substringBefore('{') to (it.substringAfter('{').dropLast(1).split(',')) }

	yComb(mapOf('x' to 1..4000.s, 'm' to 1..4000.s, 'a' to 1..4000.s, 's' to 1..4000.s), "in") { part, rule ->
		if (rule == "A") ret(part.values.map { it.sizeS }.product())
		if (rule == "R") ret(0.s)

		rus[rule]!!.fold(0.s to part) { (sum, part), i ->
			if (part.values.any { it.isEmpty() }) ret(0.s)
			if (':' in i) {
				val (prp, res) = i.split2(":")
				val re = prp[0]
				val pp = prp.sint()
				val good = part.copyWith(re) { it!!.intersect(if ('<' in i) 1 until pp else (pp + 1)..4000) }
				val bad = part.copyWith(re) { it!!.intersect(if ('<' in i) pp..4000 else 1..pp) }
				sum + call(good, res) to bad
			} else {
				sum + call(part, i) to mapOf()
			}
		}.first
	} log 2
}


fun main() {
	println("Day 19: ")
	part1()
	part2()
}
