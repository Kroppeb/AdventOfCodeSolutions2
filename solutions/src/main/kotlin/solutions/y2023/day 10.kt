@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d10


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
import java.util.Deque
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

val LEFT = listOf('-', 'J', '7', 'S')
val UP = listOf('|', 'J', 'L', 'S')
val RIGHT = listOf('-', 'F', 'L', 'S')
val DOWN = listOf('|', 'F', '7', 'S')

private fun combined() {
	var data = getLines(2023, 10).e().grid()

	var start = data.find { it.v == 'S' }!!

	var connected = (data as Iterable<BoundedGridPoint<Char>>).map {
		it to buildList {

			if ((it.v in LEFT || it.v == 'S') && it.left?.v in RIGHT) add(it.left!!)
			if ((it.v in RIGHT || it.v == 'S') && it.right?.v in LEFT) add(it.right!!)
			if ((it.v in UP || it.v == 'S') && it.up?.v in DOWN) add(it.up!!)
			if ((it.v in DOWN || it.v == 'S') && it.down?.v in UP) add(it.down!!)
		}
	}.toMap()

	var count = 0
	var s = start
	var p: BoundedGridPoint<Char>? = null
	var seen = mutableSetOf<BoundedGridPoint<Char>>(start)
	while (true) {

		count++
		connected[s] // log 0
		val new = connected[s]!!.find { it != p }!!
		p = s
		s = new
		seen.add(s)
		if (s.v == 'S') break
	}

	count / 2 log 1

	var seeds = mutableSetOf<Point>()

	var dir = Clock.down
	for (i in seen) {
		(i to dir) log 0
		var v = i.v
		if (v == 'S') v = '-'
		when {
			v == '-' -> {
				seeds.add(i.p + dir)
			}

			v == '|' -> {
				seeds.add(i.p + dir)
			}

			v == '7' -> {
				when (dir) {
					Clock.down -> dir = Clock.left
					Clock.left -> dir = Clock.down
					Clock.up -> {
						seeds.add(i.p.up)
						seeds.add(i.p.right)
						dir = Clock.right
					}

					Clock.right -> {
						seeds.add(i.p.right)
						seeds.add(i.p.up)
						dir = Clock.up
					}
				}
			}

			v == 'L' -> {
				when (dir) {
					Clock.up -> dir = Clock.right
					Clock.right -> dir = Clock.up
					Clock.left -> {
						seeds.add(i.p.left)
						seeds.add(i.p.down)
						dir = Clock.down
					}

					Clock.down -> {
						seeds.add(i.p.down)
						seeds.add(i.p.left)
						dir = Clock.left
					}
				}
			}

			v == 'F' -> {
				when (dir) {
					Clock.down -> dir = Clock.right
					Clock.right -> dir = Clock.down
					Clock.up -> {
						seeds.add(i.p.up)
						seeds.add(i.p.left)
						dir = Clock.left
					}

					Clock.left -> {
						seeds.add(i.p.left)
						seeds.add(i.p.up)
						dir = Clock.up
					}
				}
			}

			v == 'J' -> {
				when (dir) {
					Clock.left -> dir = Clock.up
					Clock.up -> dir = Clock.left
					Clock.down -> {
						seeds.add(i.p.down)
						seeds.add(i.p.right)
						dir = Clock.right
					}

					Clock.right -> {
						seeds.add(i.p.right)
						seeds.add(i.p.down)
						dir = Clock.down
					}
				}
			}
		}
	}

	var inner = mutableSetOf<BoundedGridPoint<Char>>()
	var queue = ArrayDeque<Point>()
	queue.addAll(seeds)
	print(seeds.filter { it in data.bounds && data.getBp(it) !in seen })

	while (queue.isNotEmpty()) {
		var p = queue.removeFirst()
		if (p !in data.bounds) continue
		var bp = data.getBp(p)
		if (bp in seen) continue
		if (bp in inner) continue
		inner.add(bp)
		for (quadNeighbour in bp.getQuadNeighbours()) {
			queue.add(quadNeighbour.p)
		}

	}

	((0 toP 0) in inner.map { it.p }) log 0
	inner.size log 2
}


fun main() {
	println("Day 10: ")
	combined()
}
