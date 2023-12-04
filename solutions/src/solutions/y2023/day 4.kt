@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023


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


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2023, 4).sumOf{line ->
		line.substringAfter(":").split2("|").on{a,b ->
			val p = a.ints().intersect(b.ints()).count() log 0

			val x = if (p > 0) 2.pow(p - 1) else 0
			x
		}
	} log 1

}


private fun part2() {
	var mul = mapOf<Int, Sint>().default{1.s}

	var data = getLines(2023, 4).sumOf{line ->
		val (x,y) = line.split2(":")
		val xx = x.int()
		val m = mul[xx]
		y.split2("|").on{a,b ->
			val p = a.ints().intersect(b.ints()).count() log 0

			for (i in 1..p) {
				mul[xx + i] += m
			}

//			val x = if (p > 0) 2.pow(p - 1)  else 0
			m
		}
	} log 1

}


fun main() {
	println("Day 04: ")
	part1()
	part2()
}
