@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y2024

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import log
import kotlin.*
import kotlin.collections.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

private fun oneliners() {
	getLines(2024, 1).ints().transpose().map { it.sorted() }.transpose().sumOf { (a, b) -> abs(b - a) } log 1_001

	getLines(2024, 2).ints().filter { it.isStrictAscending() || it.isStrictDescending() }.count { it.windowed(2).all { (a, b) -> abs(b - a) <= 3 } } log 2_001

	Regex("mul\\((\\d+),(\\d+)\\)").findAll(getData(2024, 3)).map { it.groupValues[1].sint() * it.groupValues[2].sint() }.sumOf { it.i } log 3_001

	getLines(2024, 4).e().grid().sumOf { Point.ZERO.getOctNeighbours().count { step -> it.sequenceInc(step).take(4).map { it.v } == "XMAS".e() } } log 4_001
	getLines(2024, 4).e().grid().count { c -> c.v == 'A' && listOfNotNull(c.northWest?.v, c.northEast?.v, c.southEast?.v, c.southWest?.v).let { it.join() in listOf("MMSS", "SMMS", "SSMM", "MSSM") } } log 4_002

	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> listOfNotNull(a.offsetOrNull(a - b), b.offsetOrNull(b - a)) } }.toSet().size log 8_001
	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> a.sequenceInc(a - b) + a.sequenceInc(b - a) } }.toSet().size log 8_002

	getLines(2024, 10).digitsI().grid().filter { it.v == 0 }.sumOf { start -> floodFill(start) { p -> p.getQuadNeighbours().filter { it.v == p.v + 1 } }.count { it.v == 9 } } log 10_001
	getLines(2024, 10).digitsI().grid().filter { it.v == 0 }.sumOf { start -> floodFill(listOf(start)) { p -> p.last().getQuadNeighbours().filter { it.v == p.last().v + 1 }.map { p + listOf(it) } }.count { it.last().v == 9 } } log 10_002


	getLines(2024, 12).grid().map { floodFill(it) { it.getQuadNeighbours().filter { a -> a.v == it.v } } }.toSet().map2 { it.p }.sumOf { it.size.s * it.sumOf { a -> a.getQuadNeighbours().count { b -> b !in it } } } log 12_001
	getLines(2024, 12).grid().map { floodFill(it) { it.getQuadNeighbours().filter { a -> a.v == it.v } } }.toSet().map2 { it.p }.sumOf { it.size.s * it.sumOf { a -> a.getQuadNeighbours().count { b -> (b !in it && (a + (b - a).rotateClock() !in it || b + (b - a).rotateClock() in it)) } } } log 12_002


	getLines(2024, 16).grid().single { it.v == 'S' } to Clock.east pathTo { it.first.v == 'E' } dijkstra { (p, d) -> listOf(p + d to d to 1.s, p to d.rotateClock() to 1000.s, p to d.rotateAntiClock() to 1000.s).filter { (a, _) -> a.first.v != '#' } } log 16_001
	(getLines(2024, 16).grid().single { it.v == 'S' } to Clock.east).dijkstraAll({ it.first.v == 'E' }, { (p, d) -> listOf(p + d to d to 1.s, p to d.rotateClock() to 1000.s, p to d.rotateAntiClock() to 1000.s).filter { (a, _) -> a.first.v != '#' } }).getAllPaths().flatten().map { it.first.p }.toSet().size log 16_002

}