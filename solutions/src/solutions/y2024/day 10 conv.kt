@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d10c1


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
import me.kroppeb.aoc.helpers.collections.extensions.dequeOf
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {

	var inp = getLines(2024, 10)
//	var inp = pre(10, 0)
	var hob = inp.digitsI().grid()

	hob.filter{it.v==0}.sumOf { start ->
		val seen = setTT(start)

		val a = dequeOf(start)
		var c = 0

		while (a.isNotEmpty()) {
			val pp = a.removeFirst()
			if (pp in seen) {
				continue
			}

			if (pp.v == 9){
				c++
			}

			seen.add(pp)

			for (i in pp.getQuadNeighbours()){
				if (i.v - pp.v == 1){
					a.add(i)
				}
			}
		}


		c
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 10)
//	var inp = pre(10, 0)
	var hob = inp.digitsI().grid()

	hob.filter { it.v == 0 }.sumOf { start ->
		val seen = setLike(start)
		val back = mutableMapOf(start to listLike(start))

		val a = dequeOf(start)

		while (a.isNotEmpty()) {
			val pp = a.removeFirst()
			if (pp in seen) {
				continue
			}
			seen.add(pp)

			for (i in pp.getQuadNeighbours()) {
				if (i.v - pp.v == 1) {
					a.add(i)
					back.getOrPut(i) { mutableListOf() }.add(pp)
				}
			}
		}

		val backScore = mutableMapOf(start to 1.s)
		for (i in 1..9) {
			seen.filter { it.v == i }.forEach {
				backScore[it] = back[it]!!.sumOf { backScore[it]!! }
			}
		}

		seen.filter { it.v == 9 }.sumOf { backScore[it]!! }
	} log 2
}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}
