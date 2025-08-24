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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmhashanmd.dagger.binds.BindsInterface
import com.dmhashanmd.dagger.model.Car
import com.dmhashanmd.dagger.model.Engine
import com.dmhashanmd.dagger.ui.theme.DaggerTheme
import com.dmhashanmd.dagger.viewmodel.AssistedViewModel
import com.dmhashanmd.dagger.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var engine: Provider<Engine>

    @Inject lateinit var bindsInterface: BindsInterface

    @Inject
    lateinit var car: Car

    @Inject
    lateinit var assistedInterface: AssistedViewModel.AssistedViewModelInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val assistedViewModel = assistedInterface.create("hashan")
            val uiState by assistedViewModel.uiState.collectAsState()

            DaggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = engine.get().name,
                        modifier = Modifier.padding(innerPadding),
                        bindsInterface.hello(),
                        car.engine.name,
                        assistedViewModel.userId
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    hello: String,
    contructerInject: String,
    userId: String
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val transmission by viewModel._transmission.collectAsState()

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

        Text(
            text = transmission.name,
            modifier = modifier
        )
        Text(
            text = "Construct inject: $contructerInject",
            modifier = modifier
        )

        Text(
            text = "userId: $userId",
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
        Greeting(
            "Android",
            hello = "bind",
            contructerInject = "Construct",
            userId = "hashan"
        )
    }
}