package com.example.lab3

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.lab3.ui.theme.Lab3Theme
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF1C1B1B)
                ) { innerPadding ->
                    JokeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun JokeScreen(modifier: Modifier = Modifier) {
    var jokeCount by remember { mutableIntStateOf(5) }
    var currentJokeIndex by remember { mutableIntStateOf(0) }

    val jokes = stringArrayResource(R.array.jokes_array)

    val currentLocales = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    val isSystem = currentLocales.isEmpty()
    val isPl = currentLocales == "pl"
    val isEn = currentLocales == "en"

    val primaryTeal = Color(0xFF006D40)
    val cardBackground = Color(0xFF666666)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isSystem) {
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = primaryTeal)) {
                    Text(stringResource(id = R.string.system_language))
                }
            } else {
                OutlinedButton(onClick = {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
                }) {
                    Text(stringResource(id = R.string.system_language), color = Color.Gray)
                }
            }

            if (isPl) {
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = primaryTeal)) {
                    Text("Polski")
                }
            } else {
                OutlinedButton(onClick = {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("pl"))
                }) {
                    Text("Polski", color = Color.Gray)
                }
            }

            if (isEn) {
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = primaryTeal)) {
                    Text("English")
                }
            } else {
                OutlinedButton(onClick = {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
                }) {
                    Text("English", color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = cardBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = jokes[currentJokeIndex % jokes.size],
                modifier = Modifier.padding(24.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                jokeCount++
                currentJokeIndex = Random.nextInt(jokes.size)
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = primaryTeal),
            modifier = Modifier.size(160.dp)
        ) {
            Text(
                text = stringResource(R.string.show_joke),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = pluralStringResource(
                id = R.plurals.jokes_seen,
                count = jokeCount,
                jokeCount
            ),
            color = primaryTeal,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.jokes_counter, jokeCount),
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}