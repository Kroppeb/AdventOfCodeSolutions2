@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d08c1


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
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3)


private fun part1() {
	val data = getLines(2023, 8)

	val cm = data.first().map { it == 'L' }.cyclic()
	val nodes = data.drop(2).words().associate { (a, b, c) -> a to (b to c) }

	var current = "AAA"
	var count = 0

	while (current != "ZZZ") {
		val (a, b) = nodes[current]!!
		current = if (cm[count]) a else b
		count++
	}

	count log 1
}


private fun part2() {
	val data = getLines(2023, 8)

	val cm = data.first().map { it == 'L' }
	val nodes = data.drop(2).words().associate { (a, b, c) -> a to (b to c) }

	val starts = nodes.keys.filter { it.endsWith("A") } log 0

	starts.map {
		var current = it
		var count = 0.s

		while (!current.endsWith("Z")) {
			val (a, b) = nodes[current]!!
			current = if (cm[count]) a else b
			count++

		}

		count
	}.log().lcm() log 2
}


fun main() {
	println("Day 08: ")
	part1()
	part2()
}
