@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2022.d02


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


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2022, 2)

	var score = 0

	for (line in inp) {
		when(line) {
			"A X" -> score += 3 + 1
			"B X" -> score += 0 + 1
			"C X" -> score += 6 + 1
			"A Y" -> score += 0 + 2
			"B Y" -> score += 6 + 2
			"C Y" -> score += 3 + 2
			"A Z" -> score += 6 + 3
			"B Z" -> score += 3 + 3
			"C Z" -> score += 0 + 3
		}
	}

	score log 1
}



private fun part2() {
	var inp = getLines(2022, 2)

	var score = 0

	for (line in inp) {
		when(line) {
			"A X" -> score += 0 + 3
			"B X" -> score += 0 + 1
			"C X" -> score += 0 + 2
			"A Y" -> score += 3 + 1
			"B Y" -> score += 3 + 2
			"C Y" -> score += 3 + 3
			"A Z" -> score += 6 + 2
			"B Z" -> score += 6 + 3
			"C Z" -> score += 6 + 1
		}
	}

	score log 2
}

fun main() {
	println("Day 2: ")
	part1()
	part2()
}
