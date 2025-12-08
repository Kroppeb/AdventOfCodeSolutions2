@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d07


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


import itertools.*
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import java.util.Comparator
import java.util.ArrayDeque
import java.util.PriorityQueue
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.math.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.system.exitProcess
import kotlin.text.*
import com.sschr15.aoc.annotations.SkipOverflowChecks
import com.sschr15.aoc.annotations.Memoize
import com.sschr15.aoc.annotations.SkipDestructuringChecks
import log


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(2025, 7)
	var hob = inp.grid()

	val seen = mutableSetOf<BGP<Char>>()

	val start = hob.find { it.v == 'S' }!!

	withFIFO(start){ p ->
		val b = p.down
		if (b != null){
			if (b.v == '^'){
				if (b !in seen){
					seen += b
					yield(b.left!!)
					yield(b.right!!)
				}

			} else {
				yield(b)
			}
		}
	}

	seen.size log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 7)
	var hob = inp.grid()

	val seen = mutableSetOf<BGP<Char>>()

	val start = hob.find { it.v == 'S' }!!
	val counts = mutableMapOf( start to 1.s)
	var endCount = 0.s

	withFIFO(start){ p ->
		val b = p.down
		val c = counts[p]!!
		if (b != null){
			if (b.v == '^'){
				if (b !in seen){
					seen += b
					if (counts[b.left!!] == null){
						yield(b.left!!)
					}
					if (counts[b.right!!] == null){
						yield(b.right!!)
					}
					counts[b.left!!] = c + (counts[b.left!!] ?: 0.s)
					counts[b.right!!] = c + (counts[b.right!!] ?: 0.s)

				}

			} else {
				if (counts[b] == null){
					yield(b)
				}
				counts[b] = c + (counts[b] ?: 0.s)

			}
		} else {
			endCount += c
		}
	}

	seen.size log 1
	endCount log 2
}



fun main() {
	println("Day  7: ")
	part1()
	part2()
}
