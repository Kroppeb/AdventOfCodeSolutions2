@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d16c6


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
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.io.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2023, 16).e().grid()
	countCells(inp, 0 toP 0, Clock.right) log 1
}

private fun part2() {
	var inp = getLines(2023, 16).e().grid()

	listOf(
		inp.bounds.leftEdge().map { it to Clock.right },
		inp.bounds.rightEdge().map { it to Clock.left },
		inp.bounds.topEdge().map { it to Clock.down },
		inp.bounds.bottomEdge().map { it to Clock.up },
	).flatMap { it.map { (pos, dir) -> countCells(inp, pos, dir) } }.max() log 2
}

private fun countCells(inp: SimpleGrid<Char>, start: Point, dir: Point): Sint =
	floodFillY(start to dir) { (pos, dir) ->
		fun y(vararg dirs: Point) = yieldFrom(dirs.map { pos + it to it }.filter { it.first in inp.bounds })
		when (inp.getBpOrNull(pos)?.v) {
			null -> {}
			'.' -> y(dir)
			'/' -> y(dir.reflect(PointTransformers.NE))
			'\\' -> y(dir.reflect(PointTransformers.NW))
			'-' -> when {
				dir.isHorizontal() -> y(dir)
				dir.isVertical() -> y(Clock.right, Clock.left)
			}

			'|' -> when {
				dir.isHorizontal() -> y(Clock.up, Clock.down)
				dir.isVertical() -> y(dir)
			}
		}
	}.map { it.first }.toSet().size.s

fun main() {
	println("Day 16: ")
	part1()
	part2()
}
