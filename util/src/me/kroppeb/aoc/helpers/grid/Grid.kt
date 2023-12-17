package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.point.Bounds
import me.kroppeb.aoc.helpers.point.Point

public interface Grid<out T> {
	public operator fun get(index: Point): T
	public val bounds: Bounds
}

public interface StrictGrid<out T> : Grid<T> {
}

public interface ResizeableGrid<out T> : Grid<T> {
	public fun expand(amount: Int)
	public fun expand(x: Int = 0, y: Int = 0)

	public fun contract(amount: Int)
	public fun contract(x: Int = 0, y: Int = 0)
}

public interface MutableGrid<T> : Grid<T> {
	public operator fun set(index: Point, item: T)
}
