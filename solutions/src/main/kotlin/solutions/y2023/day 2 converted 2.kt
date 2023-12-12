@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2023.d02c2


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
	var data = getLines(2023,2).sumOf {
		it.split2(": ").on { g, d ->
			val dd = d.split("; ").map{it.split(", ").map{it.split2(" ").on{a,b -> b to a.int()}}.toMap()}.reduce{a,b -> a.merge(b) {x,y -> max(x,y)} }
			if (dd.getOrDefault("red", 0) <= 12 && dd.getOrDefault("green", 0) <= 13 && dd.getOrDefault("blue", 0) <= 14)
				g.int()
			else
			0
		}} log 1
}


private fun part2() {
	var data = getLines(2023,2).sumOf {
		it.split2(": ").on { g, d ->
			val dd = d.split("; ").map{it.split(", ").map{it.split2(" ").on{a,b -> b to a.int()}}.toMap()}.reduce{a,b -> a.merge(b) {x,y -> max(x,y)} }
			dd.getOrDefault("red", 0) * dd.getOrDefault("green", 0) * dd.getOrDefault("blue", 0)
		}} log 2
}


fun main() {
	println("Day 02: ")
	part1()
	part2()
}
