package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.grid.BGP
import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.Point3D

public fun msoa(): MSet<Any> = mutableSetOf()
public fun <T> msot(): MSet<T> = mutableSetOf()
public fun soi(vararg a: Int): Set<Int> = setOf(*a.toTypedArray())
public fun msoi(vararg a: Int): MSet<Int> = mutableSetOf(*a.toTypedArray())
public fun sol(vararg a: Long): Set<Long> = setOf(*a.toTypedArray())
public fun msol(vararg a: Long): MSet<Long> = mutableSetOf(*a.toTypedArray())
public fun sop(vararg a: Point): Set<Point> = setOf(*a)
public fun msop(vararg a: Point): MSet<Point> = mutableSetOf(*a)
public fun <T> sobp(vararg a: BGP<T>): Set<BGP<T>> = setOf(*a)
public fun <T> msobp(vararg a: BGP<T>): MSet<BGP<T>> = mutableSetOf(*a)
public fun sop3(vararg a: Point3D): Set<Point3D> = setOf(*a)
public fun msop3(vararg a: Point3D): MSet<Point3D> = mutableSetOf(*a)

public fun mmoaa(): MMap<Any, Any> = mutableMapOf()
public fun <T> mmoat(): MMap<Any, T> = mutableMapOf()
public fun <K, V> mmokv(): MMap<K, V> = mutableMapOf()

public fun moii(vararg a: Pair<Int, Int>): Map<Int, Int> = mapOf(*a)
public fun mmoii(vararg a: Pair<Int, Int>): MMap<Int, Int> = mutableMapOf(*a)
public fun moil(vararg a: Pair<Int, Long>): Map<Int, Long> = mapOf(*a)
public fun mmoil(vararg a: Pair<Int, Long>): MMap<Int, Long> = mutableMapOf(*a)
public fun moip(vararg a: Pair<Int, Point>): Map<Int, Point> = mapOf(*a)
public fun mmoip(vararg a: Pair<Int, Point>): MMap<Int, Point> = mutableMapOf(*a)
public fun moip3(vararg a: Pair<Int, Point3D>): Map<Int, Point3D> = mapOf(*a)
public fun mmoip3(vararg a: Pair<Int, Point3D>): MMap<Int, Point3D> = mutableMapOf(*a)
public fun moia(vararg a: Pair<Int, Any>): Map<Int, Any> = mapOf(*a)
public fun mmoia(vararg a: Pair<Int, Any>): MMap<Int, Any> = mutableMapOf(*a)

public fun moli(vararg a: Pair<Long, Int>): Map<Long, Int> = mapOf(*a)
public fun mmoli(vararg a: Pair<Long, Int>): MMap<Long, Int> = mutableMapOf(*a)
public fun moll(vararg a: Pair<Long, Long>): Map<Long, Long> = mapOf(*a)
public fun mmoll(vararg a: Pair<Long, Long>): MMap<Long, Long> = mutableMapOf(*a)
public fun molp(vararg a: Pair<Long, Point>): Map<Long, Point> = mapOf(*a)
public fun mmolp(vararg a: Pair<Long, Point>): MMap<Long, Point> = mutableMapOf(*a)
public fun molp3(vararg a: Pair<Long, Point3D>): Map<Long, Point3D> = mapOf(*a)
public fun mmolp3(vararg a: Pair<Long, Point3D>): MMap<Long, Point3D> = mutableMapOf(*a)
public fun mola(vararg a: Pair<Long, Any>): Map<Long, Any> = mapOf(*a)
public fun mmola(vararg a: Pair<Long, Any>): MMap<Long, Any> = mutableMapOf(*a)


public fun mopi(vararg a: Pair<Point, Int>): Map<Point, Int> = mapOf(*a)
public fun mmopi(vararg a: Pair<Point, Int>): MMap<Point, Int> = mutableMapOf(*a)
public fun mopl(vararg a: Pair<Point, Long>): Map<Point, Long> = mapOf(*a)
public fun mmopl(vararg a: Pair<Point, Long>): MMap<Point, Long> = mutableMapOf(*a)
public fun mopp(vararg a: Pair<Point, Point>): Map<Point, Point> = mapOf(*a)
public fun mmopp(vararg a: Pair<Point, Point>): MMap<Point, Point> = mutableMapOf(*a)
public fun mopp3(vararg a: Pair<Point, Point3D>): Map<Point, Point3D> = mapOf(*a)
public fun mmopp3(vararg a: Pair<Point, Point3D>): MMap<Point, Point3D> = mutableMapOf(*a)
public fun mopa(vararg a: Pair<Point, Any>): Map<Point, Any> = mapOf(*a)
public fun mmopa(vararg a: Pair<Point, Any>): MMap<Point, Any> = mutableMapOf(*a)

