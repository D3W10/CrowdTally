package com.isel.g07.crowdtally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.isel.g07.crowdtally.ui.theme.CrowdTallyTheme
import com.isel.g07.crowdtally.ui.views.CountingView
import com.isel.g07.crowdtally.ui.views.SettingsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout() {
    CrowdTallyTheme {
        var value by rememberSaveable { mutableStateOf(0) }
        var min by rememberSaveable { mutableStateOf(0) }
        var max by rememberSaveable { mutableStateOf(100) }
        var settings by rememberSaveable { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        if (settings)
                            IconButton(onClick = { settings = false }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.back)
                                )
                            }
                    },
                    actions = {
                        if (!settings)
                            IconButton(onClick = { settings = true }) {
                                Icon(Icons.Outlined.Settings, contentDescription = stringResource(R.string.settings))
                            }
                    }
                )
            }
        ) { innerPadding ->
            if (!settings)
                CountingView(
                    value = value,
                    min = min,
                    max = max,
                    increment = { value = value.inc() },
                    decrement = { value = value.dec() },
                    modifier = Modifier.padding(innerPadding)
                )
            else
                SettingsView(
                    min = min,
                    max = max,
                    setMin = { min = it; value = min },
                    setMax = { max = it; value = min },
                    modifier = Modifier.padding(innerPadding)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppLayout()
}