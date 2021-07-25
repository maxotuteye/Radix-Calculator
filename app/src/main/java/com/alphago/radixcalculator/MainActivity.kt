package com.alphago.radixcalculator

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alphago.radixcalculator.ui.theme.RadixCalculatorTheme
import org.intellij.lang.annotations.JdkConstants
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
	private val SMALL_TEXT_SIZE = 20.sp
	private var sliderToBaseValue = mutableStateOf(2)
	private var sliderFromBaseValue = mutableStateOf(2)
	private var selectedBase = mutableStateOf(2)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			RadixCalculatorTheme {
				window.statusBarColor = MaterialTheme.colors.primary.toArgb()
				window.navigationBarColor = MaterialTheme.colors.background.toArgb()
				Surface(color = MaterialTheme.colors.background) {
					Column(
						modifier = Modifier
							.background(MaterialTheme.colors.primary)
							.fillMaxHeight()
							.fillMaxWidth()
					) {
						Text(
							"Radix Calculator", modifier = Modifier
								.align(Alignment.CenterHorizontally)
								.padding(0.dp, 0.dp, 0.dp, 10.dp),
							fontSize = 40.sp,
							color = MaterialTheme.colors.surface
						)
						Card(
							shape = RoundedCornerShape(8),
							modifier = Modifier
								.fillMaxWidth(0.85f)
								.fillMaxHeight(0.2f)
								.align(Alignment.CenterHorizontally)
								.padding(0.dp, 0.dp, 0.dp, 0.dp),
							backgroundColor = MaterialTheme.colors.background
						) {
							Column(
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = "0.0",
									fontSize = 50.sp,
									modifier = Modifier
										.align(Alignment.CenterHorizontally)
										.padding(0.dp, 20.dp, 0.dp, 0.dp),
									color = MaterialTheme.colors.primary
								)
							}
						}
						Column(
							modifier = Modifier
								.padding(0.dp, 30.dp, 0.dp, 0.dp)
								.fillMaxSize()
								.background(MaterialTheme.colors.background)

						) {
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
									},
									valueRange = 2f..27f,
									modifier = Modifier
										.padding(0.dp, 10.dp, 0.dp, 0.dp),
									steps = 25,
								)

								Text(
									"To Base", modifier = Modifier
										.padding(0.dp, 20.dp)
										.align(Alignment.CenterHorizontally),
									fontSize = SMALL_TEXT_SIZE,
									color = MaterialTheme.colors.onSurface
								)
							}

						}

					}
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	RadixCalculatorTheme {
		Greeting("Alpha Go")
	}
}
