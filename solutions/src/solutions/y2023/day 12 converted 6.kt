@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d05c5


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
import log
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
	getLines(2023, 12).sumOf { line ->
		val (d, v) = line.split2(" ")

		yComb(listOf(d).repeat(5).joinToString("?").e(), v.sints().repeat(5)) { cs, ix ->
//		yComb(d.e(), v.sints()) { cs, ix ->
			when (cs.firstOrNull()) {
				null -> if (ix.isEmpty()) 1.s else 0.s
				'?' -> listOf('.', '#').sumOf{call(it.g() + cs.b(), ix)}
				'.' -> call(cs.b(), ix)
				'#' -> {
					val x = ix.getOrNull(0) ?: ret(0.s)
					if (cs.size < x) ret(0.s)
					if ('.' in cs[0 until x]) ret(0.s)
					when (cs.getOrNull(x)) {
						null -> if (ix.size == 1) 1.s else 0.s
						'#' -> 0.s
						else -> call(cs.drop(x + 1), ix.b())
					}
				}

				else -> no()
			}
		} log 0
	} log 1
}


fun main() {
	println("Day 12: ")
	part1()
}
