@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d08


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
	var data = getLines(2023, 8)

	var cm = data.first().map{it == 'L'}
	var nodes = data.drop(2).words().associate { (a,b,c) -> a to (b to c) } log 0

	var current = "AAA"
	var count = 0

	while (current != "ZZZ") {
		val (a, b) = nodes[current]!!
		if (cm[count % cm.size]) {
			current = a
		} else {
			current = b
		}
		count++
	}

	count log 1



}


private fun part2() {
	var data = getLines(2023, 8)

	var cm = data.first().map { it == 'L' }
	var nodes = data.drop(2).words().associate { (a, b, c) -> a to (b to c) } log 0

	var current = nodes.keys.filter { it.endsWith("A") } log 0
	val pair = current.map{ff(it, nodes, cm)} log 0
	pair.reduce{a, b -> a / gcd(a,b) * b} log 2



}

private fun ff(
	current: String,
	nodes: Map<String, Pair<String, String>>,
	cm: List<Boolean>
): Sint {
	var current1 = current
	var count = 0.s

	while (!current1.endsWith("Z")) {
		val (a, b) = nodes[current1]!!
		val x =
			if (cm[count % cm.size]) {
				a
			} else {
				b
			}

		current1 = x
		count++

	}
	return count
}


fun main() {
	println("Day 08: ")
	part1()
	part2()
}
