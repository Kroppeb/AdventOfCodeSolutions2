@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023


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


private val xxxxx = Clock(12, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(14).e().grid()

	var locations = inp.filter { it.v == 'O' }.map { it.p }
	var allLocs = mutableListOf(locations)
	var rev = mutableMapOf(locations to 0)

	require(locations in rev)
	locations.size log 0

	for (cy in 1.. 1000000000) {
		if (cy % 1000 == 0) cy to locations.size log 0
		var locs = locations
		var nextLoc = locations.tas()

		for (point in locs.sortedBy { -it.x }) {
			var last = point
			for (i in point.norths()) {
				if (i !in inp.bounds) break
				if (i !in nextLoc && inp.get(i) in listOf('.', 'O')) {
					last = i
				} else {
					break
				}
			}
			nextLoc.add(last)
		}
		locs = nextLoc
		nextLoc = locations.tas()

		for (point in locs.sortedBy { it.y }) {
			var last = point
			for (i in point.wests()) {
				if (i !in inp.bounds) break
				if (i !in nextLoc && inp.get(i) in listOf('.', 'O')) {
					last = i
				} else {
					break
				}
			}
			nextLoc.add(last)
		}

		locs = nextLoc
		nextLoc = locations.tas()

		for (point in locs.sortedBy { it.x }) {
			var last = point
			for (i in point.souths()) {
				if (i !in inp.bounds) break
				if (i !in nextLoc && inp.get(i) in listOf('.', 'O')) {
					last = i
				} else {
					break
				}
			}
			nextLoc.add(last)
		}

		locs = nextLoc
		nextLoc = locations.tas()

		for (point in locs.sortedBy { -it.y }) {
			var last = point
			for (i in point.easts()) {
				if (i !in inp.bounds) break
				if (i !in nextLoc && inp.get(i) in listOf('.', 'O')) {
					last = i
				} else {
					break
				}
			}
			nextLoc.add(last)
		}


			locations = nextLoc

		if (locations in rev) {
			val ind = rev[locations]!! log 0
			cy log 0
			val diff = cy - ind
			val ind2 = (1000000000 - ind) % diff + ind
			locations = allLocs[ind2]
			break
		}
		rev[locations] = cy
		allLocs += locations
	}

	locations.map{it.x + 1}.sum() log 2
}

fun main() {
	println("Day 14: ")
	part1()
}
