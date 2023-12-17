@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import me.kroppeb.aoc.helpers.sint.sumOf
import java.math.BigInteger

public class BoundsTree<B : BoundsN<B, P>, P:PointN<P>, S>(public val bounds: B, public var state: S) {
	public val children: MutableList<BoundsTree<B, P, S>> = mutableListOf()

	public operator fun set(newBounds: B, newState: S) {
		require(bounds.contains(newBounds)) { "Bounds $newBounds is not contained in $bounds" }
		if (this.bounds == newBounds) {
			this.state = newState
			return
		} else if (children.isEmpty()) {
			if (state == newState) return
			children += newBounds.fracture(bounds).map { BoundsTree(it, if (it in newBounds) newState else state) }
		} else {
			for (child in children) {
				if (child.bounds.doesIntersect(newBounds)) {
					child[newBounds.intersect(child.bounds)] = newState
				}
			}
		}
	}

	public fun count(predicate: (S) -> Boolean): Sint {
		return if (children.isEmpty()) {
			if (predicate(state)) bounds.weight() else 0.s
		} else {
			children.sumOf { it.count(predicate) }
		}
	}

	public fun countB(predicate: (S) -> Boolean): BigInteger {
		return if (children.isEmpty()) {
			if (predicate(state)) bounds.weightB() else BigInteger.ZERO
		} else {
			children.map { it.countB(predicate) }.reduce(BigInteger::add)
		}
	}

	public fun filter(function: (S) -> Boolean): List<P> {
		return if (children.isEmpty()) {
			if (function(state)) bounds.toList() else emptyList()
		} else {
			children.flatMap { it.filter(function) }
		}
	}

	public fun filterBounds(function: (S) -> Boolean): List<B> {
		return if (children.isEmpty()) {
			if (function(state)) listOf(bounds) else emptyList()
		} else {
			children.flatMap { it.filterBounds(function) }
		}
	}
}