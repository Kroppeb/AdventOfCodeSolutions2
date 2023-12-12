@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.infi.y2023


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



import LoggerSettings
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


private val xxxxx = Clock(3, 12)
private val xxxxy = YCombSettings.useMemoization(false)
private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(9900, 1).map { line ->
		sqrt(line.points().maxBy { it.sqrDist() }.sqrDist().i.toDouble()) log 0
//		0.s
	}.sum().let { floor(it).toLong().s } log 1
}


private fun part2() {
	var inp = getLines(9900, 1).map { line ->
		val points = line.points()
		val centers = points.flatMapIndexed { i1, a ->
			points.drop(i1 + 1).flatMapIndexed { i2, b ->
				points.drop(i1 + i2 + 2).map { c ->
					// find the center of the circle through a,b,c
					val u = b.sqrDist().d
					val v = (a.sqrDist().d - u) / 2.0
					val w = (u - c.sqrDist().d) / 2.0
					val ab = a - b
					val bc = b - c
					val cross = (a - b).cross(b - c).d

//					if (cross < 0.0000001) return@map Double.MAX_VALUE

					val cx = (v * (bc.y.d) - w * (ab.y.d)) / cross
					val cy = (w * (ab.x.d) - v * (bc.x.d)) / cross

					cx to cy
				}
			}
		} + points.pairWise().map{(a,b) -> (a.x.d + b.x.d) / 2 to (a.y.d + b.y.d) / 2}
		centers.minOf { (cx, cy) -> points.maxOf{sqrt((cx - it.x.d).pow(2) + (cy - it.y.d).pow(2))} } log 0
	}.sum().let { floor(it).toLong().s } log 2
}


fun main() {
	println("Infi 2023: ")
	// Note that this is using my utils and stuff, they are aimed at getting an answer fast, at the cost of anything that isn't needed for that
	// AoC doesn't use doubles AFAIK so handling these doubles is a bit more work
	part1()
	part2()
}
