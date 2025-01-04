@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d20


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



import com.sschr15.aoc.annotations.Memoize
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


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(20)
//	var inp = pre(20, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' }

	val base = (start to true).bfs({ it.first.v == 'E' }) { (p, c) ->
		if (c) {
			p.getQuadNeighbours().filter { it.v != '#' }.map { it to true }
		} else {
			p.getQuadNeighbours().map { it to true } + p.getQuadNeighbours().filter { it.v != '#' }.map { it to false }
		}
	}!! log 0


	hob.filter { it.v == '#' }.flatMap { it.getQuadNeighbours().orderedPairWise() }.filter { (a, b) ->
		a.v != '#' && b.v != '#'
	}

	val distToStart = mutableMapOf(start to 0.s)
	var queue = dequeOf(start)

	queue.drainFirst { c ->
		val cost = distToStart[c]!!
		for (neighbour in c.getQuadNeighbours()) {
			if (neighbour.v != '#' && neighbour !in distToStart) {
				distToStart[neighbour] = 1.s + cost
				queue.add(neighbour)
			}
		}
	}

	val distToEnd = mutableMapOf(hob.single { it.v == 'E' } to 0.s)
	queue = dequeOf(hob.single { it.v == 'E' })

	queue.drainFirst { c ->
		val cost = distToEnd[c]!!
		for (neighbour in c.getQuadNeighbours()) {
			if (neighbour.v != '#' && neighbour !in distToEnd) {
				distToEnd[neighbour] = 1.s + cost
				queue.add(neighbour)
			}
		}
	}

	hob.filter { it.v == '#' }.flatMap { it.getQuadNeighbours().orderedPairWise() }.filter { (a, b) ->
		a.v != '#' && b.v != '#'
	}.map { (a, b) -> distToStart[a]!! + distToEnd[b]!! }.filter { it < base.length - 100 }.size log 1
}

private fun part2() {
	var inp = getLines(2024, 20)
//	var inp = pre(20, 0)
	var hob = inp.grid()

	var start = hob.single { it.v == 'S' }

	val base = (start to true).bfs({ it.first.v == 'E' }) { (p, c) ->
		if (c) {
			p.getQuadNeighbours().filter { it.v != '#' }.map { it to true }
		} else {
			p.getQuadNeighbours().map { it to true } + p.getQuadNeighbours().filter { it.v != '#' }.map { it to false }
		}
	}!! log 0


	hob.filter { it.v == '#' }.flatMap { it.getQuadNeighbours().orderedPairWise() }.filter { (a, b) ->
		a.v != '#' && b.v != '#'
	}

	val distToStart = mutableMapOf(start to 0.s)
	var queue = dequeOf(start)

	queue.drainFirst { c ->
		val cost = distToStart[c]!!
		for (neighbour in c.getQuadNeighbours()) {
			if (neighbour.v != '#' && neighbour !in distToStart) {
				distToStart[neighbour] = 1.s + cost
				queue.add(neighbour)
			}
		}
	}

	val distToEnd = mutableMapOf(hob.single { it.v == 'E' } to 0.s)
	queue = dequeOf(hob.single { it.v == 'E' })

	queue.drainFirst { c ->
		val cost = distToEnd[c]!!
		for (neighbour in c.getQuadNeighbours()) {
			if (neighbour.v != '#' && neighbour !in distToEnd) {
				distToEnd[neighbour] = 1.s + cost
				queue.add(neighbour)
			}
		}
	}

	distToStart.entries.cartesianProduct(distToEnd.entries).filter { (s, e) ->
		(s.key - e.key).manDist() <= 20
	}.map { (s, e) ->
		(base.length - (s.value + e.value + (s.key - e.key).manDist()))
	}.filter { it >= 100 }.size log 2
}

fun main() {
	println("Day 20: ")
	part1()
	part2()
}
