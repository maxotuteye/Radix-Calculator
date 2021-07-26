package com.alphago.radixcalculator.algorithm

import android.util.Log
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.pow

class Algorithm {
	companion object {
		fun calculate(value: String, oldBase: Int, newBase: Int): String {
			var output = ""
			var temp = validateInput(value.uppercase())
			if (temp.isNegative()) {
				output = "-"
				temp = temp.removePrefix("-")
			}
			val newVal = validateInput(convertToBase10(temp, oldBase)).split('.')
			val characteristic = newVal[0].toULong()
			val mantissa = "0.${newVal[1]}".toDouble()
			output +=
				validateInput(
					"${convertCharacteristic(characteristic, newBase)}.${
						convertMantissa(
							mantissa,
							newBase
						)
					}"
				)
			return output
		}

		private fun String.isNegative(): Boolean {
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

			while (v > 0.0f && remainderStack.size() < 20) {
				remainderStack.push((v * newBase).toULong())
				v = ((v * newBase).toInt() - (v * newBase)).absoluteValue
			}

			while (!remainderStack.isEmpty()) {
				if (remainderStack.peek()!! > 9u) {
					val numToChar: Char = intToChar(remainderStack.pop()!!.toInt())
					output += numToChar
				} else {
					output += remainderStack.pop()
				}
			}

			return output.reversed()
		}

		private fun convertCharacteristic(value: ULong, newBase: Int): String {
			var output = ""
			val remainderStack = Stack()
			var v = value

			if (v.equals(newBase)) {
				return "10"
			}

			while (v >= newBase.toULong()) {
				remainderStack.push(v % newBase.toULong())
				v /= newBase.toULong()
			}
			remainderStack.push(v)

			while (!remainderStack.isEmpty()) {
				if (remainderStack.peek()!! > 9u) {
					val numToChar: Char = intToChar(remainderStack.pop()!!.toInt())
					output += numToChar
				} else {
					output += remainderStack.pop()
				}
			}

			Log.e("ccx", "output is $output, length is ${output.length}")
			return output
		}

		private fun convertToBase10(value: String, oldBase: Int): String {
			val characteristic = value.split('.')[0].uppercase()
			val mantissa = value.split('.')[1].uppercase()

			var base10whole: ULong = 0u
			var base10frac = 0f

			for (char in characteristic) {
				base10whole = try {
					if (char.isDigit())
						(oldBase.toULong() * base10whole) + char.toString().toULong()
					else (oldBase.toULong() * base10whole) + charToInt(char).toULong()
				} catch (e: Exception) {
					ULong.MAX_VALUE
				}
			}
			Log.e("ccx", "b10whole is ${base10whole}")

			for (i in mantissa.indices) {
				base10frac +=
					if (mantissa[i].isDigit()) {
						(mantissa[i].toString().toDouble() *
								oldBase.toDouble().pow(-abs(i + 1).toDouble())).toFloat()
					} else {
						(charToInt(mantissa[i]).toString().toDouble() *
								oldBase.toDouble().pow(-abs(i + 1).toDouble())).toFloat()
					}
			}
			Log.e("ccx",validateInput("HEY! ${(base10whole + base10frac.toUInt())}"))
			return validateInput("${(base10whole + base10frac.toUInt())}")
		}

		private fun validateInput(value: String): String {
			return if (value.contains('.')) {
				if (!value.contains('+')) {
					if (value.split('.')[1].isNotEmpty()) {
						value
					} else "${value}0"
				} else ULong.MAX_VALUE.toString()
			} else {
				if (value.contains('+')) {
					ULong.MAX_VALUE.toString() + ".0"
				} else "$value.0"
			}
		}
	}


}

