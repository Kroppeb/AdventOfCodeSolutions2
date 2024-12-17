package me.kroppeb.aoc.helpers.graph

import me.kroppeb.aoc.helpers.Loggable
import me.kroppeb.aoc.helpers.collections.default
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.g
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import me.kroppeb.aoc.helpers.sint.sumOf
import java.util.*

public data class BfsResult<out State>(val path: List<State>) : Loggable {
	public val start: State get() = path.first()
	public val end: State get() = path.last()
	public val length: Int get() = path.size - 1

	override fun getCopyString(): String = length.toString()

	override fun toString(): String {
		return "BfsResult(start=$start, end=$end, length=$length)"
	}
}

public data class Res<State>(val state: State?, val dist: Int) : Loggable {
	val first: State? get() = state
	val second: Int get() = dist
	override fun getCopyString(): String = dist.toString()
}

public inline fun <State> bfs(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> = bfsL(listOf(start), isEnd, next)


public inline fun <State> bfsL(starts: Iterable<State>, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) return Res(current, dist)

			for (i in next(current)) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return Res(null, dist)
}


public inline fun <State> bfsDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> = bfsLDist(listOf(start), isEnd, next)


public inline fun <State> bfsLDist(
	starts: Iterable<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist)) return Res(current, dist)

			for (i in next(current, dist)) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return Res(null, dist)
}


public inline fun <State> bfsPath(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? = bfsLPath(listOf(start), isEnd, next)


public inline fun <State> bfsLPath(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1.s

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) {
				val path = mutableListOf(current)
				var c = current
				while (true) {
					c = back[c] ?: break
					path += c
				}
				return BfsResult(path)
			}

			for (i in next(current)) {
				if (seen.add(i)) {
					nextQueue.add(i)
					back[i] = current
				}
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return null
}

public inline fun <State> bfsPathDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? = bfsLPathDist(listOf(start), isEnd, next)


public inline fun <State> bfsLPathDist(
	starts: Iterable<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist)) {
				val path = mutableListOf(current)
				var c = current
				while (true) {
					c = back[c] ?: break
					path += c
				}
				return BfsResult(path)
			}

			for (i in next(current, dist)) {
				if (seen.add(i)) {
					nextQueue.add(i)
					back[i] = current
				}
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return null
}


public inline fun <State> dfs(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> =
	dfsL(listOf(start), isEnd, next)


public inline fun <State> dfsL(starts: Iterable<State>, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = starts.toMutableSet()
	val stack = starts.mapTo(mutableListOf()) { it to 0 }
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current).reversed()) {
			if (seen.add(i))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}

public inline fun <State> dfsDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> = dfsLDist(listOf(start), isEnd, next)


public inline fun <State> dfsLDist(
	starts: Iterable<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = starts.map { it to 0 }.toMutableSet()
	val stack = starts.mapTo(mutableListOf()) { it to 0 }
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current, dist)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}


public inline fun <State> dfsPath(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? = dfsLPath(listOf(start), isEnd, next)


public inline fun <State> dfsLPath(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.map { it to 0 }.toMutableSet()
	val stack = starts.mapTo(mutableListOf()) { it to 0 }
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}


public inline fun <State> dfsPathDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? = dfsLPathDist(listOf(start), isEnd, next)


public inline fun <State> dfsLPathDist(
	starts: Iterable<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.map { it to 0 }.toMutableSet()
	val stack = starts.mapTo(mutableListOf()) { it to 0 }
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current, dist)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}

public data class DijkstraResult<out State>(val path: List<State>, val cost: Sint) : Loggable {
	val start: State get() = path.first()
	val end: State get() = path.last()
	val length: Int get() = path.size - 1

	override fun getCopyString(): String = cost.toString()

	override fun toString(): String {
		return "DijkstraResult(start=$start, end=$end, length=$length, cost=$cost)"
	}
}

public data class DijkstraResultD<out State>(val path: List<State>, val cost: Double) : Loggable {
	val start: State get() = path.first()
	val end: State get() = path.last()
	val length: Int get() = path.size - 1

	override fun getCopyString(): String = cost.toString()

	override fun toString(): String {
		return "DijkstraResult(start=$start, end=$end, length=$length, cost=$cost)"
	}
}

