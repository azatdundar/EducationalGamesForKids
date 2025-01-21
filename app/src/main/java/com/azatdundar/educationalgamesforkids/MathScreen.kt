package com.azatdundar.educationalgamesforkids

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager.ComponentEnabledSetting
import android.widget.Space
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.requestProfiling
import androidx.navigation.navOptions
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit
import kotlin.random.Random


@Composable
fun MathScreen(navController: NavHostController) {
    questionArea()


}

@Composable
fun questionArea(){




    val numbers = (0..20).toMutableList()
    var questionNumber  by remember { mutableIntStateOf(0) }
    var isClicked by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    val builder = AlertDialog.Builder(LocalContext.current)

    val questions = Array(30) {""}
    val answers = Array(30){0}
    val options1 = Array(30){0}
    val options2 = Array(30){0}


    for(i in 0..29){
        val questionsData = GenerateQuestion()
        questions[i] = remember { questionsData["question"] as String }
        answers[i] = remember { questionsData["answer"] as Int }
        numbers.remove(answers[i])
        val possible_answers = mutableListOf(numbers.random(), answers[i])
        val randomIndex = Random.nextInt(possible_answers.size)
        options1[i] = remember { possible_answers[randomIndex] }
        possible_answers.remove(options1[i])
        options2[i] = remember { possible_answers[0] }
        numbers.add(answers[i])
    }


    val question = questions[questionNumber]
    val answer = answers[questionNumber]
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color(red = 0, green = 35, blue = 0)
    ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuestionText(question = question)
            Row {
                Spacer(modifier = Modifier.padding(10.dp))
                ButtonText(number = options1[questionNumber], answer = answers[questionNumber],isSelected = isClicked) {
                    isClicked = true
                    if (options1[questionNumber] == answers[questionNumber]){
                        isAnswerCorrect = true
                        score++
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))

                ButtonText(number = options2[questionNumber], answer = answers[questionNumber], isSelected = isClicked) {
                    isClicked = true
                    if(options2[questionNumber] == answers[questionNumber]){
                        isAnswerCorrect = true
                        score++
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))

            }
            Spacer(modifier = Modifier.padding(20.dp))
            Row {
                PreviousButton(questionNumber) {
                    questionNumber--
                    isClicked = false
                }
                Spacer(modifier = Modifier.padding(20.dp))
                NextButton(questionNumber, questions.size) {
                    questionNumber++
                    isClicked = false
                }
            }
                Spacer(modifier = Modifier.size(40.dp))
                finishQuizButton {
                    builder.setTitle("Finish the quiz")
                        .setTitle("Are you sure to finish the game")
                        .setPositiveButton("Yes"){dialog, which ->
                            //
                        }
                        .setNegativeButton("Cancel"){dialog, which ->

                        }

            }
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

fun ButtonText(number: Int, answer : Int, isSelected : Boolean, onClick: () -> Unit){
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.size(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            when{
                isSelected && (answer==number) -> Color.Green
                isSelected &&(answer!=number) -> Color.Red
                else -> Color.Blue
            }
        )

        ) {
        Text(text = number.toString(), fontSize = 50 .sp)

    }
}

@Composable

fun PreviousButton(questionNum : Int,onClick: () -> Unit){
    Button(onClick = {
                     onClick()
    },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4fa3e3)
        ),
        enabled = when{
            questionNum == 0 -> false
            else -> true
        }

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

fun NextButton(questionNum : Int,numOfTotalQuestions : Int,onClick : ()->Unit){
    Button(onClick = {
                     onClick()
    },
        shape = CircleShape,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4fa3e3)
        ),
        enabled = questionNum < numOfTotalQuestions-1
    ) {
        Icon(imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
            )
    }

}

@Composable
fun finishQuizButton(onClick: () -> Unit){
    Button(onClick = {  onClick()
    },
        modifier = Modifier.size(200.dp, 50.dp),
        colors = ButtonDefaults.buttonColors(Color.Magenta)
    ) {
        Text(text = "Finish Quiz")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMathScreen() {

    val navController = rememberNavController()
    MathScreen(navController = navController)
}
