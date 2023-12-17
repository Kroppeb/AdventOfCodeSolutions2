@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s


public infix fun Sint.toP(y: Sint): Point = Point(this, y)
public infix fun Sint.toP(y: Int): Point = Point(this, y.s)
public infix fun Int.toP(y: Sint): Point = Point(this.s, y)
public infix fun Int.toP(y: Int): Point = Point(this.s, y.s)
public infix fun Sint.toP(y: Long): Point = Point(this, y.s)
public infix fun Long.toP(y: Sint): Point = Point(this.s, y)
public infix fun Long.toP(y: Int): Point = Point(this.s, y.s)
public infix fun Int.toP(y: Long): Point = Point(this.s, y.s)
public infix fun Long.toP(y: Long): Point = Point(this.s, y.s)

public infix fun Point.toP(z: Sint): Point3D = Point3D(x, y, z)
public infix fun Point.toP(z: Int): Point3D = Point3D(x, y, z.s)
public infix fun Point.toP(z: Long): Point3D = Point3D(x, y, z.s)


public fun Char.toPoint(): Point = when (this.uppercaseChar()) {
	'E' -> Clock.right
	'R' -> Clock.right

	'S' -> Clock.down
	'D' -> Clock.down

	'W' -> Clock.left
	'L' -> Clock.left

	'N' -> Clock.up
	'U' -> Clock.up

	else -> error("Unknown direction $this")
}

public fun <T : PointN<T>> abs(v: T): T = v.abs()


