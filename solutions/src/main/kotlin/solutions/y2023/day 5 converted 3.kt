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

private fun part1() {
	var data = getLines(2023, 5).splitOnEmpty()

	var conv = data.drop(1).map { it.drop(1) }.sints()

	var lows = data.first().first().sints()

	for (mp in conv) {
		var res = lows.tas()
		var rem = lows.tas()

		for ((a, b, l) in mp) {
			rem = rem.tas()
			for (i in lows) {
				if (i in b until (b + l)) {
					res.add(a + i - b)
				} else {
					rem.add(i)
				}
			}
			lows = rem
		}
		lows = res + rem
	}
	lows.min() log 1
}


private fun part2() {
	var data = getLines(32023, 5).splitOnEmpty()

	var conv = data.drop(1).map { it.drop(1) }.sints()

	var lows = data.first().first().sints().chunked(2).map { (a, b) -> a until a + b }

	for (mp in conv) {
		var res = lows.tas()
		var rem = lows.tas()

		for ((a, b, l) in mp) {
			rem = rem.tas()
			for (r in lows) {

				val base = b until b + l
				for (i in base.fracture(r)) {
					if (i.intersect(base).isEmpty()) {
						rem.add(i)
					} else {
						res.add((a - b + i.first())..(a - b + i.last()))
					}
				}
			}
			lows = rem
		}

		lows = (res + rem).also { it.size log 0 }
	}

	lows.map { it.first() }.min() log 2
}


fun main() {
	println("Day 05: ")
	val startTime = System.nanoTime()
	part1()
	part2()
	println("Time: ${(System.nanoTime() - startTime) / 1_000_000}ms")

}
