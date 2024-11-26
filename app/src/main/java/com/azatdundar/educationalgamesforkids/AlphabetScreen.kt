package com.azatdundar.educationalgamesforkids

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.util.VelocityTrackerStrategyUseImpulse

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TextColors{
    RED,
    GREEN,
    WHITE,
    YELLOW
}

@Composable
fun AlphabetScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
            LetterButton(letter = "A")
            LetterButton(letter = "B")

        }
    }

}


@Composable
fun LetterButton(letter : String){
    Button(modifier = Modifier.size(120.dp),
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
fun LetterText(letter : String){
    val gaamliFontFamily = FontFamily(
        Font(R.font.gaamli)
    )



    Text(text = letter, fontSize = 60.sp,
        fontFamily = gaamliFontFamily,
        color = Color.Blue
        )
}

@Preview(showBackground = true, name = "Alphabet Screen Preview")
@Composable
fun PreviewAlphabetScreen() {
    AlphabetScreen()
}