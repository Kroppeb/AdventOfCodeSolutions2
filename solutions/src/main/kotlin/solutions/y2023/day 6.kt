@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023


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
	var data = getLines(2023, 6).sints() log 0
	var (times, rec) = data

	(times zip rec).map{(t, r) ->
		(0..t).count { s->
			val tr = t - s
			val m = s * tr
			m > r
		}
	}.product() log 1
}

private fun part2() {
	var data = getLines(2023, 6).map{it.split(" ") log 0 }.map{it.join()}.sints() log 0
	var (times, rec) = data

	(times zip rec).map{(t, r) ->
		(0..t).count { s->
			val tr = t - s
			val m = s * tr
			m > r
		}
	}.product() log 2
}


fun main() {
	println("Day 06: ")
	val startTime = System.nanoTime()
	part1()
	part2()
	println("Time: ${(System.nanoTime() - startTime) / 1_000_000}ms")
}
