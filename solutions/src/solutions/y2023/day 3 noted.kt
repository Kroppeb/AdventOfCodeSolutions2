@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d03noted


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

// Editor note: This is irrelevant on most days, it specifies what "right" and "down" means
// for the coordinate system. Here it specifies that positive x is down (6) and positive y is right (3)
private val xxxxx = Clock(6, 3);


private fun part1() {
	// Editor note: Don't worry about 'e()', this line just makes a grid of chars from the input
	// (ignoring the last empty line, if it exists)
	val data = getLines(2023, 3).e().grid()

	// Editor note: This was supposed to be a set.
	// Because it wasn't, my solution takes 40 seconds, instead of 2.5 seconds.
	val sym = data.filter { it.v != '.' && it.v !in '0'..'9' }

	val seen = mutableSetOf<BoundedGridPoint<Char>>()
	val gears = mutableMapOf<Point, List<Int>>() // ADDED IN PART 2

	// Editor note: This is a "smart" number, if it overflows it will raise an error instead of silently
	// wrapping around and giving you the wrong answer. It uses a Long under the hood
	var res = 0.s

	// Editor note: when iterating over a grid, you get BoundedGridPoints. These are like
	// regular points, but they have a value, and their "left", "right", "up" and "down" methods return
	// null if they are out of bounds.
	for (i in data) {
		if (!i.v.isDigit() || i in seen) continue

		var number = ""
		// Editor note: tas is a function that returns a new mutable set (or list or map)
		// that has the same type as the original, but is empty.
		// This is useful as it means I don't have to type out the type again
		val nodes = seen.tas()

		var x = i
		while (x.v in '0'..'9') {
			number += x.v
			seen += x
			nodes += x
			// Editor note: When right is null, we've reached the end of the grid, and therefor the end of the number
			x = x.right ?: break
		}

		val neig = nodes.flatMap { it.getOctNeighbours() }

		// Editor note: this is "short" for `neig.any{it in sym}`.
		// After today's incident it will even warn me if I pass in
		if (neig anyIn sym) {
			res += number.toInt()
		}

		// ADDED IN PART 2
		for (n in neig.toSet()) {
			if (n.v == '*') {
				var l = gears[n.p] ?: mutableListOf()
				// Editor note: Bug: This creates a new list each time, which is bad
				// It isn't hot enough, nor are the list big for this to be a bottleneck,
				// but it still isn't what I intended
				l += number.toInt()
				gears[n.p] = l
			}
		}
	}

	// Editor note: `x log y` will print x, if y is 1 or 2, and copy it to the clipboard during the contest
	// Due to an error of mine yesterday, it only copied in the "solutions" module instead of the "today" module
	res log 1
	gears.values.filter { it.size == 2 }.sumOf { it.product() } log 2 // PART 2
}


fun main() {
	val startTime = System.nanoTime()
	println("Day 03: ")
	part1()
	println("Time: ${(System.nanoTime() - startTime) / 1e6}ms")
}
