# Radix-Calculator
Radix Calculator app with Jetpack Compose.
View on the Play Store: https://play.google.com/store/apps/details?id=com.maxotuteye.radix

## About
Radix Calculator allows users to easily convert a value from any base to any other base, within the range of base 2 (binary) to base 27 (septemvigesimal).

## Algorithm
Conversion is first done from the given base to base 10, which serves as an intermediary base.
The value in base 10 is then converted to the desired base.
Conversions are done using recursive multiplications with varying exponents.

## Tools
The app was built with Jetpack Compose on Android Studio using Kotlin.

## Architecture and Design Patterns
Radix Calculator was built with the State pattern and the MVC architecture

## Build
Clone the project in a version of Android Studio compatible with Jetpack Compose.
Allow Android Studio to download dependencies.
Build the project.

## Screenshots
![image](https://user-images.githubusercontent.com/37118417/173671270-d3f9499d-831e-43db-a32f-32eb3ae605f5.png)
![image](https://user-images.githubusercontent.com/37118417/173671271-b1a07f22-a87f-4d36-b1e3-9ac7a2d6fc24.png)
