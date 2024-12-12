@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d12


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


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 12)
//	var inp = pre(11, 0)
	var hob = inp.grid()

	val seen = setLike(hob.first())
	val regions = setLike(setOf(hob.first()))

	for (i in hob) {
		if (i !in seen) {
			val pp = floodFill(i) {it.getQuadNeighbours().filter{a -> a.v == it.v}}

			seen.addAll(pp)
			regions.add(pp)
		}
	}

	regions.sumOf {
		it log 0
		it.size.s log 0
		val oo = it.map{it.p} .toSet()
		it.sumOf { a -> a.p.getQuadNeighbours().count{b -> b !in oo} } log 0

		it.size.s * it.sumOf { a -> a.p.getQuadNeighbours().count{b -> b !in oo} } log 0
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 12)
//	var inp = pre(11, 0)
	var hob = inp.grid()

	val seen = setLike(hob.first())
	val regions = setLike(setOf(hob.first()))

	for (i in hob) {
		if (i !in seen) {
			val pp = floodFill(i) {it.getQuadNeighbours().filter{a -> a.v == it.v}}

			seen.addAll(pp)
			regions.add(pp)
		}
	}

	regions.sumOf {
		it log 0
		it.size.s log 0
		val oo = it.map{it.p} .toSet()
		it.sumOf {
			a -> a.p.getQuadNeighbours().count{b ->
			(b !in oo )log "a$a$b"
			(a.p + (b-a.p).rotateClock() !in oo)log "a$a$b${b + (b-a.p).rotateClock()}"
			(b + (b-a.p).rotateClock() in oo )log "a$a$b"
			(b !in oo && (a.p + (b-a.p).rotateClock() !in oo || a.p + (b-a.p).rotateClock() + (b-a.p) in oo ))log "a$a$b"
			}
		} log 0

		it.size.s * it.sumOf { a -> a.p.getQuadNeighbours().count{b -> (b !in oo && (a.p + (b-a.p).rotateClock() !in oo || a.p + (b-a.p).rotateClock() + (b-a.p) in oo )) }} log 0
	} log 2
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}
