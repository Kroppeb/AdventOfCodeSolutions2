@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d16c2


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
	floodFill(start to dir) { (pos, dir) ->
		when (inp.getBpOrNull(pos)?.v) {
			null -> listOf()
			'.' -> listOf(pos + dir to dir)
			'/' -> {
				val nd = when (dir) {
					Clock.right -> Clock.up
					Clock.up -> Clock.right
					Clock.left -> Clock.down
					Clock.down -> Clock.left
					else -> error(dir)
				}
				listOf(pos + nd to nd)
			}

			'\\' -> {
				val nd = when (dir) {
					Clock.right -> Clock.down
					Clock.down -> Clock.right
					Clock.left -> Clock.up
					Clock.up -> Clock.left
					else -> error(dir)
				}
				listOf(pos + nd to nd)
			}

			'-' -> {
				when (dir) {
					Clock.right -> listOf(pos + dir to dir)
					Clock.left -> listOf(pos + dir to dir)
					Clock.up -> listOf(
						pos.right to Clock.right,
						pos.left to Clock.left,
					)

					Clock.down -> listOf(
						pos.right to Clock.right,
						pos.left to Clock.left,
					)

					else -> error(dir)
				}
			}

			'|' -> {
				when (dir) {
					Clock.right -> listOf(
						pos.up to Clock.up,
						pos.down to Clock.down,
					)

					Clock.left -> listOf(
						pos.up to Clock.up,
						pos.down to Clock.down,
					)

					Clock.up -> listOf(pos + dir to dir)
					Clock.down -> listOf(pos + dir to dir)
					else -> error(dir)
				}
			}

			else -> error("")
		}
	}.map { it.first }.filter{it in inp.bounds} .distinct().size.s // This filter step is devious

fun main() {
	println("Day 16: ")
	part1()
	part2()
}
