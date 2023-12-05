@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d05fixed


/*

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
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
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

private fun part1() {
	var data = getLines(2023, 5).splitOnEmpty()

	var conv = data.drop(1).map { it.drop(1) }.sints()

	var lows = data.first().first().sints()

	for (mp in conv) {
		var res = mutableListOf<Sint>()

		for ((a, b, l) in mp) {
			var rem = mutableListOf<Sint>()
			for (i in lows) {
				if (i in b until (b + l)) {
					res.add(a + i - b)
				} else {
					rem.add(i)
				}
			}
			lows = rem
		}
		lows = res
	}

	lows.min() log 1
}


private fun part2() {
	var data = getLines(2023, 5).splitOnEmpty()

	var conv = data.drop(1).map { it.drop(1) }.sints()

	var lows = data.first().first().sints().chunked(2).map { (a, b) -> listOf(a, a + b) }

	for (mp in conv) {
		var res = mutableListOf<List<Sint>>()

		for ((a, b, l) in mp) {
			var rem = mutableListOf<List<Sint>>()
			for ((iss, ie) in lows) {
				val inter = (b until b + l).intersect(iss until ie)
				if (inter.isEmpty()) {
					rem.add(listOf(iss, ie))
					continue
				}

				val (s, e) = inter.first() to inter.last()
				res.add(listOf((a - b + s), (a - b + e)))
				if (s > iss) {
					rem.add(listOf(iss, s))
				}
				if (e < ie) {
					rem.add(listOf(e, ie))
				}
			}
			lows = rem
		}

		lows = res.also{it.size log 0}
	}

	lows.map { it.first() }.min() log 2
}


fun main() {
	println("Day 05: ")
	part1()
	part2()
}
