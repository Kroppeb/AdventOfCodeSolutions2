@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d15


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


import itertools.repeat
import itertools.s
import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.graph.floodFill
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.math.round
import kotlin.math.roundToLong


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2024, 15)
//	var inp = pre(15, 0)
	var (ax, bx) = inp.splitOnEmpty()
	var hob = ax.grid()
	var steps = bx.join().e()

	var robot = hob.single { it.v == '@' }.p

	var gg = hob.mutable()

	gg[robot] = '.'

	for (step in steps) {
		var dir = step.toPoint()
		var check = robot
		var updates = listOf(check).mut()

		while (true) {
			check += dir
			if (gg[check] == '#') {
				break
			}
			if (gg[check] == 'O') {
				updates.add(check)
			}
			if (gg[check] == '.') {
				for (i in updates.reversed()){
					gg[i + dir] = gg[i]
				}
				robot += dir
				break
			}
		}
	}

//	gg.bounds.print { gg[it] }
	gg.bounds.filter { gg[it] == 'O' }.sumOf { it.x * 100 + it.y } log 1
}

private fun part2() {
	var inp = getLines(2024, 15)
//	var inp = pre(15, 0)
	var (ax, bx) = inp.splitOnEmpty()
	var hob = ax.map {
		it.flatMap {
			when (it) {
				'O' -> "[]".e()
				'#' -> "##".e()
				'.' -> "..".e()
				'@' -> "@.".e()
				else -> no()
			}
		}
	}.grid()
	var steps = bx.join().e()

	var robot = hob.single { it.v == '@' }.p

	var gg = hob.mutable()

	gg[robot] = '.'

	for (step in steps) {
		var dir = step.toPoint()

		fun dd(check: Point): Boolean {
			when (gg[check]) {
				'[' -> {
					if (dir.isVertical())
						return dd(check + dir) && dd(check + dir.right)
					return dd(check + dir)
				}

				']' -> {
					if (dir.isVertical())
						return dd(check + dir) && dd(check + dir.left)
					return dd(check + dir)
				}

				'#' -> return false
				'.' -> return true
				else -> no()
			}
		}

		fun ff(pos: Point, char: Char) {
			pos to char log 0
			when (gg[pos]) {
				'[' -> {
					if (dir.isVertical()) {
						ff(pos + dir, '[')
						ff(pos + dir.r, ']')
						gg[pos] = char
						gg[pos.r] = '.'
					} else {
						ff(pos + dir, '[')
						gg[pos] = char
					}
				}

				']' -> {
					if (dir.isVertical()) {
						ff(pos + dir, ']')
						ff(pos + dir.l, '[')
						gg[pos] = char
						gg[pos.l] = '.'
					} else {
						ff(pos + dir, ']')
						gg[pos] = char
					}
				}

				'#' -> no()
				'.' -> {
					gg[pos] = char
				}

				else -> no()
			}
		}

		if (dd(robot + dir)) {
			ff(robot + dir, '.')
//			gg.bounds.print { gg[it] }
			robot += dir

		}
	}

//	gg.bounds.print { gg[it] }
	gg.bounds.filter { gg[it] == '[' }.sumOf { it.x * 100 + it.y } log 1
}


fun main() {
	println("Day 15: ")
	part1()
	part2()
}
