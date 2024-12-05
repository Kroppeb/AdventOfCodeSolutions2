@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d04


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
	var inp = getLines(2024, 4).e()

	val a = inp.sumOf { it.windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val b = inp.sumOf { it.reversed().windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val c = inp.transpose().sumOf { it.windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val d = inp.transpose().sumOf { it.reversed().windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val ll = inp[0].size
	val e = inp.mapIndexed { i, it -> listOf(' ') * i + it + listOf(' ') * (ll - i) }.transpose()
		.sumOf { it.log(0).windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val f = inp.mapIndexed { i, it -> listOf(' ') * (ll - i) + it + listOf(' ') * i }.transpose()
		.sumOf { it.log(0).windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val g = inp.mapIndexed { i, it -> listOf(' ') * i + it + listOf(' ') * (ll - i) }.transpose()
		.sumOf { it.log(0).reversed().windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	val h = inp.mapIndexed { i, it -> listOf(' ') * (ll - i) + it + listOf(' ') * i }.transpose()
		.sumOf { it.log(0).reversed().windowed(4).count { it == listOf('X', 'M', 'A', 'S') } } log 0
	a + b + c + d + e + f +g +h log 1
}

private fun part2() {
	var inp = getLines(2024, 4).e()

	val a = inp.grid().count{ c ->
		c.v == 'A' &&
		c.north?.west?.v == 'M' &&
			c.north?.east?.v == 'M' &&
			c.south?.west?.v == 'S' &&
			c.south?.east?.v == 'S'
	}
	val b = inp.reversed().grid().count{ c ->
		c.v == 'A' &&
			c.north?.west?.v == 'M' &&
			c.north?.east?.v == 'M' &&
			c.south?.west?.v == 'S' &&
			c.south?.east?.v == 'S'
	}
	val c = inp.transpose().grid().count{ c ->
		c.v == 'A' &&
			c.north?.west?.v == 'M' &&
			c.north?.east?.v == 'M' &&
			c.south?.west?.v == 'S' &&
			c.south?.east?.v == 'S'
	}
	val d = inp.transpose().reversed().grid().count{ c ->
		c.v == 'A' &&
			c.north?.west?.v == 'M' &&
			c.north?.east?.v == 'M' &&
			c.south?.west?.v == 'S' &&
			c.south?.east?.v == 'S'
	}
	a + b+c+d log 2
}

fun main() {
	println("Day 4: ")
	part1()
	part2()
}
