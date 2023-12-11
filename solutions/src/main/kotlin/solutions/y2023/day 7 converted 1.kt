@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d07c1


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
	var data = getLines(2023, 7).map { line ->
		val (hand, bd) = line.split2(" ")
		hand to bd.sint()
	}.sortedBy { (hand, bid) ->
		val h = hand.map {
			when (it) {
				'A' -> 14
				'K' -> 13
				'Q' -> 12
				'J' -> 11
				'T' -> 10
				else -> it - '0'
			}
		}

		val hh = h.countEach()

		val cc = when {
			hh.values.any { it == 5.s } -> 6 // 5 of a kind
			hh.values.any { it == 4.s } -> 5 // 4 of a kind
			hh.values.any { it == 3.s } && hh.values.any { it == 2.s } -> 4 // full house
			hh.values.any { it == 3.s } -> 3 // 3 of a kind
			hh.values.count { it == 2.s } == 2 -> 2 // 2 pairs
			hh.values.count { it == 2.s } == 1 -> 1 // 1 pair
			else -> 0
		}
		sortKey(cc to h)
	}

	data.map { it.second }.mapIndexed { index, it ->
		(index + 1) * it
	}.sum() log 1


}

private fun part2() {
	var data = getLines(2023, 7).map { line ->
		val (hand, bd) = line.split2(" ")
		hand to bd.sint()
	}.sortedBy { (hand, bid) ->
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


		val cc = yComb(h, 0) { hs, x ->
			when {
				x >= 5 -> {
					val hh = hs.countEach()
					when {
						hh.values.any { it == 5.s } -> 6 // 5 of a kind
						hh.values.any { it == 4.s } -> 5 // 4 of a kind
						hh.values.any { it == 3.s } && hh.values.any { it == 2.s } -> 4 // full house
						hh.values.any { it == 3.s } -> 3 // 3 of a kind
						hh.values.count { it == 2.s } == 2 -> 2 // 2 pairs
						hh.values.count { it == 2.s } == 1 -> 1 // 1 pair
						else -> 0
					}
				}

				hs[x] > 1 -> call(hs, x + 1)
				else -> (2..14).map {
					hs.copyWith(x, if (it == 11) 1 else it)
				}.maxOfC(x + 1)
			}
		}
		sortKey(cc to h)
	}

	data.map { it.second }.mapIndexed { index, it ->
		(index + 1) * it
	}.sum() log 2
}


fun main() {
	println("Day 07: ")
	part1()
	part2()
}
