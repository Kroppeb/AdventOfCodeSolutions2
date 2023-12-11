@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d10c1


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
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)

val LEFT = listOf('-', 'J', '7', 'S')
val UP = listOf('|', 'J', 'L', 'S')
val RIGHT = listOf('-', 'F', 'L', 'S')
val DOWN = listOf('|', 'F', '7', 'S')

private fun combined() {
	val data = getLines(2023, 10).e().grid()

	val start = data.find { it.v == 'S' }!!

	val connected = data.associateWith {
		buildList {
			if (it.v in LEFT && it.left?.v in RIGHT) add(it.left!!)
			if (it.v in RIGHT && it.right?.v in LEFT) add(it.right!!)
			if (it.v in UP && it.up?.v in DOWN) add(it.up!!)
			if (it.v in DOWN && it.down?.v in UP) add(it.down!!)
		}
	}

	var s = start
	var p: BoundedGridPoint<Char>? = null
	val cycle = mutableSetOf<BoundedGridPoint<Char>>(start)
	while (true) {
		connected[s] // log 0
		val new = connected[s]!!.find { it != p }!!
		p = s
		s = new
		cycle.add(s)
		if (s.v == 'S') break
	}

	val perimeter = cycle.size
	perimeter / 2 log 1

	val area = cycle.zip(cycle.rotateRight(1)) { a, b -> a.p.x * b.p.y - a.p.y * b.p.x }.sum().abs() / 2 log 0
	val points = area - perimeter / 2 + 1 log 2

	//281

	run {
//		val corners = cycle//.filter { it.v in listOf('7', 'J', 'L', 'F') }
		// shoelace formula
		val perimeter = cycle.size
		val area = cycle.zip(cycle.drop(1) + cycle.take(1)) { a, b -> a.p.x * b.p.y - a.p.y * b.p.x }.sum().abs() / 2
		val points = area - perimeter / 2 + 1
		println(points)
	}
}


fun main() {
	println("Day 10: ")
	combined()
}
