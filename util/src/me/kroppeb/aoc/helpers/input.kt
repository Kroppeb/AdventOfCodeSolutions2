package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.grid.SimpleGrid
import me.kroppeb.aoc.helpers.grid.StrictGrid
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import java.io.FileNotFoundException

private val regexSusInt = Regex("""-?\d+-\d+""")
private val regexInt = Regex("""-?\d+""")
private val regexPosInt = Regex("""\d+""")
private val regexDigit = Regex("""\d""")
private val regexFloat = Regex("""-?\d+(?:\.\d+)?""")
private val regexPosFloat = Regex("""\d+(?:\.\d+)?""")
private val regexWord = Regex("""[a-zA-Z]+""")
private val regexAlphaNum = Regex("""[a-zA-Z0-9]+""")


public fun getInt(day: Int): Int {
	return regexInt.find(getData(day))!!.value.toInt()
}

public fun getPosInt(day: Int): PosInt {
	return regexPosInt.find(getData(day))!!.value.toInt()
}

public fun getLong(day: Int): Long {
	return regexInt.find(getData(day))!!.value.toLong()
}

public fun getPosLong(day: Int): PosLong {
	return regexPosInt.find(getData(day))!!.value.toLong()
}


public fun getDigit(day: Int): Digit {
	return regexDigit.find(getData(day))!!.value.toInt()
}

public fun getDouble(day: Int): Double {
	return regexFloat.find(getData(day))!!.value.toDouble()
}

public fun getPosDouble(day: Int): PosDouble {
	return regexPosFloat.find(getData(day))!!.value.toDouble()
}

public fun getWord(day: Int): Word {
	return regexWord.find(getData(day))!!.value
}

public fun getAlphaNum(day: Int): AlphaNum {
	return regexAlphaNum.find(getData(day))!!.value
}

private class Ugh;

public fun getData(year: Int, day: Int): String = getData(year * 100 + day)

public fun getData(day: Int): String =
		if (day > 100) {
			val y = day / 100
			val d = day % 100
			(Ugh::class.java.getResource("/$y/$d")
					?:Ugh::class.java.getResource("/$y/$d.txt"))
					.readText()
		} else {
			(Ugh::class.java.getResource("/$day")
					?:Ugh::class.java.getResource("/$day.txt"))
					.readText()
		}


public fun getData(type: String?, year: Int?, day: Int, part: Int?): String {
	val ts = type?:""
	val ys = year?.toString()?:""
	val ps = if(part == null) "" else "-$part"

	val paths = buildList {
		add("/$ts/$ys/$day$ps")
		add("/$ts/$ys/$day$ps.txt")
		if (day < 10) {
			add("/$ts/$ys/0$day$ps")
			add("/$ts/$ys/0$day$ps.txt")
		}
	}

	val resources = paths.mapNotNull { Ugh::class.java.getResource(it) }
	if (resources.isEmpty()) {
		throw FileNotFoundException("Could not find input file for `$ts:$ys:$day:${part?:""}`")
	} else if (resources.size > 1) {
		throw FileNotFoundException("Multiple possible input files for `$ts:$ys:$day:${part?:""}`")
	} else {
		return resources.single().readText()
	}
}

