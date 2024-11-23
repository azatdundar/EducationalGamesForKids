package com.azatdundar.educationalgamesforkids

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.os.requestProfiling


@Composable
fun MathScreen(navController: NavHostController) {
    val numbers = (0..20).toMutableList()

    val questions = Array(30) {""}
    val answers = Array(30){0}

    var questionNumber  by remember { mutableStateOf(0) }
    val questionsData = GenerateQuestion()

    for(i in 0..29){
        questions[i] = questionsData["question"] as String
        answers[i] = questionsData["answer"] as Int
    }

    val question = questions[questionNumber]
    val answer = answers[questionNumber]

    numbers.remove(answer)

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color.Green
        ) {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuestionText(question = question)
            Spacer(modifier = Modifier.padding(25.dp))
            Row {
                ButtonText(number = numbers.random(), answers[questionNumber])
                Spacer(modifier = Modifier.padding(30.dp))
                ButtonText(number = answers[questionNumber], answers[questionNumber])
            }
            Spacer(modifier = Modifier.padding(80.dp))

            Row {
                PreviousButton()
                Spacer(modifier = Modifier.padding(50.dp))
                NextButton()

            }



        }
}



@Composable
fun Question(){




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

fun ButtonText(number: Int, answer : Int){
    Button(
        onClick = {
            if(number == answer){
                println("Your answer is correct!")
            }
            else{
                println("Your answer is wrong")
            }
        },

        modifier = Modifier.size(150.dp),
        shape = RoundedCornerShape(16.dp),

        ) {
        Text(text = number.toString(), fontSize = 50 .sp
        )
    }
}

@Composable

fun PreviousButton(){
    Button(onClick = {

    },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4fa3e3)
        ),

    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Prev",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}


@Composable

fun NextButton(){
    Button(onClick = {

    },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4fa3e3)
        )
    ) {
        Icon(imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
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

