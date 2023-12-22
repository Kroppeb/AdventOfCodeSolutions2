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
	var data = getLines(2023, 22).points3D().map { it.first() toB it.last() }

	var taken = mutableMapOf<Point3D, Int>()
	var high = mutableMapOf<Point, Int>()
	var locs = mutableListOf<Bounds3D>()
	var check = mutableListOf<Int>()

	for ((i, bb) in data.sortedBy { it.zs.first() }.withIndex()) {
		bb log 0
		if (bb.zs.first() == 1.s) {
			for (p in bb) {
				taken[p] = i
				high[p.x toP p.y] = bb.zs.last().i
			}
			locs.add(bb log 0)
		} else {
			val h = cartesianProductOf(bb.xs, bb.ys).maxOf { (x, y) -> high[x toP y] ?: 0 } + 1
			h log "h"
			val l = bb.zs.first() log "l"
			for (p in bb) {
				taken[p + (0 toP 0 toP (h - l))] = i
				high[p.x toP p.y] = bb.zs.last().i + (h - l.i)
			}
			locs.add(Bounds3D(bb.xs, bb.ys, (bb.zs.first() + (h - l.i))..(bb.zs.last() + (h - l.i))) log 0)
			check += i
		}
	}

	var c = 0
	var lock = mutableSetOf<Int>()
	var seen = mutableSetOf<Int>()
	for (i in check) {
		val bb = locs[i]
		val z = bb.zs.first()
		val p = cartesianProductOf(bb.xs, bb.ys).mapNotNull { (x, y) -> taken[x toP y toP (z - 1)] }.distinct() log 0
		if (p.size == 1) {
			lock += p
		}
	}

	(locs.size - lock.size) log 1


}

private fun part2() {
	var data = getLines(2023, 22).points3D().map { it.first() toB it.last() }

	var taken = mutableMapOf<Point3D, Int>()
	var high = mutableMapOf<Point, Int>()
	var locs = mutableListOf<Bounds3D>()
	var check = mutableListOf<Int>()

	for ((i, bb) in data.sortedBy { it.zs.first() }.withIndex()) {
		bb log 0
		if (bb.zs.first() == 1.s) {
			for (p in bb) {
				taken[p] = i
				high[p.x toP p.y] = bb.zs.last().i
			}
			locs.add(bb log 0)
		} else {
			val h = cartesianProductOf(bb.xs, bb.ys).maxOf { (x, y) -> high[x toP y] ?: 0 } + 1
			h log "h"
			val l = bb.zs.first() log "l"
			for (p in bb) {
				taken[p + (0 toP 0 toP (h - l))] = i
				high[p.x toP p.y] = bb.zs.last().i + (h - l.i)
			}
			locs.add(Bounds3D(bb.xs, bb.ys, (bb.zs.first() + (h - l.i))..(bb.zs.last() + (h - l.i))) log 0)
			check += i
		}
	}

	var c = 0
	var depend = mutableMapOf<Int, List<Int>>()
	for (i in check) {
		val bb = locs[i]
		val z = bb.zs.first()
		val p = cartesianProductOf(bb.xs, bb.ys).mapNotNull { (x, y) -> taken[x toP y toP (z - 1)] }.distinct() log 0
		depend[i] = p
	}

	var counts = 0.s

	for (d in locs.indices) {
		var removed = mutableSetOf(d)

		var p = 0
		while (p != removed.size) {
			p = removed.size

			for ((k, vs) in depend) {
				if (k in removed || vs.isEmpty()) continue
				if (vs.allIn(removed)) {
					removed += k
				}
			}
		}
		counts += removed.size - 1 log "cc"
	}

	counts log 2

//	for ((k, vs) in depend){
//		println("k$k -> ${vs.map{"k$it"}.joinToString(",")}")
//	}
}


fun main() {
	println("Day 22: ")
	part1()
	part2()
}
