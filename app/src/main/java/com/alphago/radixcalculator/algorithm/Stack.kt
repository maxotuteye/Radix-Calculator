class Stack {
	private val elements: MutableList<Int> = mutableListOf()

	fun isEmpty() = elements.isEmpty()

	fun size() = elements.size

	fun push(item: Int) = elements.add(item)

	fun pop(): Int? {
		val item = elements.lastOrNull()
		if (!isEmpty()) {
			elements.removeAt(elements.size - 1)
		}
		return item
	}

	fun peek(): Int? = elements.lastOrNull()

	override fun toString(): String = elements.toString()
}