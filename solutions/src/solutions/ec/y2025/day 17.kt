@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.ec.y2025.d17


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
import log
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
import kotlin.text.*


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)




private fun part1() {
	var inp = getLines("ec", 2025, 17, 1)
//	var inp = pre(19, 0)
	var hob = inp.grid() log 0

	var v = hob.find{it.v == '@'}!!
	hob.filter{it.v != '@' && it.p.sqrDistTo(v.p) <= 100}.map{it.v.toString().sint()}.sum() log 1


}

private fun part2() {
	var inp = getLines("ec", 2025, 17, 2)
//	var inp = pre(19, 0)
	var hob = inp.grid() log 0

	var v = hob.find{it.v == '@'}!!
	var mx = 0.s
	var p = 0
	for (i in 0..50) {
		val r = hob.filter { it.v != '@' && it.p.sqrDistTo(v.p) <= i * i && it.p.sqrDistTo(v.p) > (i - 1) * (i - 1)}.map { it.v.toString().sint() }.sum() log 0
		if (r > mx){
			mx = r
			p = i
		}

	}

	mx * p log 1


}

fun minr(p: Sint): Sint {
	loop{i ->
		if (i * i >= p){
			return i.s
		}
	}
}


private fun part3() {
	var inp = getLines("ec", 2025, 17, 3)
//	var inp = pre(19, 0)
	var hob = inp.grid()

	var v = hob.find{it.v == '@'}!!
	var s = hob.find{it.v == 'S'}!!

	val rr = dijkstraDist(
		s toH 0.s toH minr(s.sqrDistTo(v)),
		{(c, x), _ -> c == s && x > 0.s}
	) { (c, x, r), dist ->
		c.getQuadNeighbours().filter{it != v}.map{n ->
			var xx = x
			if((n.p - v.p).sign() == Clock.down || (c.p - v.p).sign() == Clock.down ){
				xx += (n.p - c.p).dot(Clock.right)
			}
			val nr = minr((n.p - v.p).sqrDist())
			n toH xx toH min(r, nr) to (if(n.v in '0'..'9') n.v.toString().s else 0.s)
		}.filter{it.first.c * 30 > dist && it.first.b >= 0.s}
	} log 0

	val xo = rr!!.path.map{it.a.p}.toSet()
	hob.bounds.print {
		if (it in xo){
			'!'
		} else {
			hob[it]
		}
	}




	rr!!.cost * (rr.start.c - 1) log 1
}

fun main() {
	println("Day 17: ")
	part1()
	part2()
	part3()
}
