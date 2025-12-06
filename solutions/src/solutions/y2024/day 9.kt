@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024


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
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 9)
//	var inp = pre(9, 0)
	var hob = inp.digitsI()[0]

	var file = mutableListOf<Sint>()

	var free = false
	var id = 0.s

	for (i in hob) {
		if (!free) {
			for (x in 1..i) {
				file.add(id)
			}
			id++
		} else {
			for (x in 1..i) {
				file.add(-1.s)
			}
		}
		free = !free
	}

	var end = file.size - 1

	file log 0

	for (i in file.indices) {
		if (i >= end) break
		if (file[i] < 0.s) {
			file[i] = file[end]
			file[end] = -1.s
			while (file[end] < 0)
				end--
//			file log 0
		}
	}

	file.take(end + 1).withIndex().sumOf { (i, v) ->
		i * v
	} log 1
}

private fun part2() {
	var inp = getLines(2024, 9)
//	var inp = pre(9, 0)
	var hob = inp.digitsI()[0]

	var file = mutableListOf<Triple<Sint, Sint, Sint>>()

	var free = false
	var idx = 0.s
	var id = 0.s

	for (i in hob) {
		if (!free) {
			file.add(Triple(idx, idx + i, id))
			id++
			idx += i
		} else {
			idx += i
		}
		free = !free
	}

	var res = mutableListOf<Triple<Sint, Sint, Sint>>()
	var taken = 0.s

	file log 0
	var current = file.mut()

	for (i in file.reversed()) {
		current log 0
		for (ix in current.indices) {
			if (current[ix] == i) {
				break
			}

			var a = current[ix]
			var b = current[ix + 1]
			var sp = b.first - a.second
			if (sp >= i.second - i.first) {
				current.add(ix + 1, Triple(a.second, a.second + i.second - i.first, i.third))
				current.remove(i)
				break
			}
		}
	}

	current log 0

	current.sumOf { (start, end, id) ->
		val n = (end - start)

		id * n * (end + start - 1) / 2
	} log 2
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}
