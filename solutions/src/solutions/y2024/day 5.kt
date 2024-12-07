@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d05


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
private val xxxxz = LoggerSettings.logNonAnswers(false)



private fun part1() {
	var (a, b) = getLines(2024, 5).splitOnEmpty()

	val orderings = a.ints()

	var x = 0;
	l@ for (i in b.ints()) {
		for (ii in i.indices) {
			val ai = i[ii]
			for ((a1, a2) in orderings) {
				if (ai == a1) {
					if (a2 in i.take(ii)){
						continue@l
					}
				}
			}
		}

		x += i[i.size / 2]
	}

	x log 1
}

private fun part2() {
	var (a, b) = getLines(2024, 5).splitOnEmpty()

	val orderings = a.ints()

	var x = 0;
	for (i in b.ints()) {
		var best = i.mut()
		var chgecnge = false
		l@ while (true) {
			for (ii in best.indices) {
				val ai = best[ii]
				for ((a1, a2) in orderings) {
					if (ai == a1) {
						val a2id = best.take(ii).indexOf(a2)
						if (a2id >= 0) {
							best[a2id] = a1
							best[ii] = a2
							chgecnge = true
							continue@l
						}
					}
				}
			}
			break
		}

		if (chgecnge) {
			best log 0
			x += best[i.size / 2]
		}
	}

	x log 2
}

fun main() {
	println("Day 5: ")
	part1()
	part2()
}
