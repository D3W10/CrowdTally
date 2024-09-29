package com.isel.g07.crowdtally.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val SETTINGS_VIEW = "settingsView"

@Composable
fun SettingsView(
    min: Int,
    max: Int,
    setMin: (Int) -> Unit = {},
    setMax: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize().testTag(SETTINGS_VIEW)) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)) {
            OutlinedTextField(
                value = min.toString(),
                onValueChange = { setMin(it.toIntOrNull() ?: 0) },
                label = { Text("Minimum Value") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = max.toString(),
                onValueChange = { setMax(it.toIntOrNull() ?: 0)},
                label = { Text("Maximum Value") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsView(0, 100)
}