public inline fun <State> dijkstra(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? = dijkstraL(listOf(start), isEnd, next)


public inline fun <State> dijkstraL(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}

		for ((next, cost) in next(current)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


public inline fun <State> dijkstraY(
	start: State,
	isEnd: (State) -> Boolean,
	next: Yier<State, Pair<State, Sint>>,
): DijkstraResult<State>? = dijkstraYL(listOf(start), isEnd, next)


public inline fun <State> dijkstraYL(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: Yier<State, Pair<State, Sint>>,
): DijkstraResult<State>? {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}


		for ((next, cost) in yieldList(next, current)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


public inline fun <State> dijkstraD(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? = dijkstraLD(listOf(start), isEnd, next)

public inline fun <State> dijkstraLD(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
	val costs = starts.associateWith { 0.0 }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += starts.map { it to 0.0 }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResultD(path, currentCost)
		}

		for ((next, cost) in next(current)) {
			val newCost = currentCost + cost
			val previousCost = costs.getOrDefault(next, Double.POSITIVE_INFINITY)
			if (newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


public inline fun <State> dijkstraDist(
	start: State,
	isEnd: (State, Sint) -> Boolean,
	next: (State, Sint) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? = dijkstraLDist(listOf(start), isEnd, next)


public inline fun <State> dijkstraLDist(
	starts: Iterable<State>,
	isEnd: (State, Sint) -> Boolean,
	next: (State, Sint) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}

public inline fun <State> dijkstraDDist(
	start: State,
	isEnd: (State, Double) -> Boolean,
	next: (State, Double) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? = dijkstraLDDist(listOf(start), isEnd, next)


public inline fun <State> dijkstraLDDist(
	starts: Iterable<State>,
	isEnd: (State, Double) -> Boolean,
	next: (State, Double) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
	val costs = starts.associateWith { 0.0 }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += starts.map { it to 0.0 }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResultD(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
			val newCost = currentCost + cost
			val previousCost = costs.getOrDefault(next, Double.POSITIVE_INFINITY)
			if (newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}

public data class DijkstraResultAll<State>(
	val backs: Map<State, List<State>>,
	val cost: Sint,
	val counts: Map<State, Sint>,
	val startPoints: List<State>,
	val endPoints: List<State>,
	val totalCount: Sint,
) : Loggable {
	override fun getCopyString(): String = totalCount.toString()

	override fun toString(): String {
		return "DijkstraResult(cost=$cost, totalCount=$totalCount)"
	}

	public fun getAllPaths(): List<List<State>> = getAllPathsSeq().toList()

	public fun getAllPathsSeq(): Sequence<List<State>> = sequence {
		withLIFOList(endPoints.map { it.g() }) { path ->
			val backs = backs[path.first()]
			if (backs != null) {
				for (back in backs) {
					this@withLIFOList.yield(back.g() + path)
				}
			} else {
				this@sequence.yield(path)
			}
		}
	}
}


// Kotlin does not support local functions in inline functions
public fun <State> ____counts(state: State, pathCounts: MutableMap<State, Sint>, backs: MutableMap<State, MutableList<State>>): Sint {
	pathCounts[state]?.let { return it }
	val cnt = backs[state]?.sumOf { ____counts(it, pathCounts, backs) } ?: 1.s  // start
	pathCounts[state] = cnt
	return cnt
}

public inline fun <State> dijkstraAll(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResultAll<State> = dijkstraLAll(listOf(start), isEnd, next)


public inline fun <State> dijkstraLAll(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResultAll<State> {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val backs = mutableMapOf<State, MutableList<State>>()
	var lowestCost: Sint? = null
	val endPoints = mutableListOf<State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (lowestCost != null && costs[current]!! > lowestCost) break
		if (isEnd(current)) {
			lowestCost = costs[current]
			endPoints.add(current)
		}

		for ((next, cost) in next(current)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				// override the previous backs
				backs[next] = mutableListOf(current)
			} else if (previousCost == newCost) {
				// add to the previous backs
				backs[next]!! += current
			}
		}
	}

	val pathCounts = mutableMapOf<State, Sint>()
	val totalCount = endPoints.sumOf { i ->
		____counts(i, pathCounts, backs)
	}

	return DijkstraResultAll(
		backs,
		lowestCost ?: -1.s,
		pathCounts,
		starts.toList(),
		endPoints,
		totalCount,
	)
}


public inline fun <State> dijkstraYAll(
	start: State,
	isEnd: (State) -> Boolean,
	next: Yier<State, Pair<State, Sint>>
): DijkstraResultAll<State> = dijkstraYLAll(listOf(start), isEnd, next)


public inline fun <State> dijkstraYLAll(
	starts: Iterable<State>,
	isEnd: (State) -> Boolean,
	next: Yier<State, Pair<State, Sint>>
): DijkstraResultAll<State> {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val backs = mutableMapOf<State, MutableList<State>>()
	var lowestCost: Sint? = null
	val endPoints = mutableListOf<State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (lowestCost != null && costs[current]!! > lowestCost) break
		if (isEnd(current)) {
			lowestCost = costs[current]
			endPoints.add(current)
		}

		for ((next, cost) in yieldList(next, current)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				// override the previous backs
				backs[next] = mutableListOf(current)
			} else if (previousCost == newCost) {
				// add to the previous backs
				backs[next]!! += current
			}
		}
	}

	val pathCounts = mutableMapOf<State, Sint>()
	val totalCount = endPoints.sumOf { i ->
		____counts(i, pathCounts, backs)
	}

	return DijkstraResultAll(
		backs,
		lowestCost ?: -1.s,
		pathCounts,
		starts.toList(),
		endPoints,
		totalCount,
	)
}


public inline fun <State> floodFill(start: State, next: (State) -> Iterable<State>): Set<State> = floodFillL(listOf(start), next)

public inline fun <State> floodFillL(starts: Iterable<State>, next: (State) -> Iterable<State>): Set<State> {
	val stack = starts.toMutableList()
	val visited = mutableSetOf<State>()

	while (stack.isNotEmpty()) {
		val current = stack.removeLast()
		if (!visited.add(current)) {
			continue
		}
		stack += next(current)
	}

	return visited
}


public inline fun <State> floodFillY(start: State, next: Yr<State>): Set<State> = floodFillYL(listOf(start), next)

public inline fun <State> floodFillYL(starts: Iterable<State>, next: Yr<State>): Set<State> {
	val visited = mutableSetOf<State>()

	withFIFOList(starts) { current ->
		if (current in visited) return@withFIFOList
		visited += current
		next(current)
	}

	return visited
}

@JvmName("bfsSuffix")
public inline fun <State> State.bfs(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> = bfs(this, isEnd, next)

@JvmName("bfsLSuffix")
public inline fun <State> Iterable<State>.bfsL(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> = bfsL(this, isEnd, next)

@JvmName("bfsDistSuffix")
public inline fun <State> State.bfsDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): Res<State> = bfsDist(this, isEnd, next)

@JvmName("bfsLDistSuffix")
public inline fun <State> Iterable<State>.bfsLDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): Res<State> = bfsLDist(this, isEnd, next)

@JvmName("bfsPathSuffix")
public inline fun <State> State.bfsPath(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): BfsResult<State>? = bfsPath(this, isEnd, next)

@JvmName("bfsLPathSuffix")
public inline fun <State> Iterable<State>.bfsLPath(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): BfsResult<State>? = bfsLPath(this, isEnd, next)

@JvmName("bfsPathDistSuffix")
public inline fun <State> State.bfsPathDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): BfsResult<State>? = bfsPathDist(this, isEnd, next)

@JvmName("bfsLPathDistSuffix")
public inline fun <State> Iterable<State>.bfsLPathDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): BfsResult<State>? = bfsLPathDist(this, isEnd, next)

@JvmName("dfsSuffix")
public inline fun <State> State.dfs(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> = dfs(this, isEnd, next)

@JvmName("dfsLSuffix")
public inline fun <State> Iterable<State>.dfsL(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> = dfsL(this, isEnd, next)

@JvmName("dfsDistSuffix")
public inline fun <State> State.dfsDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): Res<State> = dfsDist(this, isEnd, next)

@JvmName("dfsLDistSuffix")
public inline fun <State> Iterable<State>.dfsLDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): Res<State> = dfsLDist(this, isEnd, next)

@JvmName("dfsPathSuffix")
public inline fun <State> State.dfsPath(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): BfsResult<State>? = dfsPath(this, isEnd, next)

@JvmName("dfsLPathSuffix")
public inline fun <State> Iterable<State>.dfsLPath(isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): BfsResult<State>? = dfsLPath(this, isEnd, next)

@JvmName("dfsPathDistSuffix")
public inline fun <State> State.dfsPathDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): BfsResult<State>? = dfsPathDist(this, isEnd, next)

@JvmName("dfsLPathDistSuffix")
public inline fun <State> Iterable<State>.dfsLPathDist(isEnd: (State, Int) -> Boolean, next: (State, Int) -> Iterable<State>): BfsResult<State>? = dfsLPathDist(this, isEnd, next)

@JvmName("dijkstraSuffix")
public inline fun <State> State.dijkstra(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResult<State>? = dijkstra(this, isEnd, next)

@JvmName("dijkstraLSuffix")
public inline fun <State> Iterable<State>.dijkstraL(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResult<State>? = dijkstraL(this, isEnd, next)

@JvmName("dijkstraDistSuffix")
public inline fun <State> State.dijkstraDist(isEnd: (State, Sint) -> Boolean, next: (State, Sint) -> Iterable<Pair<State, Sint>>): DijkstraResult<State>? = dijkstraDist(this, isEnd, next)

@JvmName("dijkstraLDistSuffix")
public inline fun <State> Iterable<State>.dijkstraLDist(isEnd: (State, Sint) -> Boolean, next: (State, Sint) -> Iterable<Pair<State, Sint>>): DijkstraResult<State>? = dijkstraLDist(this, isEnd, next)

@JvmName("dijkstraDSuffix")
public inline fun <State> State.dijkstraD(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Double>>): DijkstraResultD<State>? = dijkstraD(this, isEnd, next)

@JvmName("dijkstraLDSuffix")
public inline fun <State> Iterable<State>.dijkstraLD(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Double>>): DijkstraResultD<State>? = dijkstraLD(this, isEnd, next)

@JvmName("dijkstraDDistSuffix")
public inline fun <State> State.dijkstraDDist(isEnd: (State, Double) -> Boolean, next: (State, Double) -> Iterable<Pair<State, Double>>): DijkstraResultD<State>? = dijkstraDDist(this, isEnd, next)

@JvmName("dijkstraLDDistSuffix")
public inline fun <State> Iterable<State>.dijkstraLDDist(isEnd: (State, Double) -> Boolean, next: (State, Double) -> Iterable<Pair<State, Double>>): DijkstraResultD<State>? = dijkstraLDDist(this, isEnd, next)

@JvmName("dijkstraAllSuffix")
public inline fun <State> State.dijkstraAll(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResultAll<State> = dijkstraAll(this, isEnd, next)

@JvmName("dijkstraAllLSuffix")
public inline fun <State> Iterable<State>.dijkstraLAll(isEnd: (State) -> Boolean, next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResultAll<State> = dijkstraLAll(this, isEnd, next)

@JvmName("floodFillSuffix")
public inline fun <State> State.floodFill(next: (State) -> Iterable<State>): Set<State> = floodFill(this, next)

@JvmName("floodFillLSuffix")
public inline fun <State> Iterable<State>.floodFillL(next: (State) -> Iterable<State>): Set<State> = floodFillL(this, next)



public data class PathingIdea<State>(
	public val starts: Iterable<State>,
	public val endCheck: (State) -> Boolean,
)

public infix fun <State> State.pathTo(end: State): PathingIdea<State> = PathingIdea(listOf(this)) { it == end }
public infix fun <State> Iterable<State>.pathTo(end: State): PathingIdea<State> = PathingIdea(this) { it == end }
public infix fun <State> State.pathTo(isEnd: (State)->Boolean): PathingIdea<State> = PathingIdea(listOf(this), isEnd)
public infix fun <State> Iterable<State>.pathTo(isEnd: (State)->Boolean): PathingIdea<State> = PathingIdea(this, isEnd)


public inline infix fun <State> PathingIdea<State>.bfs(next: (State) -> Iterable<State>): BfsResult<State>? = bfsLPath(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dfs(next: (State) -> Iterable<State>): BfsResult<State>? = dfsLPath(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dijkstra(next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResult<State>? = dijkstraL(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dijkstraY(next: Yier<State, Pair<State, Sint>>): DijkstraResult<State>? = dijkstraYL(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dijkstraD(next: (State) -> Iterable<Pair<State, Double>>): DijkstraResultD<State>? = dijkstraLD(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dijkstraAll(next: (State) -> Iterable<Pair<State, Sint>>): DijkstraResultAll<State> = dijkstraLAll(starts, endCheck, next)
public inline infix fun <State> PathingIdea<State>.dijkstraYAll(next: Yier<State, Pair<State, Sint>>): DijkstraResultAll<State> = dijkstraYLAll(starts, endCheck, next)