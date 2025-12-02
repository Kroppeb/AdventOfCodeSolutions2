@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d01


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
	var inp = getLines(1)
	var hob = inp .map{it.first() to it.sint()}

	var count = 0.s
	hob.scan(50.s){i, (a, b) -> val n = if (a == 'L') {
		count += (b - i) / 100
		i - b

	} else {
		count += (b + i) / 100
		i + b
	}
		n mod 0..99 log 0
	}.map{it mod 0..99}.count{it == 0.s}  log 0

	count log 1  // EDITOR'S NOTE: THIS IS NOT THE ANSWER, THE PREVIOUS LOG 0 IS
}



//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(1)
	var hob = inp .map{it.first() to it.sint()}

	var count = 0.s
	hob.scan(50.s){i, (a, b) ->
		val n = if (a == 'L') {

			if (i == 0.s) count -= 1
		count += (b - i).floorDiv(100) + 1 log "b"
		i - b

	} else {
		count += (b + i) / 100 log "a"
		i + b
	}
		n mod 0..99 log 0
	}.map{it mod 0..99}.count{it == 0.s}  log 0

	count log 1
}

fun main() {
	println("Day 1: ")
	part1()
	part2()
}
