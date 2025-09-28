package io.github.ayushkumar.localeSyncApp

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import io.github.ayushkumar.localSync.presentation.StringManager
import io.github.ayushkumar.localSync.presentation.getLocalizedString
import io.github.ayushkumar.localeSyncApp.ui.theme.LocaleSyncTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var stringManager: StringManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocaleSyncTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        stringManager = stringManager
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, stringManager: StringManager) {
    val scope = rememberCoroutineScope()
    var currentLanguage by remember { mutableStateOf("en") }

    var refreshKey by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Select Language:")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            RadioButton(
                selected = currentLanguage == "en",
                onClick = {
                    currentLanguage = "en"
                    stringManager.setLanguage("en")
                    refreshKey++ // recompose all texts
                }
            )
            Text("English", modifier = Modifier.align(Alignment.CenterVertically))

            RadioButton(
                selected = currentLanguage == "hi",
                onClick = {
                    currentLanguage = "hi"
                    stringManager.setLanguage("hi")
                    refreshKey++
                }
            )
            Text("Hindi", modifier = Modifier.align(Alignment.CenterVertically))
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        val dummy = refreshKey

        Text(text = stringManager.getString("name"))
        Text(text = stringManager.getString("welcome_message"))
        Text(text = stringManager.getString("login_button"))
        Text(text = stringManager.getString("logout_button"))
        Text(text = stringManager.getString("error_network", 404))
        Text(text = stringManager.getString("settings_title"))

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            scope.launch {
                stringManager.updateLocalization()
                refreshKey++
            }
        }) {
            Text("Update Localization")
        }
    }
}




