@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d05c2


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



import LoggerSettings
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

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
import java.util.Deque
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
private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2023, 12).map { line ->
		val (d, v) = line.split2(" ")

		val vals = v.sints()

		yComb(d.e(), vals) { cs, ix ->
			if (cs.size == 0) {
				if (ix.size == 0) {
					ret(1.s)
				} else {
					ret(0.s)
				}
			}

			when (cs.first()) {
				'.' -> retCall(cs.drop(1), ix)
				'#' -> {
					if (ix.size == 0) {
						ret(0.s)
					}

					val x = ix[0]
					if (cs.size < x) ret(0.s)
					for (i in 0 until x) {
						if (cs[i] == '.') ret(0.s)
					}
					if (cs.size == x.i) {
						if (ix.size == 1) ret(1.s)
						ret(0.s)
					}
					if (cs[x] == '#') ret(0.s)
					ret(call(cs.drop(x + 1), ix.drop(1)))
				}

				'?' -> {
					val x: Sint = call(cs.drop(1), ix)
					x + call(listOf('#') + cs.drop(1), ix)
				}

				else -> error("zz")
			}
		} log 0
	}.sum() log 1
}


private fun part2() {
	var inp = getLines(2023, 12).map { line ->
		val (d, v) = line.split2(" ")

		val vals = v.sints()

		yComb(listOf(d).repeat(5).joinToString("?").e(), vals.repeat(5)) { cs, ix ->
			if (cs.size == 0) {
				if (ix.size == 0) {
					ret(1.s)
				} else {
					ret(0.s)
				}
			}

			when (cs.first()) {
				'.' -> retCall(cs.drop(1), ix)
				'#' -> {
					if (ix.size == 0) {
						ret(0.s)
					}

					val x = ix[0]
					if (cs.size < x) ret(0.s)
					for (i in 0 until x) {
						if (cs[i] == '.') ret(0.s)
					}
					if (cs.size == x.i) {
						if (ix.size == 1) ret(1.s)
						ret(0.s)
					}
					if (cs[x] == '#') ret(0.s)
					ret(call(cs.drop(x + 1), ix.drop(1)))
				}

				'?' -> {
					val x: Sint = call(cs.drop(1), ix)
					x + call(listOf('#') + cs.drop(1), ix)
				}

				else -> error("zz")
			}
		} log 0
	}.sum() log 2
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}
