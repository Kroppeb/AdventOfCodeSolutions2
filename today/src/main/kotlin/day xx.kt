@file:Suppress("PackageDirectoryMismatch", "UnusedImport")


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
	var data = getLines(3).e().grid()

	var sym = data.filter { it.v != '.' && it.v !in '0'..'9' }

	var seen = mutableSetOf<BoundedGridPoint<Char>>()

	var res = 0.s

	var gears = mutableMapOf<Point, List<Int>>()

	for (i in data){
		if(!i.v.isDigit() || i in seen) continue
//		i log 0

		var number = ""
		var nodes = seen.tas()

		var x = i
		while (x.v in '0'..'9'){
			number += x.v
			seen += x
			nodes += x
			x = x.right?:break
		}

		var neig = nodes.flatMap { it.getOctNeighbours() }

		if (neig anyIn sym) {
			res += number.toInt()
		}

		for (n in neig.toSet()) {
			if (n.v == '*') {
				var l = gears[n.p] ?: mutableListOf()
				l += number.toInt()
				gears[n.p] = l
			}
		}



	}

	res log 1
	gears.values.filter{it.size == 2}.sumOf { it.product() } log 2

}


fun main() {
	println("Day 03: ")
	part1()
}
