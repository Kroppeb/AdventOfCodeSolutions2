@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d11


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
	var input = getLines(2023, 11)
	var data = input.e()

	data = data.flatMap { if (it.all{it == '.'}) listOf(it, it)  else listOf(it)}.transpose()
	data = data.flatMap { if (it.all{it == '.'}) listOf(it, it)  else listOf(it)}.transpose()

	var d = data.grid()

	d.filter{it.v == '#'}.pairWise().map{(a,b) -> a.p.manDistTo(b.p)}.sum() log 1


}


private fun part2() {
	var input = getLines(2023, 11)
	var data = input.e().grid()

	var rows = data.bounds.xs.toSet().toMutableSet()
	var cols = data.bounds.ys.toSet().toMutableSet()

	for (i in data.filter{it.v == '#'}) {
		rows.remove(i.p.x)
		cols.remove(i.p.y)
	}

	data.filter{it.v == '#'}.pairWise().map{(a,b) -> a.p.manDistTo(b.p) +
		( (listOf(a.p.x, b.p.x).minMaxRange().count{it in rows})+ (listOf(a.p.y, b.p.y).minMaxRange().count{it in cols})) * (1000000 - 1)}.sum() log 1


}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}
