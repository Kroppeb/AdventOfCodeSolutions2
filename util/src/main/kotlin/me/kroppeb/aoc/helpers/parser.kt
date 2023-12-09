//package me.kroppeb.aoc.helpers
//
//import me.kroppeb.aoc.helpers.collections.list.*
//import me.kroppeb.aoc.helpers.sint.Sint
//import me.kroppeb.aoc.helpers.sint.s
//
//
//fun interface ParserBuilder<T> {
//	fun parse(s: String): Pair<T, String>
//}
//
//val NIL: ParserBuilder<Het0> = ParserBuilder { null to it }
//
//
//fun <T> String.parse(parser: ParserBuilder<Het0>.() -> ParserBuilder<T>): T {
//	return NIL.parser().parse(this).first
//}
//
//fun <T> combine(l: Het0, r: T): Het1<T> = Het1(r)
//fun <A, T> combine(l: Het1<A>, r: T): Het2<A, T> = l.a toH r
//
//private val regexInt = Regex("""(-?\d+)(.*)""")
//private fun splitInt(s: String): Pair<Sint, String> {
//	val match = regexInt.matchEntire(s) ?: throw IllegalArgumentException("Invalid int: $s")
//	return match.groupValues[1].toLong().s to match.groupValues[2]
//}
//
//fun Het0.int():ParserBuilder<Het1<Sint>> = ParserBuilder {
//	val (_, remaining)
//}
//
//
//fun <T> Het0.split(s: String, left: ParserBuilder<Het0>.() -> ParserBuilder<T>) = ParserBuilder {
//	val
//}
//
