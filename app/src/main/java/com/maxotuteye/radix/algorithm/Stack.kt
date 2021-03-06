package com.maxotuteye.radix.algorithm

class Stack {
	private val elements: MutableList<ULong> = mutableListOf()

	fun isEmpty() = elements.isEmpty()

	fun size() = elements.size

	fun push(item: ULong) = elements.add(item)

	fun pop(): ULong? {
		val item = elements.lastOrNull()
		if (!isEmpty()) {
			elements.removeAt(elements.size - 1)
		}
		return item
	}

	fun peek(): ULong? = elements.lastOrNull()

	override fun toString(): String = elements.toString()
}