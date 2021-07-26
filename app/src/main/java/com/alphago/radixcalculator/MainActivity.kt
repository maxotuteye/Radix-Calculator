package com.alphago.radixcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alphago.radixcalculator.algorithm.Algorithm
import com.alphago.radixcalculator.ui.theme.RadixCalculatorTheme
import kotlin.math.roundToInt

private val SMALL_TEXT_SIZE = 20.sp
private var newBase = mutableStateOf(2)
private var selectedBase = mutableStateOf(10)
private var output = mutableStateOf("0.0")
private var input = mutableStateOf("")
private var nBase = 10

class MainActivity : ComponentActivity() {


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			RadixCalculatorTheme {
				window.statusBarColor = MaterialTheme.colors.primary.toArgb()
				window.navigationBarColor = MaterialTheme.colors.primaryVariant.toArgb()
				Surface(
					modifier = Modifier.background(
						Brush.radialGradient(
							colors = listOf(
								MaterialTheme.colors.primary,
								MaterialTheme.colors.background
							)
						)
					)
				) {
					Column(
						modifier = Modifier
							.background(
								Brush.verticalGradient(
									colors = listOf(
										MaterialTheme.colors.primary,
										MaterialTheme.colors.primaryVariant
									)
								)
							)
							.fillMaxHeight()
							.fillMaxWidth()
					) {
						Text(
							"Radix Calculator", modifier = Modifier
								.align(Alignment.CenterHorizontally)
								.padding(0.dp, 0.dp, 0.dp, 30.dp),
							fontSize = 40.sp,
							color = MaterialTheme.colors.surface
						)
						Card(
							shape = RoundedCornerShape(8),
							modifier = Modifier
								.fillMaxWidth(0.85f)
								.wrapContentHeight()
								.align(Alignment.CenterHorizontally)
								.padding(0.dp, 0.dp, 0.dp, 0.dp),
							backgroundColor = MaterialTheme.colors.background
						) {
							Column(
								modifier = Modifier
									.fillMaxWidth()
									.wrapContentHeight(),
								verticalArrangement = Arrangement.Center
							) {
								Text(
									text = output.value,
									fontSize = 50.sp,
									modifier = Modifier
										.align(Alignment.CenterHorizontally)
										.padding(10.dp, 10.dp, 10.dp, 10.dp),
									color = MaterialTheme.colors.primary,
									textAlign = TextAlign.Center
								)
							}
						}

						Column(
							modifier = Modifier
								.padding(0.dp, 30.dp, 0.dp, 0.dp)
								.fillMaxSize()
								.background(MaterialTheme.colors.background)

						) {
							Card(
								shape = RoundedCornerShape(8),
								modifier = Modifier
									.fillMaxWidth(0.85f)
									//.fillMaxHeight(0.25f)
									.wrapContentHeight()
									.align(Alignment.CenterHorizontally)
									.padding(0.dp, 30.dp, 0.dp, 20.dp),
								backgroundColor = MaterialTheme.colors.primaryVariant
							) {
								TextField(
									value = input.value,
									onValueChange = {
										//TODO: add code for handling new input and checking validity
//										if (selectedBase.value > nBase) nBase = selectedBase.value
//										nBase = if (input.value.isNotEmpty()) nBase else 10
//
//										if (it.length <= 45 && !isNumberInvalid(it)) {
//											input.value = it
//											it.forEach { c ->
//												if (c.isDigit() && c != '-' && c != '.') {
//													if (c.toString().toInt() + 1 > nBase)
//														nBase = c.toString().toInt() + 1
//												} else
//													if (Algorithm.charToInt(c.uppercaseChar()) + 1
//														> nBase && c != '-' && c != '.'
//													)
//														nBase =
//															Algorithm.charToInt(c.uppercaseChar()) + 1
//											}
//
//											selectedBase.value = nBase
//											output.value = calculate(it, nBase, newBase.value)
//										}
										correctBases(it, selectedBase.value)

									},
									modifier = Modifier,
									maxLines = 3,
									keyboardOptions = KeyboardOptions(
										capitalization = KeyboardCapitalization.Characters,
										autoCorrect = false,
										KeyboardType.Ascii,
									),
									textStyle = TextStyle(
										color = MaterialTheme.colors.background,
										fontSize = 35.sp
									),
									label = { Text("Enter Value",
									fontSize = 20.sp,
									color = MaterialTheme.colors.background) }
								)
							}

							Column(
								modifier = Modifier
									.padding(30.dp, 30.dp, 30.dp, 0.dp)
									.fillMaxSize()
									.background(MaterialTheme.colors.background)
							) {
								Row(
									modifier = Modifier
										.height(60.dp)
										.fillMaxWidth(),
									horizontalArrangement = Arrangement.SpaceBetween
								) {
									Text(
										"From Base", modifier = Modifier
											.padding(5.dp, 20.dp, 0.dp, 0.dp)
											.fillMaxWidth(0.5f),
										fontSize = SMALL_TEXT_SIZE,
										color = MaterialTheme.colors.onSurface
									)

									Text(
										"${selectedBase.value}",
										modifier = Modifier
											.padding(0.dp, 0.dp, 5.dp, 0.dp)
											.fillMaxWidth(0.5f),
										fontSize = 50.sp,
										color = MaterialTheme.colors.primary,
										textAlign = TextAlign.End,
									)
								}

								Slider(
									value = selectedBase.value.toFloat(),
									onValueChange = {
										selectedBase.value = it.roundToInt()
										correctBases(input.value, it.roundToInt())
									},
									valueRange = 2f..27f,
									modifier = Modifier
										.padding(0.dp, 10.dp, 0.dp, 0.dp),
									steps = 25,
								)
								Column(
									modifier = Modifier
										.padding(0.dp, 30.dp, 5.dp, 0.dp)
										.fillMaxSize()
										.background(MaterialTheme.colors.background)
								) {
									Row(
										modifier = Modifier
											.height(60.dp)
											.fillMaxWidth()
											.padding(0.dp, 0.dp, 0.dp, 0.dp),
										horizontalArrangement = Arrangement.SpaceBetween
									) {
										Text(
											"To Base", modifier = Modifier
												.padding(5.dp, 20.dp, 0.dp, 0.dp)
												.fillMaxWidth(0.5f),
											fontSize = SMALL_TEXT_SIZE,
											color = MaterialTheme.colors.onSurface
										)

										Text(
											"${newBase.value}",
											modifier = Modifier
												.padding(0.dp, 0.dp, 5.dp, 0.dp)
												.fillMaxWidth(0.5f),
											fontSize = 50.sp,
											color = MaterialTheme.colors.primary,
											textAlign = TextAlign.End,
										)
									}
									Slider(
										value = newBase.value.toFloat(),
										onValueChange = {
											newBase.value = it.roundToInt()
											correctBases(input.value, selectedBase.value)
										},
										valueRange = 2f..27f,
										modifier = Modifier
											.padding(0.dp, 10.dp, 0.dp, 0.dp),
										steps = 25,
									)
								}
							}
						}
					}
				}
			}
		}
	}
}

