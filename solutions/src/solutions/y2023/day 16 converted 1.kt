@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d16c1


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
	var inp = getLines(2023, 16).e().grid()

	val seen = mutableSetOf<Pair<Point, Point>>()
	var dir = Clock.right

	var queue = dequeOf(inp.getBp(0 toP 0) to dir)

	queue.drainFirst { (pos, dir) ->
		if (pos.p to dir in seen) return@drainFirst
		seen += pos.p to dir

		when (pos.v) {
			'.' -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
			'/' -> {
				val nd = when (dir) {
					Clock.right -> Clock.up
					Clock.up -> Clock.right
					Clock.left -> Clock.down
					Clock.down -> Clock.left
					else -> error(dir)
				}
				inp.getBpOrNull(pos.p + nd)?.let { queue.addLast(it to nd) }
			}

			'\\' -> {
				val nd = when (dir) {
					Clock.right -> Clock.down
					Clock.down -> Clock.right
					Clock.left -> Clock.up
					Clock.up -> Clock.left
					else -> error(dir)
				}
				inp.getBpOrNull(pos.p + nd)?.let { queue.addLast(it to nd) }
			}

			'-' -> {
				when (dir) {
					Clock.right -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.up -> {
						inp.getBpOrNull(pos.p + Clock.right)?.let { queue.addLast(it to Clock.right) }
						inp.getBpOrNull(pos.p + Clock.left)?.let { queue.addLast(it to Clock.left) }
					}

					Clock.left -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.down -> {
						inp.getBpOrNull(pos.p + Clock.right)?.let { queue.addLast(it to Clock.right) }
						inp.getBpOrNull(pos.p + Clock.left)?.let { queue.addLast(it to Clock.left) }
					}

					else -> error(dir)
				}
			}

			'|' -> {
				when (dir) {
					Clock.right -> {
						inp.getBpOrNull(pos.p + Clock.up)?.let { queue.addLast(it to Clock.up) }
						inp.getBpOrNull(pos.p + Clock.down)?.let { queue.addLast(it to Clock.down) }
					}

					Clock.up -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.left -> {
						inp.getBpOrNull(pos.p + Clock.up)?.let { queue.addLast(it to Clock.up) }
						inp.getBpOrNull(pos.p + Clock.down)?.let { queue.addLast(it to Clock.down) }
					}

					Clock.down -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					else -> error(dir)
				}
			}
		}
	}

	seen.map { it.first }.toSet().size log 1
}


private fun part2() {
	var inp = getLines(2023, 16).e().grid()

	listOf(
		inp.bounds.leftEdge().map { it to Clock.right },
		inp.bounds.rightEdge().map { it to Clock.left },
		inp.bounds.topEdge().map { it to Clock.down },
		inp.bounds.bottomEdge().map { it to Clock.up },
	).flatMap { it.map { extracted(inp, it.first, it.second) } }.max() log 2
}

private fun extracted(inp: SimpleGrid<Char>, start: Point, dir: Point): Sint {
	val seen = mutableSetOf<Pair<Point, Point>>()
	val queue = dequeOf(inp.getBp(start) to dir)

	queue.drainFirst { (pos, dir) ->
		if (pos.p to dir in seen) return@drainFirst
		seen += pos.p to dir

		when (pos.v) {
			'.' -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
			'/' -> {
				val nd = when (dir) {
					Clock.right -> Clock.up
					Clock.up -> Clock.right
					Clock.left -> Clock.down
					Clock.down -> Clock.left
					else -> error(dir)
				}
				inp.getBpOrNull(pos.p + nd)?.let { queue.addLast(it to nd) }
			}

			'\\' -> {
				val nd = when (dir) {
					Clock.right -> Clock.down
					Clock.down -> Clock.right
					Clock.left -> Clock.up
					Clock.up -> Clock.left
					else -> error(dir)
				}
				inp.getBpOrNull(pos.p + nd)?.let { queue.addLast(it to nd) }
			}

			'-' -> {
				when (dir) {
					Clock.right -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.up -> {
						inp.getBpOrNull(pos.p + Clock.right)?.let { queue.addLast(it to Clock.right) }
						inp.getBpOrNull(pos.p + Clock.left)?.let { queue.addLast(it to Clock.left) }
					}

					Clock.left -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.down -> {
						inp.getBpOrNull(pos.p + Clock.right)?.let { queue.addLast(it to Clock.right) }
						inp.getBpOrNull(pos.p + Clock.left)?.let { queue.addLast(it to Clock.left) }
					}

					else -> error(dir)
				}
			}

			'|' -> {
				when (dir) {
					Clock.right -> {
						inp.getBpOrNull(pos.p + Clock.up)?.let { queue.addLast(it to Clock.up) }
						inp.getBpOrNull(pos.p + Clock.down)?.let { queue.addLast(it to Clock.down) }
					}

					Clock.up -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					Clock.left -> {
						inp.getBpOrNull(pos.p + Clock.up)?.let { queue.addLast(it to Clock.up) }
						inp.getBpOrNull(pos.p + Clock.down)?.let { queue.addLast(it to Clock.down) }
					}

					Clock.down -> inp.getBpOrNull(pos.p + dir)?.let { queue.addLast(it to dir) }
					else -> error(dir)
				}
			}
		}
	}

	return seen.map { it.first }.toSet().size.s
}

fun main() {
	println("Day 16: ")
	part1()
	part2()
}
