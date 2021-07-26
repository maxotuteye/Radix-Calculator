package com.alphago.radixcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = Color.fromHex("05ddff"),
	primaryVariant = Color.fromHex("05dddd"),
	secondary = Color.Black,
	background = Color.fromHex("000000"),
	surface = Color.White,
	onPrimary = Color.Black,
	onSecondary = Color.Black,
	onBackground = Color.White,
	onSurface = Color.fromHex("888888"),
)

private val LightColorPalette = lightColors(
	primary = Color.fromHex("8817ab"),
	primaryVariant = Color.fromHex("67069a"),
	secondary = Color.Black,
	background = Color.fromHex("eeeeee"),
	surface = Color.White,
	onPrimary = Color.Black,
	onSecondary = Color.Black,
	onBackground = Color.Black,
	onSurface = Color.fromHex("333333"),
	)

@Composable
fun RadixCalculatorTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors = if (darkTheme) {
		DarkColorPalette
	} else {
		LightColorPalette
	}

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}

fun Color.Companion.fromHex(colorString: String) =
	Color(android.graphics.Color.parseColor("#$colorString"))