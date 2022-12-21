package com.example.basicscodelab1


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab1.ui.theme.BasicsCodelab1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelab1Theme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp(modifier: Modifier = Modifier) {

        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }


        Surface(modifier) {
            if (shouldShowOnboarding) {
                OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
            } else {
                Greetings()
            }
        }
    }

    @Composable
    private fun Greetings(
        modifier: Modifier = Modifier,
        names: List<String> = List(1000) { "$it" }
    ) {
        LazyColumn(modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

    @Composable
    private fun Greeting(name: String) {

        var expanded by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            if (expanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMedium
            )

        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
        ) {
            Row() {
                Column(
                    Modifier
                        .padding(24.dp)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                        .weight(1f)
                ) {
                    Text(
                        "Hello $name",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                IconButton(
                    modifier = Modifier.padding(24.dp),
                    onClick = { expanded = !expanded },
                    Icon(
                        Icons.
                    )

                    ) {
                    Text(if (expanded) "Show less" else "Show more")
                }
            }
        }
    }


    @Composable
    fun OnboardingScreen(
        modifier: Modifier = Modifier, onContinueClicked: () -> Unit
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }

    @Preview(
        showBackground = true,
        widthDp = 320,
        heightDp = 320,
        uiMode = UI_MODE_NIGHT_YES,
        name = "Dark"
    )
    @Composable
    fun Dark() {
        BasicsCodelab1Theme {
            Greetings()
        }
    }

    @Preview(
        showBackground = true,
        widthDp = 320,
        heightDp = 320,
        name = "Light"
    )
    @Composable
    fun Light() {
        BasicsCodelab1Theme {
            Greetings()
        }
    }

    @Preview(
        showBackground = true,
        widthDp = 320,
        heightDp = 320,
        name = "General"
    )
    @Composable
    fun Preview() {
        BasicsCodelab1Theme {
            MyApp(Modifier.fillMaxSize())
        }
    }

}

