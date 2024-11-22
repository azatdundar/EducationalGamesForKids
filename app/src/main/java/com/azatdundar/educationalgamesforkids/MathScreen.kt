package com.azatdundar.educationalgamesforkids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp


@Composable

fun MathScreen(navController: NavHostController) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color.Green
        ) {
        Question()

    }
}



@Composable
fun Question(){

    val numbers = (0..20).toMutableList()


    val questionData = GenerateQuestion()
    val question = questionData["question"] as String
    val answer = questionData["answer"] as Int

    numbers.remove(answer)

    println("Question: $question")
    println("Answer: $answer")

    Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        QuestionText(question = question)
        Spacer(modifier = Modifier.padding(25.dp))
        Row {
            ButtonText(number = numbers.random())
            Spacer(modifier = Modifier.padding(30.dp))
            ButtonText(number = answer)
        }
    }


}
@Composable
fun QuestionText(question : String){
    val gaamliFontFamily = FontFamily(
        Font(R.font.gaamli)
    )

    Text(text = question,
        color = Color.Blue,
        style = TextStyle(
            fontFamily = gaamliFontFamily,
            fontSize = 90.sp
        )

    )


}

@Composable

fun ButtonText(number: Int){
    Button(onClick = {
        /*TODO*/
    },
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(16.dp)
        ) {
        Text(text = number.toString(), fontSize = 50 .sp
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMathScreen() {
    // Burada NavHostController'ı geçici olarak oluşturuyoruz
    val navController = rememberNavController() // Bu fonksiyonu kullanabilmek için 'import androidx.navigation.compose.rememberNavController' eklemeyi unutmayın
    MathScreen(navController = navController)
}

