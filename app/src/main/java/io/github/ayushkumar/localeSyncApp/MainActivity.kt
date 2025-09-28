package io.github.ayushkumar.localeSyncApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.ayushkumar.localSync.presentation.getLocalizedString
import io.github.ayushkumar.localeSyncApp.ui.theme.LocaleSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocaleSyncTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen( modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = context.getLocalizedString("welcome_message") ,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    LocaleSyncTheme {
        MainScreen()
    }
}