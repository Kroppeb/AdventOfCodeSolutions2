@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024.d17.nt


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
import kotlin.system.measureTimeMillis
import kotlin.text.*
import kotlin.time.measureTime


private val xxxxx = Clock(6, 3)
//private val xxxxy = YCombSettings.useMemoization(true)
//private val xxxxz = LoggerSettings.logNonAnswers(false)


private fun part1() {
	var inp = getLines(2024, 17)
//	var inp = pre(17, 0)
	var (hob, ss) = inp.splitOnEmpty()

	val regs = hob.sint().mut()
	val insts = ss.sints()[0].windowed(2)

	fun getVal(i: Sint): Sint {
		return when (i.i) {

			4 -> regs[0]
			5 -> regs[1]
			6 -> regs[2]
			7 -> no()
			else -> i
		}
	}

	var pc = 0
	val out = mutableListOf<Sint>()
	while (pc < insts.size) {
		val (inst, opp) = insts[pc]
		when (inst.i) {
			0 -> regs[0] = regs[0] shr getVal(opp)
			1 -> regs[1] = regs[1] xor opp
			2 -> regs[1] = getVal(opp) mod 8
			3 -> {
				if (regs[0] != 0.s) {
					pc = opp.i
					continue
				}
			}

			4 -> regs[1] = regs[1] xor regs[2]
			5 -> {
				getVal(opp) mod 8 log 0
				out.add(getVal(opp) mod 8)
			}

			6 -> regs[1] = regs[0] shr getVal(opp)
			7 -> regs[2] = regs[0] shr getVal(opp)
		}
		pc += 2
	}

	out.joinToString(",") log 1


}

private fun part2() {
	var inp = getLines(2024, 17)
//	var inp = pre(17, 0)
	var (hob, ss) = inp.splitOnEmpty()

//	val regs = hob.sint()
//	val insts = ss.sints()[0].windowed(2)
//
//	loop { i ->
//		val rr = regs.mut()
//		rr[0] = i.s
//		val out = check(rr, insts)
//		if (out == insts){
//			i log 2
//			no()
//		}
//
//		if (i divBy 100000) i log 0
//	}

	val target = ss.sints()[0].reversed()
	var res = 0.s

	ch(0.s, target) log 2
//	res log 2

}

private fun ch(res: Sint, rem: List<Sint>): Sint? {
	if (rem.size == 0) {
		return res
	}

	for (x in 0..7) {
		val test = (res shl 3) + x
		if ((test mod 8) xor (0b100.s) xor ((test shr ((test xor 0b001.s mod 8))) mod 8) == rem[0]) {
			ch(test, rem.drop(1))?.let{return it}
		}
	}
	return null
}


fun main() {
	println("Day 17: ")
	part1()
	println(measureTime {
		part2()
	})
}
