@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers


import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.sint.*


public object Clock {
	public enum class Mode(
		public val nX: Int,
		public val eX: Int,
		public val nY: Int,
		public val eY: Int,
	) { // X+, Y+
		NE(1, 0, 0, 1),
		NW(1, 0, 0, -1),
		SE(-1, 0, 0, 1),
		SW(-1, 0, 0, -1),
		EN(0, 1, 1, 0),
		ES(0, 1, -1, 0),
		WN(0, -1, 1, 0),
		WS(0, -1, -1, 0);

		init {
			assert(nX == 0 || eX == 0)
			assert(nY == 0 || eY == 0)
		}
	}

	public lateinit var left: Point
		private set;
	public lateinit var right: Point
		private set;
	public lateinit var up: Point
		private set;
	public lateinit var down: Point
		private set;

	public val north: Point get() = up
	public val east: Point get() = right
	public val south: Point get() = down
	public val west: Point get() = left


	// bounds calculation
	public val nX: Int get() = mode.nX
	public val eX: Int get() = mode.eX
	public val nY: Int get() = mode.nY
	public val eY: Int get() = mode.eY

	public lateinit var mode: Mode
		private set


	public operator fun invoke(x: Int, y: Int) {
		mode = when (x to y) {
			3 to 6 -> Mode.ES
			3 to 12 -> Mode.EN
			6 to 3 -> Mode.SE
			6 to 9 -> Mode.SW
			9 to 6 -> Mode.WS
			9 to 12 -> Mode.WN
			12 to 3 -> Mode.NE
			12 to 9 -> Mode.NW
			else -> error("invalid clock")
		}

		up = nX toP nY
		right = eX toP eY
		left = -right
		down = -up

		this.print()
	}

	public fun print() {
		// eg 6,3: (nX = -1, eY = 1
		//      -----
		//     /     \
		//    /       \
		//   |    o->  |
		//    \   |   /
		//     \  v  /
		//      -----
		println("    -----    ")

		if (nX == 1) {
			println("   /  ^  \\   ")
			println("  /   |   \\  ")
		} else {
			println("   /     \\   ")
			if (nY == 1)
				println("  /   ^   \\  ")
			else
				println("  /       \\  ")
		}

		if (eX == 1) {
			println(" |    o--->| ")
		} else if (eX == -1) {
			println(" |<---o    | ")
		} else if (eY == 1) {
			println(" |    o->  | ")
		} else {
			println(" |  <-o    | ")
		}

		if (nX == -1) {
			println("  \\   |   /  ")
			println("   \\  v  /   ")
		} else {
			if (nY == -1)
				println("  \\   v   /  ")
			else
				println("  \\       /  ")
			println("   \\     /   ")
		}

		println("    -----    ")
	}

	public fun firstRange(size: Sint): SintProgression =
		if (down.x + down.y > Sint.ZERO) 0.s until size
		else size - 1 downTo 0.s

	public fun secondRange(size: Sint): SintProgression =
		if (right.x + right.y > Sint.ZERO) 0.s until size
		else size - 1 downTo 0.s

	public fun fromIndices(first: Sint, second: Sint): Point {
		return down * first + right * second
	}
}