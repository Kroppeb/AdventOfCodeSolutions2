@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023


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
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2023, 18)

	var points = msop()
	var p = 0 toP 0
	for(line in inp) {
		var (a,b,c) = line.split(' ')
		var np = p + (a[0].toPoint() * b.toSint())
		points += p toL np
		p = np log 0
	}

	var filling = points.toMutableSet()

	withFIFO(-1 toP -1){
		if (it !in filling) {
			yieldFrom(it.getQuadNeighbours())
		}

		filling += it
	}

	filling.size log 1


}


private fun part2() {
	var inp = getLines(2023, 18)

	var lines = mutableListOf<Line>()
	var p = 0 toP 0
	for(line in inp) {
		var (a,b,c) = line.split(' ')
		var bb = c.drop(2).take(5)
		val xx =c.drop(2).drop(5).take(1)
		var cc = when(xx) {
			"0" -> 'R'
			"1" -> 'D'
			"2" -> 'L'
			"3" -> 'U'
			else -> error(xx)
		}

		var np = p + (cc.toPoint() * bb.toInt(16))
		lines += p toL np
		p = np log 0
	}

	val area = lines.map{it.first().x * it.last().y - it.first().y * it.last().x}.sum().abs() / 2 log 0
	val perimeter = lines.map { it.length }.sum() log 0

	val points = area - perimeter / 2 + 1 log 0
	points + perimeter log 2
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}
