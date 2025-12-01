@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025


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


private val xxxxx = Clock(3, 12)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(1)
	var hob = inp.sints().single().drop(1) log 0

	var segments = mutableListOf(mutableListOf(null, hob[0], null))

	o@ for (i in hob.b()){
		for (curr in segments) {
			if (i < curr[1]!! && curr[0] == null) {
				curr[0] = i
				segments log 0
				continue@o
			} else if (i > curr[1]!! && curr[2] == null) {
				curr[2] = i
				segments log 0
				continue@o
			}
		}
		segments.add(mutableListOf(null, i, null))
		segments log 0
	}

	segments.map{it[1]!!}.joinToString("") log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(1)
	var hob = inp.sints() log 0


	fun quali(h: List<Sint>): Sint {
		var segments = mutableListOf(mutableListOf(null, h[0], null))
		o@ for (i in h.b()) {
			for (curr in segments) {
				if (i < curr[1]!! && curr[0] == null) {
					curr[0] = i
//					segments log 0
					continue@o
				} else if (i > curr[1]!! && curr[2] == null) {
					curr[2] = i
//					segments log 0
					continue@o
				}
			}
			segments.add(mutableListOf(null, i, null))
//			segments log 0
		}

		return segments.map { it[1]!! }.joinToString("").toSint() log 0
	}

	val p = hob.map{quali(it.drop(1))}
	p.max() - p.min() log 1

}

//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part3() {
	var inp = getLines(1)
	var hob = inp.sints() log 0


	fun quali(h: List<Sint>): Pair<Sint, List<Sint>> {
		var segments = mutableListOf(mutableListOf(null, h[0], null))
		o@ for (i in h.b()) {
			for (curr in segments) {
				if (i < curr[1]!! && curr[0] == null) {
					curr[0] = i
//					segments log 0
					continue@o
				} else if (i > curr[1]!! && curr[2] == null) {
					curr[2] = i
//					segments log 0
					continue@o
				}
			}
			segments.add(mutableListOf(null, i, null))
//			segments log 0
		}

		return segments.map { it[1]!! }.joinToString("").toSint() to segments.map{
			it.joinToString("") {if (it == null) "" else it.toString() }.toSint()
		} log 0
	}

	val p = hob.map{it.first() to quali(it.drop(1))}
	val x = p.sortedWith(Comparator.comparing<Pair<Sint, Pair<Sint, List<Sint>>>?, Sint?> {-it.second.first}.thenComparing(
		Comparator.comparing({it.second.second}, Comparator { o1, o2 ->
			for ((l,r) in o1.zip(o2)){
				val c = -Comparator.comparingLong<Sint>({it.l}).compare(l,r)
				if (c == 0) continue
				return@Comparator c
			}
			0
		}
		)
	).thenComparing(Comparator.comparing { -it.first})) log 0
	x.map{it.first}.log(0).mapIndexed{i,id -> (i + 1) * id}.sum() log 1

}

fun main() {
	println("Day  5: ")
	part1()
	part2()
	part3()
}
