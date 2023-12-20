@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d19c1


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
	var (rules, parts) = getLines(2023, 19).splitOnEmpty()

	var pas = parts.map { "xmas".e().zip(it.sints()).associate { (a, b) -> a to b } }

	val rus = rules.associate { it.substringBefore('{') to (it.substringAfter('{').dropLast(1).split(',')) }

	fun eval(part: Map<Char, Sint>, rule: String = "in"): Boolean {
		part to rule // log 0
		if (rule == "A") return true
		if (rule == "R") return false
		val r = rus[rule] ?: error(rule)

		for (i in r) {
			if (':' in i) {
				val (prp, res) = i.split2(":")
				if ('<' in i) {
					val (pre, post) = prp.split2("<")
					val pp = post.sint()
					if (part[pre[0]]!! < pp) {
						return eval(part, res)
					}
				} else if ('>' in i) {
					val (pre, post) = prp.split2(">")
					val pp = post.sint()
					if (part[pre[0]]!! > pp) {
						return eval(part, res)
					}
				} else error(i)
			} else {
				return eval(part, i)
			}
		}
		error(r)
	}

	pas.filter { eval(it) }.log(0).sumOf { it.values.sum() } log 1
}

private fun part2() {
	var (rules, parts) = getLines(2023, 19).splitOnEmpty()

	var pas = parts.map { "xmas".e().zip(it.sints()).associate { (a, b) -> a to b } }

	val rus = rules.associate { it.substringBefore('{') to (it.substringAfter('{').dropLast(1).split(',')) }

	fun eval(part: Map<Char, SintRange>, rule: String = "in"): Sint {
		part to rule // log 0
		if (rule == "A") return part.values.map { it.sizeS }.product()
		if (rule == "R") return 0.s
		val r = rus[rule] ?: error(rule)
		var part = part

		var cum = 0.s

		for (i in r) {
			if (part.values.any { it.isEmpty() }) return 0.s
			if (':' in i) {
				val (prp, res) = i.split2(":")
				if ('<' in i) {
					val (pre, post) = prp.split2("<")
					val pp = post.sint()
					cum += eval(part.copyWith(pre[0], part[pre[0]]!!.intersect(0 until pp)), res)
					part = part.copyWith(pre[0], part[pre[0]]!!.intersect((pp)..4000))
				} else if ('>' in i) {
					val (pre, post) = prp.split2(">")
					val pp = post.sint()
					cum += eval(part.copyWith(pre[0], part[pre[0]]!!.intersect((pp + 1)..4000)), res)
					part = part.copyWith(pre[0], part[pre[0]]!!.intersect(0..pp))
				} else error(i)
			} else {
				cum += eval(part, i)
			}
		}
		return cum
	}

	// 443136000000000
	// 167409079868000

	eval(mapOf('x' to 1..4000.s, 'm' to 1..4000.s, 'a' to 1..4000.s, 's' to 1..4000.s)) log 2
}


fun main() {
	println("Day 19: ")
	part1()
	part2()
}