public fun mop3i(vararg a: Pair<Point3D, Int>): Map<Point3D, Int> = mapOf(*a)
public fun mmop3i(vararg a: Pair<Point3D, Int>): MMap<Point3D, Int> = mutableMapOf(*a)
public fun mop3l(vararg a: Pair<Point3D, Long>): Map<Point3D, Long> = mapOf(*a)
public fun mmop3l(vararg a: Pair<Point3D, Long>): MMap<Point3D, Long> = mutableMapOf(*a)
public fun mop3p(vararg a: Pair<Point3D, Point>): Map<Point3D, Point> = mapOf(*a)
public fun mmop3p(vararg a: Pair<Point3D, Point>): MMap<Point3D, Point> = mutableMapOf(*a)
public fun mop3p3(vararg a: Pair<Point3D, Point3D>): Map<Point3D, Point3D> = mapOf(*a)
public fun mmop3p3(vararg a: Pair<Point3D, Point3D>): MMap<Point3D, Point3D> = mutableMapOf(*a)
public fun mop3a(vararg a: Pair<Point3D, Any>): Map<Point3D, Any> = mapOf(*a)
public fun mmop3a(vararg a: Pair<Point3D, Any>): MMap<Point3D, Any> = mutableMapOf(*a)

public fun moai(vararg a: Pair<Any, Int>): Map<Any, Int> = mapOf(*a)
public fun mmoai(vararg a: Pair<Any, Int>): MMap<Any, Int> = mutableMapOf(*a)
public fun moal(vararg a: Pair<Any, Long>): Map<Any, Long> = mapOf(*a)
public fun mmoal(vararg a: Pair<Any, Long>): MMap<Any, Long> = mutableMapOf(*a)
public fun moap(vararg a: Pair<Any, Point>): Map<Any, Point> = mapOf(*a)
public fun mmoap(vararg a: Pair<Any, Point>): MMap<Any, Point> = mutableMapOf(*a)
public fun moap3(vararg a: Pair<Any, Point3D>): Map<Any, Point3D> = mapOf(*a)
public fun mmoap3(vararg a: Pair<Any, Point3D>): MMap<Any, Point3D> = mutableMapOf(*a)
public fun moaa(vararg a: Pair<Any, Any>): Map<Any, Any> = mapOf(*a)
public fun mmoaa(vararg a: Pair<Any, Any>): MMap<Any, Any> = mutableMapOf(*a)

public fun mloa(): MList<Any> = mutableListOf()
public fun <T> mlot(): MList<T> = mutableListOf()

public fun loi(vararg a: Int): List<Int> = listOf(*a.toTypedArray())
public fun mloi(vararg a: Int): MList<Int> = mutableListOf(*a.toTypedArray())
public fun lol(vararg a: Long): List<Long> = listOf(*a.toTypedArray())
public fun mlol(vararg a: Long): MList<Long> = mutableListOf(*a.toTypedArray())
public fun lop(vararg a: Point): List<Point> = listOf(*a)
public fun mlop(vararg a: Point): MList<Point> = mutableListOf(*a)
public fun <T> lobp(vararg a: BGP<T>): List<BGP<T>> = listOf(*a)
public fun <T> mlobp(vararg a: BGP<T>): MList<BGP<T>> = mutableListOf(*a)
public fun lop3(vararg a: Point3D): List<Point3D> = listOf(*a)
public fun mlop3(vararg a: Point3D): MList<Point3D> = mutableListOf(*a)
public fun aoi(size: Int): IntArray = IntArray(size)
public fun aoi(size: Int, value: Int): IntArray = IntArray(size).apply { fill(value) }
public fun aod(size: Int): DoubleArray = DoubleArray(size)
public fun aod(size: Int, value: Double): DoubleArray = DoubleArray(size).apply { fill(value) }
public fun aol(size: Int): LongArray = LongArray(size)
public fun aol(size: Int, value: Long): LongArray = LongArray(size).apply { fill(value) }
