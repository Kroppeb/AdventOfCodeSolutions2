package solutions.y2024

import log
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.point.Point
import kotlin.math.abs

private fun oneliners() {
	getLines(2024, 1).ints().transpose().map { it.sorted() }.transpose().sumOf { (a, b) -> abs(b - a) } log 1_001

	getLines(2024, 2).ints().filter { it.isStrictAscending() || it.isStrictDescending() }.count { it.windowed(2).all { (a, b) -> abs(b - a) <= 3 } } log 2_001

	getData(2024, 3).let { inp -> Regex("mul\\((\\d+),(\\d+)\\)").findAll(inp) }.map { it.groupValues[1].sint() * it.groupValues[2].sint() }.sumOf { it.i } log 3_001

	getLines(2024, 4).e().grid().sumOf { Point.ZERO.getOctNeighbours().count { step -> it.sequenceInc(step).take(4).map { it.v } == "XMAS".e() } } log 4_001
	getLines(2024, 4).e().grid().count { c -> c.v == 'A' && listOfNotNull(c.northWest?.v, c.northEast?.v, c.southEast?.v, c.southWest?.v).let { it.join() in listOf("MMSS", "SMMS", "SSMM", "MSSM") } } log 4_002

	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> listOfNotNull(a.offsetOrNull(a - b), b.offsetOrNull(b - a)) } }.toSet().size log 8_001
	getLines(2024, 8).grid().groupBy { it.v }.filterKeys { it != '.' }.values.flatMap { v -> v.pairWise().flatMap { (a, b) -> a.sequenceInc(a - b) + a.sequenceInc(b - a) } }.toSet().size log 8_002
}