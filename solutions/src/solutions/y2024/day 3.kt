@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024


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
	var inp = getLines(3).joinToString(separator = "\n")

	var on = false
	Regex("mul\\((\\d+),(\\d+)\\)").findAll(inp).map{
		it.groupValues[1].sint() * it.groupValues[2].sint()
	}.sumOf { it.i } log 1
}

private fun part2() {
	var inp = getLines(3).joinToString(separator = "\n")

	var on = true
	Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)").findAll(inp).map{
		it.value log 0
		when (it.value){
			"do()" -> {
				on = true
				0
			}
			"don't()" -> {
				on = false
				0
			}
			else -> {
				if (on)
					it.groupValues[1].int() * it.groupValues[2].int()
				else 0
			}
		}

	}.sumOf { it } log 2
}

fun main() {
	println("Day 3: ")
	part1()
	part2()
}
