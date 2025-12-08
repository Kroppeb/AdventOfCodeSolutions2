@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2025.d08


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

import log
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
	var inp = getLines(2025, 8)
	var hob = inp.sints().map{it.point3D()}

	val uf = UnionFind(hob)

	var count = 0.s
	for ((a,b) in hob.pairWise().sortedBy { (a,b) -> a.sqrDistTo(b) }) {
		count ++
		if (!uf.areJoined(a,b)) {
			uf.join(a,b)


		}
		if(count == 1000.s) {
			uf.getAllGroups().values.maxBy(3){it.size}.productOf { it.size } log 1
			return
		}
	}
	count log 0

}
//@SkipOverflowChecks
//@SkipDestructuringChecks
private fun part2() {
	var inp = getLines(2025 , 8)
	var hob = inp.sints().map{it.point3D()}

	val uf = UnionFind(hob)

	var count = 0.s
	var last2 = hob.first() to hob.first()
	for ((a,b) in hob.pairWise().sortedBy { (a,b) -> a.sqrDistTo(b) }) {
		count ++
		if (!uf.areJoined(a,b)) {
			uf.join(a,b)
			last2 = a to b

		}
//		if(count == 1000.s) {
//			uf.getAllGroups().values.maxBy(3){it.size}.productOf { it.size } log 1
//			return
//		}
	}
	count log 0
	last2.map { it.x }.toList().product() log 2

}




fun main() {
	println("Day  8: ")
	part1()
	part2()
}