private var hasWarnedAboutSusInt = false
public fun getInts(day: Int): Ints {
	val input = getData(day)
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(input)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

public fun getPosInts(day: Int): PosInts {
	return regexPosInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

public fun getLongs(day: Int): Longs {
	return regexInt.findAll(getData(day)).map { it.value.toLong() }.toList()
}

public fun getPosLongs(day: Int): PosLongs {
	return regexPosInt.findAll(getData(day)).map { it.value.toLong() }.toList()
}

public fun getDigits(day: Int): Digits {
	return regexDigit.findAll(getData(day)).map { it.value.toInt() }.toList()
}

public fun getDoubles(day: Int): Doubles {
	return regexFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

public fun getPosDoubles(day: Int): PosDoubles {
	return regexPosFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

public fun getWords(day: Int): Words {
	return regexWord.findAll(getData(day)).map { it.value }.toList()
}

public fun getAlphaNums(day: Int): AlphaNums {
	return regexAlphaNum.findAll(getData(day)).map { it.value }.toList()
}

public fun getLines(day: Int): List<String> {
	val lines = getData(day).lines()
	if(lines.last().isEmpty())
		return lines.subList(0,lines.lastIndex)
	return lines
}

public fun getLines(year: Int, day: Int): List<String> {
	val lines = getData(year * 100 + day).lines()
	if(lines.last().isEmpty())
		return lines.subList(0,lines.lastIndex)
	return lines
}

public fun getLines(type: String, year: Int, day: Int, part:Int? = null): List<String> {
	val lines = getData(type, year, day, part).lines()
	if(lines.last().isEmpty())
		return lines.subList(0,lines.lastIndex)
	return lines
}

public fun getCGrid(day: Int): CGrid = getLines(day).grid()

/**
 * Read in Comma (',') Separated Values
 */
public fun getCSV(day: Int): CSV {
	return getLines(day).map { it.split(',') }
}
public typealias CSV = List<List<String>>

/**
 * Read in Semicolon (';') Separated Values
 */
public fun getSSV(day: Int): SSV {
	return getLines(day).map { it.split(';') }
}
public typealias SSV = List<List<String>>

/**
 * Read in Whitespace (' ' or '\t') Separated Values
 */
public fun getWSV(day: Int): WSV {
	return getLines(day).map { it.split(' ', '\t') }
}
public typealias WSV = List<List<String>>

public fun getIntCode(day: Int): IntCode{
	return getLongs(day).withIndex().associate { (i,v) -> i.toLong() to v }
}


public fun Iterable<Sint>.getPoint(): Point? {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return null
	val a = iterator.next()
	if (!iterator.hasNext()) return null
	return a toP iterator.next()
}

public fun Iterable<Sint>.getPoint3D(): Point3D? {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return null
	val a = iterator.next()
	if (!iterator.hasNext()) return null
	val b = iterator.next()
	if (!iterator.hasNext()) return null
	return a toP b toP iterator.next()
}

@JvmName("getPointInt")
public fun Iterable<Int>.getPoint() = map{it.s}.getPoint()
@JvmName("getPointInt3D")
public fun Iterable<Int>.getPoint3D() = map{it.s}.getPoint3D()
@JvmName("getPointLong")
public fun Iterable<Long>.getPoint() = map{it.s}.getPoint()
@JvmName("getPoint3DLong")
public fun Iterable<Long>.getPoint3D() = map{it.s}.getPoint3D()


public fun Iterable<Sint>.point() = this.getPoint()!!
public fun Iterable<Sint>.point3D() = this.getPoint3D()!!
@JvmName("pointInt")

public fun Iterable<Int>.point() = this.getPoint()!!
@JvmName("point3DInt")
public fun Iterable<Int>.point3D() = this.getPoint3D()!!
@JvmName("pointLong")
public fun Iterable<Long>.point() = this.getPoint()!!
@JvmName("point3DLong")
public fun Iterable<Long>.point3D() = this.getPoint3D()!!



public fun String.getInt(): Int? = regexInt.find(this)?.value?.toInt()
public fun String.getPosInt(): PosInt? = regexPosInt.find(this)?.value?.toInt()
public fun String.getSint(): Sint? = regexInt.find(this)?.value?.toLong()?.s
public fun String.getPosSint(): PosSint? = regexPosInt.find(this)?.value?.toLong()?.s
public fun String.getLong(): Long? = regexInt.find(this)?.value?.toLong()
public fun String.getPosLong(): PosLong? = regexPosInt.find(this)?.value?.toLong()
public fun String.getDigit(): Digit? = regexDigit.find(this)?.value?.toInt()
public fun String.getDouble(): Double? = regexFloat.find(this)?.value?.toDouble()
public fun String.getPosDouble(): PosDouble? = regexPosFloat.find(this)?.value?.toDouble()
public fun String.getWord(): Word? = regexWord.find(this)?.value
public fun String.getAlphaNum(): AlphaNum? = regexAlphaNum.find(this)?.value
public fun String.getPoint(): Point? = getSints().getPoint()
public fun String.getPoint3D(): Point3D? = getSints().getPoint3D()

@Deprecated("use getSints() instead")
public fun String.getInts(): List<Int> {
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(this)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(this).map { it.value.toInt() }.toList()
}
public fun String.getPosInts() = regexPosInt.findAll(this).map { it.value.toInt() }.toList()

public fun String.getSints(): List<Sint> {
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(this)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(this).map { it.value.toLong().s }.toList()
}
public fun String.getPosSints() = regexPosInt.findAll(this).map { it.value.toLong().s }.toList()
public fun String.getLongs(): List<Long> {
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(this)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(this).map { it.value.toLong() }.toList()
}
public fun String.getPosLongs(): Longs = regexPosInt.findAll(this).map { it.value.toLong() }.toList()
public fun String.getDigits(): Digits = regexDigit.findAll(this).map { it.value.toInt() }.toList()
public fun String.getDoubles(): Doubles = regexFloat.findAll(this).map { it.value.toDouble() }.toList()
public fun String.getPosDoubles(): PosDoubles = regexPosFloat.findAll(this).map { it.value.toDouble() }.toList()
public fun String.getWords(): Words = regexWord.findAll(this).map { it.value }.toList()
public fun String.getAlphaNums(): AlphaNums = regexAlphaNum.findAll(this).map { it.value }.toList()
public fun String.getPoints(): Points = getSints().chunked(2).filter{it.size == 2}.map{it.point()}
public fun String.getPoints3D(): Points3D = getSints().chunked(3).filter{it.size == 3}.map{it.point3D()}

public fun String.int(): Int = getInt()!!
public fun String.posInt(): Int = getPosInt()!!
public fun String.sint(): Sint = getSint()!!
public fun String.posSint(): Sint = getPosSint()!!
public fun String.long(): Long = getLong()!!
public fun String.posLong(): Long = getPosLong()!!
public fun String.digit(): Int = getDigit()!!
public fun String.double(): Double = getDouble()!!
public fun String.posDouble(): Double = getPosDouble()!!
public fun String.word(): String = getWord()!!
public fun String.alphaNum(): String = getAlphaNum()!!
public fun String.point(): Point = getPoint()!!
public fun String.point3D(): Point3D = getPoint3D()!!

@Deprecated("use sints() instead", ReplaceWith("getSints()"))
public fun String.ints(): List<Int> = getInts()
public fun String.posInts(): List<Int> = getPosInts()
public fun String.sints(): List<Sint> = getSints()
public fun String.posSints(): List<Sint> = getPosSints()
public fun String.longs(): List<Long> = getLongs()
public fun String.posLongs(): List<Long> = getPosLongs()
public fun String.digits(): List<Int> = getDigits()
public fun String.doubles(): List<Double> = getDoubles()
public fun String.posDoubles(): List<Double> = getPosDoubles()
public fun String.words(): List<String> = getWords()
public fun String.alphaNums(): List<String> = getAlphaNums()
public fun String.points(): List<Point> = getPoints()
public fun String.points3D(): List<Point3D> = getPoints3D()


public inline fun <T>String.ints(transform: (List<Int>) -> T): T = getInts().let(transform)
public inline fun <T>String.posInts(transform: (List<Int>) -> T): T = getPosInts().let(transform)
public inline fun <T>String.sints(transform: (List<Sint>) -> T): T = getSints().let(transform)
public inline fun <T>String.posSints(transform: (List<Sint>) -> T): T = getPosSints().let(transform)
public inline fun <T>String.longs(transform: (List<Long>) -> T): T = getLongs().let(transform)
public inline fun <T>String.posLongs(transform: (List<Long>) -> T): T = getPosLongs().let(transform)
public inline fun <T>String.digits(transform: (List<Int>) -> T): T = getDigits().let(transform)
public inline fun <T>String.doubles(transform: (List<Double>) -> T): T = getDoubles().let(transform)
public inline fun <T>String.posDoubles(transform: (List<Double>) -> T): T = getPosDoubles().let(transform)
public inline fun <T>String.words(transform: (List<String>) -> T): T = getWords().let(transform)
public inline fun <T>String.alphaNums(transform: (List<String>) -> T): T = getAlphaNums().let(transform)
public inline fun <T>String.points(transform: (List<Point>) -> T): T = getPoints().let(transform)
public inline fun <T>String.points3D(transform: (List<Point3D>) -> T): T = getPoints3D().let(transform)


public fun Iterable<String>.int():List<Int> = map{it.int()}
public fun Iterable<String>.posInt():List<Int> = map{it.posInt()}
public fun Iterable<String>.sint():List<Sint> = map{it.sint()}
public fun Iterable<String>.posSint():List<Sint> = map{it.posSint()}
public fun Iterable<String>.long():List<Long> = map{it.long()}
public fun Iterable<String>.posLong():List<Long> = map{it.posLong()}
public fun Iterable<String>.digit():List<Int> = map{it.digit()}
public fun Iterable<String>.double():List<Double> = map{it.double()}
public fun Iterable<String>.posDouble():List<Double> = map{it.posDouble()}
public fun Iterable<String>.word():List<String> = map{it.word()}
public fun Iterable<String>.alphaNum():List<String> = map{it.alphaNum()}
public fun Iterable<String>.point():List<Point> = map{it.point()}
public fun Iterable<String>.point3D():List<Point3D> = map{it.point3D()}

public fun Iterable<String>.ints(): List<List<Int>> = map{it.ints()}
public fun Iterable<String>.posInts(): List<List<Int>> = map{it.posInts()}
public fun Iterable<String>.sints(): List<List<Sint>> = map{it.sints()}
public fun Iterable<String>.posSints(): List<List<Sint>> = map{it.posSints()}
public fun Iterable<String>.longs(): List<List<Long>> = map{it.longs()}
public fun Iterable<String>.posLongs(): List<List<Long>> = map{it.posLongs()}
public fun Iterable<String>.digits(): List<List<Int>> = map{it.digits()}
public fun Iterable<String>.doubles(): List<List<Double>> = map{it.doubles()}
public fun Iterable<String>.posDoubles(): List<List<Double>> = map{it.posDoubles()}
public fun Iterable<String>.words(): List<List<String>> = map{it.words()}
public fun Iterable<String>.alphaNums(): List<List<String>> = map{it.alphaNums()}
public fun Iterable<String>.points(): List<List<Point>> = map{it.points()}
public fun Iterable<String>.points3D(): List<List<Point3D>> = map{it.points3D()}


public inline fun <T>Iterable<String>.ints(transform: (List<Int>) -> T): List<T> = map{it.ints(transform)}
public inline fun <T>Iterable<String>.posInts(transform: (List<Int>) -> T): List<T> = map{it.posInts(transform)}
public inline fun <T>Iterable<String>.sints(transform: (List<Sint>) -> T): List<T> = map{it.sints(transform)}
public inline fun <T>Iterable<String>.posSints(transform: (List<Sint>) -> T): List<T> = map{it.posSints(transform)}
public inline fun <T>Iterable<String>.longs(transform: (List<Long>) -> T): List<T> = map{it.longs(transform)}
public inline fun <T>Iterable<String>.posLongs(transform: (List<Long>) -> T): List<T> = map{it.posLongs(transform)}
public inline fun <T>Iterable<String>.digits(transform: (List<Int>) -> T): List<T> = map{it.digits(transform)}
public inline fun <T>Iterable<String>.doubles(transform: (List<Double>) -> T): List<T> = map{it.doubles(transform)}
public inline fun <T>Iterable<String>.posDoubles(transform: (List<Double>) -> T): List<T> = map{it.posDoubles(transform)}
public inline fun <T>Iterable<String>.words(transform: (List<String>) -> T): List<T> = map{it.words(transform)}
public inline fun <T>Iterable<String>.alphaNums(transform: (List<String>) -> T): List<T> = map{it.alphaNums(transform)}
public inline fun <T>Iterable<String>.points(transform: (List<Point>) -> T): List<T> = map{it.points(transform)}
public inline fun <T>Iterable<String>.points3D(transform: (List<Point3D>) -> T): List<T> = map{it.points3D(transform)}



@JvmName("int2") public fun Iterable<Iterable<String>>.int():List<List<Int>> = map{it.int()}
@JvmName("posInt2") public fun Iterable<Iterable<String>>.posInt():List<List<Int>> = map{it.posInt()}
@JvmName("sint2") public fun Iterable<Iterable<String>>.sint():List<List<Sint>> = map{it.sint()}
@JvmName("posSint2") public fun Iterable<Iterable<String>>.posSint():List<List<Sint>> = map{it.posSint()}
@JvmName("long2") public fun Iterable<Iterable<String>>.long():List<List<Long>> = map{it.long()}
@JvmName("posLong2") public fun Iterable<Iterable<String>>.posLong():List<List<Long>> = map{it.posLong()}
@JvmName("digit2") public fun Iterable<Iterable<String>>.digit():List<List<Int>> = map{it.digit()}
@JvmName("double2") public fun Iterable<Iterable<String>>.double():List<List<Double>> = map{it.double()}
@JvmName("posDouble2") public fun Iterable<Iterable<String>>.posDouble():List<List<Double>> = map{it.posDouble()}
@JvmName("word2") public fun Iterable<Iterable<String>>.word():List<List<String>> = map{it.word()}
@JvmName("alphaNum2") public fun Iterable<Iterable<String>>.alphaNum():List<List<String>> = map{it.alphaNum()}
@JvmName("point2") public fun Iterable<Iterable<String>>.point():List<List<Point>> = map{it.point()}
@JvmName("point3D2") public fun Iterable<Iterable<String>>.point3D():List<List<Point3D>> = map{it.point3D()}


@JvmName("ints2") public fun Iterable<Iterable<String>>.ints():List<List<List<Int>>> = map{it.ints()}
@JvmName("posInts2") public fun Iterable<Iterable<String>>.posInts():List<List<List<Int>>> = map{it.posInts()}
@JvmName("sints2") public fun Iterable<Iterable<String>>.sints():List<List<List<Sint>>> = map{it.sints()}
@JvmName("posSints2") public fun Iterable<Iterable<String>>.posSints():List<List<List<Sint>>> = map{it.posSints()}
@JvmName("longs2") public fun Iterable<Iterable<String>>.longs():List<List<List<Long>>> = map{it.longs()}
@JvmName("posLongs2") public fun Iterable<Iterable<String>>.posLongs():List<List<List<Long>>> = map{it.posLongs()}
@JvmName("digits2") public fun Iterable<Iterable<String>>.digits():List<List<List<Int>>> = map{it.digits()}
@JvmName("doubles2") public fun Iterable<Iterable<String>>.doubles():List<List<List<Double>>> = map{it.doubles()}
@JvmName("posDoubles2") public fun Iterable<Iterable<String>>.posDoubles():List<List<List<Double>>> = map{it.posDoubles()}
@JvmName("words2") public fun Iterable<Iterable<String>>.words():List<List<List<String>>> = map{it.words()}
@JvmName("alphaNums2") public fun Iterable<Iterable<String>>.alphaNums():List<List<List<String>>> = map{it.alphaNums()}
@JvmName("points2") public fun Iterable<Iterable<String>>.points():List<List<List<Point>>> = map{it.points()}
@JvmName("points3D2") public fun Iterable<Iterable<String>>.points3D():List<List<List<Point3D>>> = map{it.points3D()}


@JvmName("ints2") public inline fun <T>Iterable<Iterable<String>>.ints(transform:(List<Int>) -> T):List<List<T>> = map{it.ints(transform)}
@JvmName("posInts2") public inline fun <T>Iterable<Iterable<String>>.posInts(transform:(List<Int>) -> T):List<List<T>> = map{it.posInts(transform)}
@JvmName("sints2") public inline fun <T>Iterable<Iterable<String>>.sints(transform:(List<Sint>) -> T):List<List<T>> = map{it.sints(transform)}
@JvmName("posSints2") public inline fun <T>Iterable<Iterable<String>>.posSints(transform:(List<Sint>) -> T):List<List<T>> = map{it.posSints(transform)}
@JvmName("longs2") public inline fun <T>Iterable<Iterable<String>>.longs(transform:(List<Long>) -> T):List<List<T>> = map{it.longs(transform)}
@JvmName("posLongs2") public inline fun <T>Iterable<Iterable<String>>.posLongs(transform:(List<Long>) -> T):List<List<T>> = map{it.posLongs(transform)}
@JvmName("digits2") public inline fun <T>Iterable<Iterable<String>>.digits(transform:(List<Int>) -> T):List<List<T>> = map{it.digits(transform)}
@JvmName("doubles2") public inline fun <T>Iterable<Iterable<String>>.doubles(transform:(List<Double>) -> T):List<List<T>> = map{it.doubles(transform)}
@JvmName("posDoubles2") public inline fun <T>Iterable<Iterable<String>>.posDoubles(transform:(List<Double>) -> T):List<List<T>> = map{it.posDoubles(transform)}
@JvmName("words2") public inline fun <T>Iterable<Iterable<String>>.words(transform:(List<String>) -> T):List<List<T>> = map{it.words(transform)}
@JvmName("alphaNums2") public inline fun <T>Iterable<Iterable<String>>.alphaNums(transform:(List<String>) -> T):List<List<T>> = map{it.alphaNums(transform)}
@JvmName("points2") public inline fun <T>Iterable<Iterable<String>>.points(transform:(List<Point>) -> T):List<List<T>> = map{it.points(transform)}
@JvmName("points3D2") public inline fun <T>Iterable<Iterable<String>>.points3D(transform:(List<Point3D>) -> T):List<List<T>> = map{it.points3D(transform)}

public fun getIntLines(day:Int): IntLines = getLines(day).map{it.getInts()}
public fun getPosIntLines(day:Int): PosIntLines = getLines(day).map{it.getPosInts()}
public fun getLongLines(day:Int): LongLines = getLines(day).map{it.getLongs()}
public fun getPosLongLines(day:Int): PosLongLines = getLines(day).map{it.getPosLongs()}
public fun getDigitLines(day:Int): DigitLines = getLines(day).map{it.getDigits()}
public fun getDoubleLines(day:Int): DoubleLines = getLines(day).map{it.getDoubles()}
public fun getPosDoubleLines(day:Int): PosDoubleLines = getLines(day).map{it.getPosDoubles()}
public fun getWordLines(day:Int): WordLines = getLines(day).map{it.getWords()}
public fun getAlphaNumLines(day:Int): AlphaNumLines = getLines(day).map{it.getAlphaNums()}
public fun getPointLines(day:Int): PointLines = getLines(day).map{it.getPoints()}
public fun getPoint3DLines(day:Int): Point3DLines = getLines(day).map{it.getPoints3D()}

public typealias PosInt = Int
public typealias PosLong = Long
public typealias PosSint = Sint
public typealias Digit = Int
public typealias PosDouble = Double
public typealias Word = String
public typealias AlphaNum = String

public typealias Ints = List<Int>
public typealias PosInts = List<PosInt>
public typealias Longs = List<Long>
public typealias PosLongs = List<PosLong>
public typealias Sints = List<Sint>
public typealias PosSints = List<PosSint>
public typealias Digits = List<Digit>
public typealias Doubles = List<Double>
public typealias PosDoubles = List<PosDouble>
public typealias Words = List<Word>
public typealias AlphaNums = List<AlphaNum>
public typealias Points = List<Point>
public typealias Points3D = List<Point3D>

public typealias Lines = List<String>
public typealias CGrid = SimpleGrid<Char>

public typealias IntLines = List<List<Int>>
public typealias PosIntLines = List<List<PosInt>>
public typealias LongLines = List<List<Long>>
public typealias PosLongLines = List<List<PosLong>>
public typealias DigitLines = List<List<Digit>>
public typealias DoubleLines = List<List<Double>>
public typealias PosDoubleLines = List<List<PosDouble>>
public typealias WordLines = List<List<Word>>
public typealias AlphaNumLines = List<List<AlphaNum>>
public typealias PointLines = List<List<Point>>
public typealias Point3DLines = List<List<Point3D>>
