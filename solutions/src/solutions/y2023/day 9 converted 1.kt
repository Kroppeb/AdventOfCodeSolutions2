@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d09c1


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
	var data = getLines(2023, 9).sints()

	data.map {
		yComb(it) { ix ->
			if (ix.all { it.isZero() }) {
				0.s
			} else {
				val xx = call(ix.zipWithNext { a, b -> b - a })
				ix.last() + xx
			}
		}
	}.sum() log 1


	data.map {
		yComb(it) { ix ->
			if (ix.all { it.isZero() }) {
				ret(0.s)
			}

			val xx = call(ix.zipWithNext { a, b -> b - a })
			ix.last() + xx
		}
	}.sum() log 1

	data.map {
		it.yComb { ix ->
			if (ix.all { it.isZero() }) {
				ret(0.s)
			}

			val xx = call(ix.zipWithNext { a, b -> b - a })
			ix.last() + xx
		}
	}.sum() log 1

	data.mapY {
		if (it.all { it.isZero() }) {
			ret(0.s)
		}

		val xx = call(it.zipWithNext { a, b -> b - a })
		it.last() + xx
	}.sum() log 1

	data.sumOfY {
		if (it.all { it.isZero() }) {
			ret(0.s)
		}

		val xx = call(it.zipWithNext { a, b -> b - a })
		it.last() + xx
	} log 1

	data.sumOfY {
		if (it.all { it.isZero() }) {
			0.s
		} else {
			it.last() + call(it.zipWithNext { a, b -> b - a })
		}
	} log 1

	getLines(2023, 9).sints().sumOfY { if (it.all { it.isZero() }) 0.s else it.last() + call(it.zipWithNext { a, b -> b - a }) } log 1
	getLines(2023, 9).sints().sumOfY { if (it.allEqual()) it.first() else it.last() + call(it.zipWithNext { a, b -> b - a }) } log 1
	getLines(2023, 9).sints().sumOfY { it.distinct().singleOrNull() ?: it.last() + call(it.zipWithNext { a, b -> b - a }) } log 1
}


private fun part2() {
	getLines(2023, 9).sints().sumOfY { it.distinct().singleOrNull() ?: it.first() - call(it.zipWithNext { a, b -> b - a }) } log 2
}



fun main() {
	println("Day 09: ")
	part1()
	part2()
}
