package com.isel.g07.crowdtally.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val COUNTING_VIEW = "countingView"

@Composable
fun CountingView(
    value: Int,
    min: Int,
    max: Int,
    increment: () -> Unit,
    decrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize().testTag(COUNTING_VIEW)) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(value.toString(), fontSize = 60.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Controls(
                value = value,
                min = min,
                max = max,
                increment = { if (value.inc() <= max) increment() },
                decrement = { if (value.dec() >= min) decrement() }
            )
        }
    }
}

@Composable
fun Controls(value: Int, min: Int, max: Int, increment: () -> Unit, decrement: () -> Unit) {
    Row {
        Button(enabled = value > min, modifier = Modifier.size(60.dp), onClick = decrement) {
            Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(enabled = value < max, modifier = Modifier.size(60.dp), onClick = increment) {
            Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountingPreview() {
    var value by rememberSaveable { mutableStateOf(0) }

    CountingView(value, 0, 100, { value = value.inc() }, { value = value.dec() })
}