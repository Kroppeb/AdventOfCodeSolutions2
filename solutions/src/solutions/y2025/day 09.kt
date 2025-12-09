@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d09


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


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)




//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part1() {
	var inp = getLines(9)
	var hob = inp.point()

	val l = (hob.windowed(2)+ listOf(hob.last(), hob.first()).g())
		l.sumOf{(a,b) -> a.x * b.y - b.x * a.y } log 0
	val ll = l.map{(a,b) -> a toL b}.also{it.map{it.step} log 0}

	hob.pairWise().map{it.toList().bounds()}
		.filter{b ->
			val r = b.retract(1)
			hob.none { it in r } && ll.none { it.start + it.step in r }&& ll.none { it.end - it.step in r }
		}.sortedByDescending { it.area }
		.first { b ->
			val mb = minOf(b.xs.sizeS, b.ys.sizeS)
			val r = b.retract(1)

			ll.map{ l1->
				generateSequence(l1.start){
					it + l1.step * mb
				}.takeWhile { it in l1 }.none { it in r }
			}.all{it}
		}.let{it log 0}
		.let { it.area } log 1
}


fun main() {
	println("Day  9: ")
	part1()
}
