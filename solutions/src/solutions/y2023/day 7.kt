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


private val xxxxx = Clock(6, 3)


private fun part1() {
	var data = getLines(7).map { line ->
		val (hand, bd) = line.split2(" ")
		val bid = bd.sint()

		val h = hand.map {
			when (it) {
				'A' -> 14
				'K' -> 13
				'Q' -> 12
				'J' -> 1
				'T' -> 10
				else -> it - '0'
			}
		}


		val cc = ieee(h)
		bid to h[4] + 15 * (h[3] + 15 * (h[2] + 15 * (h[1] + 15 * (h[0] + 15 * cc))))
	}.sortedBy { it.second }

	data.withIdx().map { (i, it) ->
		i to it log 0
		(i + 1) * it.first
	}.sum() log 1



}

private fun ieee(
	h: List<Int>,
	x: Int = 0,
): Int {
	return if (x < 5) {
		if (h[x] == 1) {
			(2..14).map{
				val xx = if (it == 11){
					1
				} else {
					it
				}

				val h2 = h.toMutableList()
				h2[x] = xx

				ieee(h2, x + 1)
			}.max()
		} else {
			ieee(h, x + 1)
		}
	} else {
		i(h)
	}
}

private fun i(
	h: List<Int>
): Int {

	val hh = h.countEach()
	val cc = if (h.distinct().count() == 1) {
		6 // 5 of a kind
	} else if (hh.size == 2 && hh.values.any { it == 4.s }) {
		5 // 4 of a kind
	} else if (hh.size == 2 && hh.values.any { it == 3.s } && hh.values.any { it == 2.s }) {
		4 // full house
	} else if (hh.values.any { it == 3.s }) {
		3 // full house
	} else if (hh.values.count { it == 2.s } == 2) {
		2 // full house
	} else if (hh.values.count { it == 2.s } == 1) {
		1 // full house
	} else {
		0
	}
	return cc
}




fun main() {
	println("Day 07: ")
	part1()
}
