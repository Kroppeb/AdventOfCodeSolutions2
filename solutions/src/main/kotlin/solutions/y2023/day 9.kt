@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d09


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


private val xxxxx = Clock(6, 3)


private fun part1() {
	var data = getLines(9).sints()

	data.map {
		extrapolate(it) log 0
	}.sum() log 1
}

fun extrapolate(ix: List<Sint>): Sint {
	ix log 0
	if (ix.distinct().size > 1) {
		val xx = extrapolate(ix.zipWithNext { a, b -> b - a })
		return ix.last() + xx log "a"
	} else {
		return ix.first()
	}
}



private fun part2() {
	var data = getLines(9).sints()

	data.map {
		extrapolate2(it) log 0
	}.sum() log 1
}

fun extrapolate2(ix: List<Sint>): Sint {
	ix log 0
	if (ix.distinct().size > 1) {
		val xx = extrapolate(ix.zipWithNext { a, b -> b - a })
		return ix.first() - xx log "a"
	} else {
		return ix.first()
	}
}


fun main() {
	println("Day 09: ")
	part1()
	part2()
}