fun calculate(value: String, oldBase: Int, newBase: Int): String {
	return Algorithm.calculate(value, oldBase, newBase)
}

fun isNumberInvalid(text: String): Boolean {
	val regex1 = Regex("[^-0-9A-Qa-q.]+")
	val regex2 = Regex("[-](^a-q|A-Q|0-9).+")
	return regex1.matches(text) || regex2.matches(text) || text.count { it == '-' } > 1 || text.count { it == '.' } > 1
}

fun correctBases(s: String, sliderValue: Int) {
	//if (sliderValue > nBase) nBase = sliderValue
	nBase = if (input.value.isNotEmpty()) nBase else 2

	if (s.length <= 45 && !isNumberInvalid(s)) {
		input.value = s
		s.forEach { c ->
			if (c.isDigit() && c != '-' && c != '.') {
				if (c.toString().toInt() + 1 > nBase)
					nBase = c.toString().toInt() + 1
			} else
				if (Algorithm.charToInt(c.uppercaseChar()) + 1
					> nBase && c != '-' && c != '.'
				)
					nBase =
						Algorithm.charToInt(c.uppercaseChar()) + 1
		}
		Log.e(
			"ccx",
			"nBase is $nBase, sliderValue is $sliderValue, selected is ${selectedBase.value}"
		)

		if (nBase > sliderValue)
			selectedBase.value = nBase
		else selectedBase.value = sliderValue
		output.value = calculate(s, selectedBase.value, newBase.value)
	}
}