public fun <T : PointN<T>> Iterable<T>.getClosest(): T = this.minBy(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getClosestMan(): T = this.minBy(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getClosestCheb(): T = this.minBy(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getClosestTo(other: T): T = this.minBy { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestManTo(other: T): T = this.minBy { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestChebTo(other: T): T = this.minBy { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getClosestOrNull(): T? = this.minByOrNull(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getClosestManOrNull(): T? = this.minByOrNull(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getClosestChebOrNull(): T? = this.minByOrNull(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getClosestToOrNull(other: T): T? = this.minByOrNull { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestManToOrNull(other: T): T? = this.minByOrNull { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestChebToOrNull(other: T): T? =
	this.minByOrNull { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getFurthest(): T = this.maxBy(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestMan(): T = this.maxBy(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestCheb(): T = this.maxBy(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getFurthestTo(other: T): T = this.maxBy { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestManTo(other: T): T = this.maxBy { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestChebTo(other: T): T = this.maxBy { it.chebyshevDistTo(other) }


public fun <T : PointN<T>> Iterable<T>.getFurthestOrNull(): T? = this.maxByOrNull(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestManOrNull(): T? = this.maxByOrNull(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestChebOrNull(): T? = this.maxByOrNull(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getFurthestToOrNull(other: T): T? = this.maxByOrNull { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestManToOrNull(other: T): T? = this.maxByOrNull { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestChebToOrNull(other: T): T? =
	this.maxByOrNull { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getClosestSqrDist(): Sint = this.minOf(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getClosestDist(): Double = this.minOf(PointN<T>::dist)
public fun <T : PointN<T>> Iterable<T>.getClosestManDist(): Sint = this.minOf(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getClosestChebDist(): Sint = this.minOf(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getClosestSqrDistTo(other: T): Sint = this.minOf { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestDistTo(other: T): Double = this.minOf { it.distTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestManDistTo(other: T): Sint = this.minOf { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getClosestChebDistTo(other: T): Sint = this.minOf { it.chebyshevDistTo(other) }


public fun <T : PointN<T>> Iterable<T>.getClosestSqrDistOrNull(): Sint? = this.minOfOrNull(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getClosestDistOrNull(): Double? = this.minOfOrNull(PointN<T>::dist)
public fun <T : PointN<T>> Iterable<T>.getClosestManDistOrNull(): Sint? = this.minOfOrNull(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getClosestChebDistOrNull(): Sint? = this.minOfOrNull(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getClosestSqrDistToOrNull(other: T): Sint? =
	this.minOfOrNull { it.sqrDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getClosestDistToOrNull(other: T): Double? =
	this.minOfOrNull { it.distTo(other) }

public fun <T : PointN<T>> Iterable<T>.getClosestManDistToOrNull(other: T): Sint? =
	this.minOfOrNull { it.manDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getClosestChebDistToOrNull(other: T): Sint? =
	this.minOfOrNull { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getFurthestSqrDist(): Sint = this.maxOf(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestDist(): Double = this.maxOf(PointN<T>::dist)
public fun <T : PointN<T>> Iterable<T>.getFurthestManDist(): Sint = this.maxOf(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestChebDist(): Sint = this.maxOf(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getFurthestSqrDistTo(other: T): Sint = this.maxOf { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestDistTo(other: T): Double = this.maxOf { it.distTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestManDistTo(other: T): Sint = this.maxOf { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.getFurthestChebDistTo(other: T): Sint = this.maxOf { it.chebyshevDistTo(other) }


public fun <T : PointN<T>> Iterable<T>.getFurthestSqrDistOrNull(): Sint? = this.maxOfOrNull(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestDistOrNull(): Double? = this.maxOfOrNull(PointN<T>::dist)
public fun <T : PointN<T>> Iterable<T>.getFurthestManDistOrNull(): Sint? = this.maxOfOrNull(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.getFurthestChebDistOrNull(): Sint? = this.maxOfOrNull(PointN<T>::chebyshevDist)

public fun <T : PointN<T>> Iterable<T>.getFurthestSqrDistToOrNull(other: T): Sint? =
	this.maxOfOrNull { it.sqrDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getFurthestDistToOrNull(other: T): Double? =
	this.maxOfOrNull { it.distTo(other) }

public fun <T : PointN<T>> Iterable<T>.getFurthestManDistToOrNull(other: T): Sint? =
	this.maxOfOrNull { it.manDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.getFurthestChebDistToOrNull(other: T): Sint? =
	this.maxOfOrNull { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.sortByClosestTo(other: T): List<T> = sortedBy { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.sortByClosestManTo(other: T): List<T> = sortedBy { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.sortByClosestChebTo(other: T): List<T> = sortedBy { it.sqrDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.sortByFurthestTo(other: T): List<T> = sortedByDescending { it.manDistTo(other) }
public fun <T : PointN<T>> Iterable<T>.sortByFurthestManTo(other: T): List<T> =
	sortedByDescending { it.manDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.sortByFurthestChebTo(other: T): List<T> =
	sortedByDescending { it.chebyshevDistTo(other) }

public fun <T : PointN<T>> Iterable<T>.sortByClosest(): List<T> = sortedBy(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.sortByClosestMan(): List<T> = sortedBy(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.sortByClosestCheb(): List<T> = sortedBy(PointN<T>::chebyshevDist)
public fun <T : PointN<T>> Iterable<T>.sortByFurthest(): List<T> = sortedByDescending(PointN<T>::sqrDist)
public fun <T : PointN<T>> Iterable<T>.sortByFurthestMan(): List<T> = sortedByDescending(PointN<T>::manDist)
public fun <T : PointN<T>> Iterable<T>.sortByFurthestCheb(): List<T> = sortedByDescending(PointN<T>::chebyshevDist)

public object PointOrdering {
	public val XMayor: Comparator<Point> = Comparator { o1: Point, o2: Point ->
		if (o1.x == o2.x) o1.y.compareTo(o2.y)
		else o1.x.compareTo(o2.x)
	}

	public val YMayor: Comparator<Point> = Comparator<Point> { o1: Point, o2: Point ->
		if (o1.y == o2.y) o1.x.compareTo(o2.x)
		else o1.y.compareTo(o2.y)
	}

	public val NS: Comparator<Point> = Comparator<Point> { o1, o2 ->
		when {
			o1.isAbove(o2) -> -1
			o1.isBelow(o2) -> 1
			else -> 0
		}
	}

	public val SN: Comparator<Point> = NS.reversed()

	public val EW: Comparator<Point> = Comparator<Point> { o1, o2 ->
		when {
			o1.isLeftOf(o2) -> -1
			o1.isRightOf(o2) -> 1
			else -> 0
		}
	}

	public val WE: Comparator<Point> = EW.reversed()

	public val NS_EW: Comparator<Point> = NS.thenComparing(EW)
	public val NS_WE: Comparator<Point> = NS.thenComparing(WE)
	public val SN_EW: Comparator<Point> = SN.thenComparing(EW)
	public val SN_WE: Comparator<Point> = SN.thenComparing(WE)
}



@JvmName("PointIntIntPair")
public fun Pair<Int, Int>.toPoint(): Point = first toP second
@JvmName("PointLongLongPair")
public fun Pair<Long, Long>.toPoint(): Point = first toP second