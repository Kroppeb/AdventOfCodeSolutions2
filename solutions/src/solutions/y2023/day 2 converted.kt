@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d02c1


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


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2).sumOf { line ->
		val (g, i) = line.split(":")

		val gid = g.int()

		for (p in i.split(';')) {
			for (pp in p.split(',')) {
				val pr = pp.trim()

				val a = pr.int()

				when (pr.split(' ')[1]) {
					"red" -> if (a > 12) return@sumOf 0
					"green" -> if (a > 13) return@sumOf 0
					"blue" -> if (a > 14) return@sumOf 0
					else -> error(pr.split(' ')[1])
				}

			}
		}


		gid
	} log 1

}


private fun part2() {
	var data = getLines(2).sumOf { line ->
		val (g, i) = line.split(":")

		val gid = g.int()


		var cr = 0
		var cg = 0
		var cb = 0

		for (p in i.split(';')) {
			for (pp in p.split(',')) {
				val pr = pp.trim()

				val a = pr.int()

				when (pr.split(' ')[1]) {
					"red" -> if (a > cr) cr = a
					"green" -> if (a > cg) cg = a
					"blue" -> if (a > cb) cb = a
					else -> error(pr.split(' ')[1])
				}

			}
		}


		cr * cb * cg
	} log 2

}


fun main() {
	println("Day 02: ")
	part1()
	part2()
}
