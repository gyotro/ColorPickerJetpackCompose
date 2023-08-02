package com.example.colorpicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colorpicker.ui.theme.ColorPickerTheme
import com.github.skydoves.colorpicker.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ColorPicker()
                }
            }
        }
    }
}

@Composable
fun ColorPicker() {
    var colorController = rememberColorPickerController().apply {
        setWheelRadius(10.dp)
        setWheelColor(Color.Black)
        setWheelAlpha(0.5f)
    }

    var hexColor by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(10.dp),
                controller = colorController,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    //   Log.d("Color", "${colorEnvelope.hexCode}")
                    hexColor = colorEnvelope.hexCode
                })
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = stringResource(R.string.transparency), color = if (isSystemInDarkTheme())
            Color.White
        else
            Color.Black)
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AlphaSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = colorController,
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = stringResource(R.string.brightness), color = if (isSystemInDarkTheme())
            Color.White
        else
            Color.Black)
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BrightnessSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = colorController,
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "#$hexColor", color =
                if (isSystemInDarkTheme())
                    Color.White
                else
                    Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorController.selectedColor.value)
            ) {
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorPickerTheme {
        ColorPicker()
    }
}