package me.kroppeb.aoc.helpers

@Suppress("ReplaceSizeZeroCheckWithIsEmpty")
public sealed class Mask<T> : Collection<T> {
	public abstract infix fun and(other: Mask<T>): Mask<T>
	public open infix fun andNot(other: Mask<T>): Mask<T> = this and other.inv()
	public abstract infix fun or(other: Mask<T>): Mask<T>
	public abstract infix fun add(other: T): Mask<T>
	public abstract infix fun remove(other: T): Mask<T>
	public abstract fun inv(): Mask<T>

	abstract override fun contains(element: T): Boolean
	override fun containsAll(elements: Collection<T>): Boolean = elements.all { contains(it) }
	override fun isEmpty(): Boolean = size == 0
	abstract override fun iterator(): Iterator<T>
	abstract override val size: Int


	public operator fun plus(other: Mask<T>): Mask<T> = this or other
	public operator fun minus(other: Mask<T>): Mask<T> = this andNot other
	public operator fun times(other: Mask<T>): Mask<T> = this and other
	public operator fun plus(other: T): Mask<T> = this add other
	public operator fun minus(other: T): Mask<T> = this remove other
}

public class MaskBase<T> private constructor(public val items: List<T>, public val indexLookup: Map<T, Int>) {
	public val size: Int
		get() = items.size

	public companion object {
		private val deduplicate = mutableMapOf<List<*>, MaskBase<*>>()
		public operator fun <T> invoke(i: Iterable<T>): MaskBase<T> {
			val items = i.toList()
			deduplicate[items]?.let {
				@Suppress("UNCHECKED_CAST")
				return it as MaskBase<T>
			}

			val indexLookup = items.withIndex().associate { it.value to it.index }
			val base = MaskBase(items, indexLookup)
			deduplicate[items] = base
			return base
		}
	}
}

public class MaskLong<T>(public val base: MaskBase<T>, public val mask: Long) : Mask<T>() {
	override fun and(other: Mask<T>): Mask<T> {
		require(other is MaskLong<*> && other.base == base)
		return MaskLong(base, mask and other.mask)
	}

	override fun or(other: Mask<T>): Mask<T> {
		require(other is MaskLong<*> && other.base == base)
		return MaskLong(base, mask or other.mask)
	}

	override fun add(other: T): Mask<T> {
		if (other in this) return this
		val index = base.indexLookup[other]!!
		return MaskLong(base, mask or (1L shl index))
	}

	override fun remove(other: T): Mask<T> {
		if (other !in this) return this
		val index = base.indexLookup[other]!!
		return MaskLong(base, mask and (1L shl index).inv())
	}

	override fun inv(): Mask<T> {
		return MaskLong(base, mask.inv() and (1L shl base.size) - 1)
	}

	override fun iterator(): Iterator<T> {
		class MaskIterator(var mask: Long, val base: MaskBase<T>) : Iterator<T> {
			override fun hasNext(): Boolean = mask != 0L
			override fun next(): T {
				val index = mask.countTrailingZeroBits()
				mask = mask and (mask - 1)
				return base.items[index]
			}
		}

		return MaskIterator(mask, base)
	}

	override val size: Int
		get() = mask.countOneBits()

	override fun contains(element: T): Boolean {
		return mask and (1L shl base.indexLookup[element]!!) != 0L
	}


	override fun equals(other: Any?): Boolean = when {
		this === other -> true
		other !is MaskLong<*> -> false
		base != other.base -> false
		mask != other.mask -> false
		else -> true
	}

	override fun hashCode(): Int {
		var result = base.hashCode()
		result = 31 * result + mask.hashCode()
		return result
	}
}

public class MaskArray<T>(public val base: MaskBase<T>, public val mask: LongArray) : Mask<T>() {
	override fun and(other: Mask<T>): Mask<T> {
		require(other is MaskArray<*> && other.base == base)
		val newMask = LongArray(mask.size) { i -> mask[i] and other.mask[i] }
		return MaskArray(base, newMask)
	}

	override fun or(other: Mask<T>): Mask<T> {
		require(other is MaskArray<*> && other.base == base)
		val newMask = LongArray(mask.size) { i -> mask[i] or other.mask[i] }
		return MaskArray(base, newMask)
	}

	override fun add(other: T): Mask<T> {
		if (other in this) return this
		val index = base.indexLookup[other]!!
		val newMask = mask.copyOf()
		newMask[index / 64] = newMask[index / 64] or (1L shl index % 64)
		return MaskArray(base, newMask)
	}

	override fun remove(other: T): Mask<T> {
		if (other !in this) return this
		val index = base.indexLookup[other]!!
		val newMask = mask.copyOf()
		newMask[index / 64] = newMask[index / 64] and (1L shl index % 64).inv()
		return MaskArray(base, newMask)
	}

	override fun inv(): Mask<T> {
		val newMask = LongArray(mask.size) { i -> mask[i].inv() }
		newMask[mask.size - 1] = newMask[mask.size - 1] and (1L shl base.size % 64) - 1
		return MaskArray(base, newMask)
	}

	override fun iterator(): Iterator<T> {
		class MaskIterator(var mask: LongArray, val base: MaskBase<T>) : Iterator<T> {
			var index = 0
			override fun hasNext(): Boolean {
				while (index < mask.size && mask[index] == 0L) {
					index++
				}
				return index < mask.size
			}

			override fun next(): T {
				hasNext()  // ensure index is valid
				val subIndex = mask[index].countTrailingZeroBits()
				mask[index] = mask[index] and (mask[index] - 1)
				return base.items[index * 64 + subIndex]
			}
		}

		return MaskIterator(mask, base)
	}

	override val size: Int
		get() = mask.sumOf { it.countOneBits() }

	override fun contains(element: T): Boolean {
		val index = base.indexLookup[element]!!
		val mask = mask[index / 64]

		return mask and (1L shl index % 64) != 0L
	}


	override fun equals(other: Any?): Boolean = when {
		this === other -> true
		other !is MaskArray<*> -> false
		base != other.base -> false
		!mask.contentEquals(other.mask) -> false
		else -> true
	}

	override fun hashCode(): Int {
		var result = base.hashCode()
		result = 31 * result + mask.contentHashCode()
		return result
	}
}

public fun <T> Iterable<T>.emptyMask(): Mask<T> {
	val base = MaskBase(this)
	if (base.size <= 64) {
		return MaskLong(base, 0)
	}
	val mask = LongArray((base.size - 1) / 64 + 1)
	return MaskArray(base, mask)
}

