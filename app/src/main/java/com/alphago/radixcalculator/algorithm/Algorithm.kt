package com.alphago.radixcalculator.algorithm

import Stack
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.pow

class Algorithm {
	companion object {
		fun calculate(value: String, oldBase: Int, newBase: Int): String {
			var output: String
			val newVal = convertToBase10(validateInput(value.uppercase()), oldBase).split('.')
			val characteristic = newVal[0].toInt()
			val mantissa = "0.${newVal[1]}".toDouble()
			output =
				"${convertCharacteristic(characteristic, newBase)}.${convertMantissa(mantissa, newBase)}"


			return output
		}

		fun String.IsNegative(): Boolean {
			return startsWith('-')
		}

		 fun charToInt(char: Char): Int {
			return (char.code - 55)
		}

		 fun intToChar(int: Int): Char {
			return (int + 55).toChar()
		}

		private fun convertMantissa(value: Double, newBase: Int): String {
			var output = ""
			val remainderStack = Stack()
			var v = value

			while (v > 0.0f && remainderStack.size() < 6) {
				remainderStack.push((v * newBase).toInt())
				v = ((v * newBase).toInt() - (v * newBase)).absoluteValue
			}

			while (!remainderStack.isEmpty()) {
				if (remainderStack.peek()!! > 9) {
					val numToChar: Char = intToChar(remainderStack.pop()!!)
					output += numToChar
				} else {
					output += remainderStack.pop()
				}
			}

			return output.reversed()
		}

		private fun convertCharacteristic(value: Int, newBase: Int): String {
			var output = ""
			val remainderStack = Stack()
			var v = value

			if (v == newBase) {
				return "10"
			}

			while (v >= newBase) {
				remainderStack.push(v % newBase)
				v /= newBase
			}
			remainderStack.push(v)


			while (!remainderStack.isEmpty()) {
				if (remainderStack.peek()!! > 9) {
					val numToChar: Char = intToChar(remainderStack.pop()!!)
					output += numToChar
				} else {
					output += remainderStack.pop()
				}
			}

			return output
		}

		 fun convertToBase10(value: String, oldBase: Int): String {
			val characteristic = value.split('.')[0].uppercase()
			val mantissa = value.split('.')[1].uppercase()

			var base10whole: Long = 0
			var base10frac = 0.0

			val wholeNum = ArrayList<Char>()
			val fracNum = ArrayList<Char>()

			for (char in characteristic)
			//if (char.isDigit())
				wholeNum.add(char)
			//	else wholeNum.add(charToInt(char).toChar())


			for (char in mantissa)
			//if (char.isDigit())
				fracNum.add(char)
			//	else fracNum.add(charToInt(char).toChar())

			for (char in wholeNum) {
				base10whole = if (char.isDigit())
					(oldBase * base10whole) + char.toString().toInt()
				else (oldBase * base10whole) + charToInt(char)
			}

			for (i in 0 until fracNum.size) {
				base10frac += if (fracNum[i].isDigit())
					(fracNum[i].toString().toDouble() *
							oldBase.toDouble().pow(-abs(i + 1).toDouble()))
				else (charToInt(fracNum[i]).toString().toDouble() *
						oldBase.toDouble().pow(-abs(i + 1).toDouble()))
			}
			println("$base10frac, ${(base10whole.toDouble() + base10frac)}")
			println("${(base10whole.toDouble() + base10frac)}")
			return "${(base10whole.toDouble() + base10frac)}"

		}

		private fun validateInput(value: String): String {
			return if (value.contains('.')) value
			else "$value.0"
		}
	}


}

