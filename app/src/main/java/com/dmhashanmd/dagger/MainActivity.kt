package com.dmhashanmd.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dmhashanmd.dagger.binds.BindsInterface
import com.dmhashanmd.dagger.model.Engine
import com.dmhashanmd.dagger.ui.theme.DaggerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var engine: Engine

    @Inject lateinit var bindsInterface: BindsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = engine.name,
                        modifier = Modifier.padding(innerPadding),
                        bindsInterface.hello()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, hello: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Text(
            text = hello,
            modifier = modifier
        )
        Button({

        }){
            Text(text = "Click Me")
        }
    }

}

@Preview(showBackground = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape")
@Composable
fun GreetingPreview() {
    DaggerTheme {
        Greeting("Android", hello = "bind")
    }
}