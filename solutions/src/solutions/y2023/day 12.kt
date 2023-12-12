@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d05


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

private fun part1() {
	var inp = getLines(2023, 12).map { line ->
		val (d, v) = line.split2(" ")

		val vals = v.sints()
		sols(d.e(), vals.repeat(5)) log 0
	}.sum() log 1
}

private fun part2() {
	var inp = getLines(2023, 12).map { line ->
		val (d, v) = line.split2(" ")

		val vals = v.sints()
		val p = listOf(d).repeat(5).joinToString("?") log 0
		sols(p.e(), vals.repeat(5)) log 0
	}.sum() log 2
}

fun sols(
	cs: List<Char>,
	ix: List<Sint>,
): Sint {
	if (cs.size == 0) {
		if (ix.size == 0) {
			return 1.s
		} else {
			return 0.s
		}
	}

	when(cs.first()) {
		 '.' -> {
			return sols(cs.drop(1), ix)
		}
		'#' -> {
			return hs(ix, cs).also{mem[ix to cs] = it} // NOTE the 'also' part was added during PART 2
		}
		'?' -> {// NOTE the 'also' part was added during PART 2
			return sols(cs.drop(1), ix) + hs(ix, cs).also{mem[ix to cs] = it}
		}
		else -> error("zz")
	}
}

val mem = mutableMapOf<Pair<List<Sint>, List<Char>>, Sint>()

private fun hs(
	ix: List<Sint>,
	cs: List<Char>
): Sint {
	// NOTE this wasn't here for PART 1 during my solve
	mem[ix to cs]?.let{return it}

	if (ix.size == 0) {
		return 0.s
	}

	val x = ix[0]
	if (cs.size < x) return 0.s
	for (i in 0 until x) {
		if (cs[i] == '.') return 0.s
	}
	if (cs.size == x.i) {
		if (ix.size == 1) return 1.s
		return 0.s
	}
	if (cs[x] == '#') return 0.s
	return sols(cs.drop(x + 1), ix.drop(1))
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}
