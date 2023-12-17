@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.minus
import me.kroppeb.aoc.helpers.sint.s
import me.kroppeb.aoc.helpers.sint.times
import kotlin.math.floor

public fun interface PointTransform {
	public fun transform(p: Point): Point
}

public fun interface PointReflector : PointTransform {
	public fun reflect(p: Point): Point
	override fun transform(p: Point): Point = reflect(p)
}

public fun Point.trans(reflector: PointTransform): Point = reflector.transform(this)
public fun Point.reflect(reflector: PointReflector): Point = reflector.reflect(this)


private fun makeDiagonalReflector(dir1: Point, dir2: Point): PointReflector = PointReflector { p ->
	p.dot(dir1) * dir2 + p.dot(dir2) * dir1
}

public object PointTransformers {
	public val NS: PointReflector = PointReflector { p -> p.projectOnto(Clock.east) - p.projectOnto(Clock.north) }
	public val EW: PointReflector = PointReflector { p -> p.projectOnto(Clock.north) - p.projectOnto(Clock.east) }
	public val NE: PointReflector = makeDiagonalReflector(Clock.north, Clock.east)
	public val NW: PointReflector = makeDiagonalReflector(Clock.north, Clock.west)
	public val SE: PointReflector = NW
	public val SW: PointReflector = NE
	public val POINT: PointReflector = PointReflector { p -> -p }

	public fun ns(): PointReflector = NS
	public fun ew(): PointReflector = EW
	public fun ne(): PointReflector = NE
	public fun nw(): PointReflector = NW
	public fun se(): PointReflector = SE
	public fun sw(): PointReflector = SW
	public fun point(): PointReflector = POINT

	public fun ns(center: Point): PointReflector = PointReflector { p -> center + NS.reflect(p - center) }
	public fun ew(center: Point): PointReflector = PointReflector { p -> center + EW.reflect(p - center) }
	public fun ne(center: Point): PointReflector = PointReflector { p -> center + NE.reflect(p - center) }
	public fun nw(center: Point): PointReflector = PointReflector { p -> center + NW.reflect(p - center) }
	public fun se(center: Point): PointReflector = PointReflector { p -> center + SE.reflect(p - center) }
	public fun sw(center: Point): PointReflector = PointReflector { p -> center + SW.reflect(p - center) }
	public fun point(center: Point): PointReflector = PointReflector { p -> center + POINT.reflect(p - center) }

	public fun ns(from: Point, to: Point): PointReflector = PointReflector { p -> to + NS.reflect(p - from) }.also {
		require(from.sameLeftRight(to))
	}

	public fun ew(from: Point, to: Point): PointReflector = PointReflector { p -> to + EW.reflect(p - from) }.also {
		require(from.sameUpDown(to))
	}

	public fun ne(from: Point, to: Point): PointReflector = PointReflector { p -> to + NE.reflect(p - from) }.also {
		require(from.manDistTo(to) * 2 == from.chebyshevDistTo(to))
	}

	public fun nw(from: Point, to: Point): PointReflector = PointReflector { p -> to + NW.reflect(p - from) }.also {
		require(from.manDistTo(to) * 2 == from.chebyshevDistTo(to))
	}

	public fun se(from: Point, to: Point): PointReflector = PointReflector { p -> to + SE.reflect(p - from) }.also {
		require(from.manDistTo(to) * 2 == from.chebyshevDistTo(to))
	}

	public fun sw(from: Point, to: Point): PointReflector = PointReflector { p -> to + SW.reflect(p - from) }.also {
		require(from.manDistTo(to) * 2 == from.chebyshevDistTo(to))
	}

	public fun point(from: Point, to: Point): PointReflector = PointReflector { p -> to + POINT.reflect(p - from) }

	public fun nsNorth(value: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.east) + (2 * value - p.dot(Clock.north)) * Clock.north
	}

	public fun nsSouth(value: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.east) + (2 * value - p.dot(Clock.south)) * Clock.south
	}

	public fun ewEast(value: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.north) + (2 * value - p.dot(Clock.east)) * Clock.east
	}

	public fun ewWest(value: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.north) + (2 * value - p.dot(Clock.west)) * Clock.west
	}

	public fun nsNorth(value: Int): PointReflector = nsNorth(value.s)
	public fun nsSouth(value: Int): PointReflector = nsSouth(value.s)
	public fun ewEast(value: Int): PointReflector = ewEast(value.s)
	public fun ewWest(value: Int): PointReflector = ewWest(value.s)

	public fun nsNorth(from: Sint, to: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.east) + (from + to - p.dot(Clock.north)) * Clock.north
	}

	public fun nsSouth(from: Sint, to: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.east) + (from + to - p.dot(Clock.south)) * Clock.south
	}

	public fun ewEast(from: Sint, to: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.north) + (from + to - p.dot(Clock.east)) * Clock.east
	}

	public fun ewWest(from: Sint, to: Sint): PointReflector = PointReflector { p ->
		p.projectOnto(Clock.north) + (from + to - p.dot(Clock.west)) * Clock.west
	}

	public fun nsNorth(from: Int, to: Int): PointReflector = nsNorth(from.s, to.s)
	public fun nsSouth(from: Int, to: Int): PointReflector = nsSouth(from.s, to.s)
	public fun ewEast(from: Int, to: Int): PointReflector = ewEast(from.s, to.s)
	public fun ewWest(from: Int, to: Int): PointReflector = ewWest(from.s, to.s)

	public fun nsNorth(from: Int, to: Sint): PointReflector = nsNorth(from.s, to)
	public fun nsSouth(from: Int, to: Sint): PointReflector = nsSouth(from.s, to)
	public fun ewEast(from: Int, to: Sint): PointReflector = ewEast(from.s, to)
	public fun ewWest(from: Int, to: Sint): PointReflector = ewWest(from.s, to)

	public fun nsNorth(from: Sint, to: Int): PointReflector = nsNorth(from, to.s)
	public fun nsSouth(from: Sint, to: Int): PointReflector = nsSouth(from, to.s)
	public fun ewEast(from: Sint, to: Int): PointReflector = ewEast(from, to.s)
	public fun ewWest(from: Sint, to: Int): PointReflector = ewWest(from, to.s)


	public fun nsNorth(value: Double): PointReflector {
		val floor = floor(value)
		val frac = value - floor
		val floorSint = floor.toLong().s
		if (frac == 0.0) return nsNorth(floorSint)
		if (frac == 0.5) return nsNorth(floorSint, floorSint + 1.s)
		error("invalid value $value")
	}

	public fun nsSouth(value: Double): PointReflector {
		val floor = floor(value)
		val frac = value - floor
		val floorSint = floor.toLong().s
		if (frac == 0.0) return nsSouth(floorSint)
		if (frac == 0.5) return nsSouth(floorSint, floorSint + 1.s)
		error("invalid value $value")
	}

	public fun ewEast(value: Double): PointReflector {
		val floor = floor(value)
		val frac = value - floor
		val floorSint = floor.toLong().s
		if (frac == 0.0) return ewEast(floorSint)
		if (frac == 0.5) return ewEast(floorSint, floorSint + 1.s)
		error("invalid value $value")
	}

	public fun ewWest(value: Double): PointReflector {
		val floor = floor(value)
		val frac = value - floor
		val floorSint = floor.toLong().s
		if (frac == 0.0) return ewWest(floorSint)
		if (frac == 0.5) return ewWest(floorSint, floorSint + 1.s)
		error("invalid value $value")
	}
}