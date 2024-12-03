@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d02


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
	var inp = getLines(2) .ints().filter { it.isStrictAscending() || it.isStrictDescending() }.filter{
		it.windowed(2).all{ (a,b) -> abs(b-a) <= 3}
	}.count() log 1
}

private fun part2() {
	var inp = getLines(2) .ints().filter{
		if ((it.isStrictAscending() || it.isStrictDescending()) && it.windowed(2).all{ (a,b) -> abs(b-a) <= 3})
			return@filter true

		for (i in it.indices){
			val xx = it.take(i) + it.drop(i + 1)
			if ((xx.isStrictAscending() || xx.isStrictDescending()) && xx.windowed(2).all{ (a,b) -> abs(b-a) <= 3}){
				return@filter true
			}
		}


		false
	}.count() log 2
}

fun main() {
	println("Day 2: ")
	part1()
	part2()
}
