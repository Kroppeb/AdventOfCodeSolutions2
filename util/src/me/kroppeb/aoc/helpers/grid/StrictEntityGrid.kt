@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.point.*

public class StrictPointGrid(override val bounds: Bounds, public var points: Set<Point>) : StrictGrid<Boolean> {
	override fun get(index: Point): Boolean = index in bounds && index in points
	public operator fun contains(index: Point): Boolean = get(index)
}

public inline fun <T> SimpleGrid<T>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid {
	val points = mutableSetOf<Point>()
	forEach { bgp -> if (predicate(bgp.v)) points.add(bgp.p) }
	return StrictPointGrid(bounds, points);
}

public inline fun <T> List<List<T>>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid =
	grid().entityGrid(predicate)

public fun Iterable<Point>.entityGrid(): StrictPointGrid {
	return StrictPointGrid(bounds(), this.toSet())
}


public class StrictTypedEntityGrid<T>(override val bounds: Bounds, public val items: Map<Point, T>) : StrictGrid<T?> {
	override fun get(index: Point): T? = items[index]
	public operator fun contains(index: Point): Boolean = get(index) != null
}
