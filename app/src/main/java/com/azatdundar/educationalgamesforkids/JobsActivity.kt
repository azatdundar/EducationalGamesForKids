package com.azatdundar.educationalgamesforkids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.azatdundar.educationalgamesforkids.ui.theme.EducationalGamesForKidsTheme

class JobsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationalGamesForKidsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun Job(imageRes : Int,name : String, onClick : ()->Unit ){
    val image : Painter = painterResource(id = imageRes)
    Image(painter = image, contentDescription = name, modifier = Modifier.clickable(onClick = onClick))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    EducationalGamesForKidsTheme {
        Greeting("Android")
    }
}