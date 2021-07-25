package com.alphago.radixcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = Color.fromHex("aaaaaa"),
	primaryVariant = Color.fromHex("2f3545"),
	secondary = Color.fromHex("15496d"),
	background = Color.fromHex("000000"),
	surface = Color.fromHex("f9f9fa"),
	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.Black
)

private val LightColorPalette = lightColors(
	primary = Color.fromHex("05dddd"),
	primaryVariant = Color.fromHex("05ffff"),
	secondary = Teal200,
	background = Color.fromHex("000000"),
	surface = Color.White,
	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.Black,
	onSurface = Color.fromHex("888888"),

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