package com.isel.g07.crowdtally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isel.g07.crowdtally.ui.theme.CrowdTallyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLayout()
        }
    }
}

@Composable
fun AppLayout() {
    CrowdTallyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var count by remember { mutableStateOf(0) }

            fun increment() {
                count++;
            }

            fun decrement() {
                if (count <= 0)
                    return;

                count--;
            }

            Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("$count", fontSize = 60.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Controls(increment = { increment() }, decrement = { decrement() })
            }
        }
    }
}

@Composable
fun Controls(increment: () -> Unit, decrement: () -> Unit) {
    Row {
        Button(modifier = Modifier.size(60.dp), onClick = decrement) {
            Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(modifier = Modifier.size(60.dp), onClick = increment) {
            Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppLayout()
}