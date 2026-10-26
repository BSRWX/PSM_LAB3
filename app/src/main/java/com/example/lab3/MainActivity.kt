package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFF1C1B1B))
                {
                    innerPadding -> JokeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun JokeScreen(modifier: Modifier = Modifier)
{
    var jokeCount by remember { mutableIntStateOf(5) }

    val primaryTeal = Color(0xFF006D40)
    val cardBackground = Color(0xFF666666)
    val selectedChipBg = Color(0xFF006D40)

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(
            text="Joke",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            OutlinedButton(onClick = {}) {
                Text("Systemowy", color = Color.Gray)
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedChipBg,
                    contentColor = Color.Black
                )
            )
            {
                Text("Polski")
            }

            OutlinedButton(onClick = {}) {
                Text("English", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = cardBackground,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Test",
                modifier = Modifier.padding(24.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { jokeCount++ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = primaryTeal),
            modifier = Modifier.size(160.dp)
        ) {
            Text(
                text = "Pokaż żart",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Obejrzano $jokeCount żartów",
            color = primaryTeal,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cześć, gościu! Oto Twój żart numer $jokeCount.",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
