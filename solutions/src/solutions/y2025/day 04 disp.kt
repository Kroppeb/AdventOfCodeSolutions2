@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d04disp

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
	var inp = getLines(2025, 4)

	var hob = inp .grid().count{it.v == '@' && it.getOctNeighbours().count { it.v == '@' } < 4} log 1
}


//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025, 4)

	var hob = inp.e() .grid() // log 1

	var pp = hob

	pp.print { it.v.color(Colors.WHITE) }
	readln()

	while (true) {
		pp.print { it.v.color(if(it.v == '@' && it.getOctNeighbours().count { it.v == '@' } < 4)Colors.BRIGHT_WHITE else Colors.WHITE) }
		readln()
		val n = pp.mapGrid {
			if (it.v == '@' && it.getOctNeighbours().count { it.v == '@' } < 4){
				'.'
			} else {
				it.v
			}
		}
		if (n.count { it.v == '@' } == pp.count { it.v == '@' }){
			hob.count { it.v == '@' } - n.count { it.v == '@' } log 2
			break
		}
		pp = n
	}



}

fun main() {
	println("Day  4: ")
	part2()
}
