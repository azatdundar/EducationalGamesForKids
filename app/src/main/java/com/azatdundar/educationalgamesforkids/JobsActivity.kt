package com.azatdundar.educationalgamesforkids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azatdundar.educationalgamesforkids.ui.theme.EducationalGamesForKidsTheme
import kotlin.random.Random

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
                    JobsScreen(name = "name")
                }
            }
        }
    }
}

@Composable
fun JobsScreen(name: String) {
    var questionNumber by remember{ mutableStateOf(0) }
    var isClicked by remember { mutableStateOf(false)}
    var jobImages = mutableListOf(R.drawable.architect, R.drawable.doctor, R.drawable.engineer, R.drawable.teacher)
    var indexes = MutableList(jobImages.size-1){it}
    var randomIndex1 = indexes.random()
    var image1 = jobImages[randomIndex1]
    indexes.remove(randomIndex1)
    var randomIndex2 = indexes.random()
    var image2 = jobImages[randomIndex2]
    indexes.add(randomIndex2)


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Name Of Job")
        Row {
            Job(imageRes = image1, name = "name1") {
                //TODO
            }
            Spacer(modifier = Modifier.size(10.dp))

            Job(imageRes = image2, name = "name2") {
                //TODO
            }
        }
    }
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
        //Greeting("Android")
    }
}