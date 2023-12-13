@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d03


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
private val xxxxz = LoggerSettings.logNonAnswers(false)

private fun part1() {
	var inp = getLines(2023, 13).splitOnEmpty().map { group ->
		val g = group.e().grid()

		val a = g.bounds.ys.toList().dropLast(1).firstOrNull { i ->
			g.bounds.ys.all { o ->
				val other = i * 2 + 1 - o
				if (other !in g.bounds.ys) true
				else g.bounds.xs.all { g.get(it toP o) == g.get(it toP other) }
			}
		}?.let { it + 1 } ?: 0.s

		val b = g.bounds.xs.toList().dropLast(1).firstOrNull { i ->
			g.bounds.xs.all { o ->
				val other = i * 2 + 1 - o
				if (other !in g.bounds.xs) true
				else g.bounds.ys.all { g.get(o toP it) == g.get(other toP it) }
			}
		}?.let { it + 1 } ?: 0.s

		a + b * 100 log 0
	}.sum() log 1
}

private fun part2() {
	var inp = getLines(2023, 13).splitOnEmpty().map { group ->
		val g = group.e().grid()

		val a = g.bounds.ys.toList().dropLast(1).firstOrNull { i ->
			g.bounds.ys.all { o ->
				val other = i * 2 + 1 - o
				if (other !in g.bounds.ys) true
				else g.bounds.xs.all { g.get(it toP o) == g.get(it toP other) }
			}
		}?.let { it + 1 } ?: 0.s

		val b = g.bounds.xs.toList().dropLast(1).firstOrNull { i ->
			g.bounds.xs.all { o ->
				val other = i * 2 + 1 - o
				if (other !in g.bounds.xs) true
				else g.bounds.ys.all { g.get(o toP it) == g.get(other toP it) }
			}
		}?.let { it + 1 } ?: 0.s

		val gg = g.items
		for (pTi in gg.indices) {
			for (pTj in gg[0].indices) {
				val gn = gg.toMutableList()
					.also { it[pTi] = it[pTi].toMutableList().also { it[pTj] = if (it[pTj] == '.') '#' else '.' } }
				val g2 = gn.grid()


				val a2 = g2.bounds.ys.toList().dropLast(1).firstOrNull { i ->
					if (i + 1 == a) return@firstOrNull false
					g2.bounds.ys.all { o ->
						val other = i * 2 + 1 - o
						if (other !in g2.bounds.ys) true
						else g2.bounds.xs.all { g2.get(it toP o) == g2.get(it toP other) }
					}
				}?.let { it + 1 } ?: 0.s

				val b2 = g2.bounds.xs.toList().dropLast(1).firstOrNull { i ->
					if (i + 1 == b) return@firstOrNull false
					g2.bounds.xs.all { o ->
						val other = i * 2 + 1 - o
						if (other !in g2.bounds.xs) true
						else g2.bounds.ys.all { g2.get(o toP it) == g2.get(other toP it) }
					}
				}?.let { it + 1 } ?: 0.s

				if (a2 == 0.s && b2 == 0.s) {
					continue
				}

				val xx = a2 + b2 * 100 log 0
				return@map xx
			}
		}

//		a + b * 100 log 0
		error("N")
	}.sum() log 2
}

fun main() {
	println("Day 13: ")
	part1()
	part2()
}
