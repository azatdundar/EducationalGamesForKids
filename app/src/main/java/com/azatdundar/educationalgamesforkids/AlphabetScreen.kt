package com.azatdundar.educationalgamesforkids

import android.speech.tts.TextToSpeech
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun AlphabetScreen(navController: NavHostController, tts: TextToSpeech?) {

    val alphabet = listOf(
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    )

    val sizeOfAlphabet = alphabet.size
    var questionNumber by remember{ mutableStateOf(0) }
    var letter :Char = alphabet[questionNumber]


    Surface(modifier = Modifier.fillMaxSize(),
        color = Color(red = 255, green = 223, blue = 186)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
            LetterButton(letter = letter)
            Spacer(modifier = Modifier.padding(40.dp))
            Row {
                previousButton(numOfChar = sizeOfAlphabet, numOfQuestions = questionNumber) {
                    if(questionNumber>0){
                        questionNumber--
                    }
                }

                nextButton(numOfChar = sizeOfAlphabet, numOfQuestions = questionNumber) {
                    if(questionNumber<sizeOfAlphabet-1){
                        questionNumber++
                    }
                }
            }
        }

    }

}



@Composable
fun LetterButton(letter : Char){

    Button(modifier = Modifier.size(200.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            //*TODO*
        })
    {
        LetterText(letter = letter)
    }

    Spacer(Modifier.padding(10.dp))
}

@Composable
fun LetterText(letter : Char){
    val gaamliFontFamily = FontFamily(
        Font(R.font.gaamli)
    )



    Text(text = letter.toString(), fontSize = 60.sp,
        fontFamily = gaamliFontFamily,
        color = Color.Blue
        )
}

@Composable
fun previousButton(numOfChar: Int, numOfQuestions:Int, onClick:()->Unit){
    Button(onClick = { onClick() },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor =Color(0xFF4fa3e3))

    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(30.dp))
    }

}

@Composable
fun nextButton(numOfChar : Int, numOfQuestions:Int, onClick:()->Unit){
    Button(onClick = {
            onClick()
    },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor =Color(0xFF4fa3e3))
        ) {
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(30.dp))
    }
}
@Preview(showBackground = true, name = "Alphabet Screen Preview")
@Composable
fun PreviewAlphabetScreen() {
    val navController = rememberNavController()
    Surface {
        AlphabetScreen(navController, tts = null)
    }
}