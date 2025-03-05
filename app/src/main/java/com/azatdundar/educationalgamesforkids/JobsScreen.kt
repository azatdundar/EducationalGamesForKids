package com.azatdundar.educationalgamesforkids

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.azatdundar.educationalgamesforkids.ui.theme.EducationalGamesForKidsTheme
import kotlin.random.Random



class JobQuestion(val imageIndex1 : Int, val imageIndex2 : Int, val answer : String, val imageName1 : String, val  imageName2 : String)
@Composable
fun JobsScreen(navController: NavController) {
    var questionNumber by remember{ mutableIntStateOf(0) }
    var isClicked by remember { mutableStateOf(false)}
    var jobImages = mutableListOf(R.drawable.architect, R.drawable.doctor_image, R.drawable.engineer_image, R.drawable.teacher)
    var jobs = mutableListOf("Architect", "Doctor", "Engineer", "Teacher")
    var indexes = MutableList(jobImages.size){it}
    var questions = MutableList(6){JobQuestion(0,0, "","","")}
    var answers = MutableList(6){""}
    var jobNames1 = MutableList(6){""}
    var jobNames2 = MutableList(6){""}
    var score by remember { mutableIntStateOf(0) }

    var isFinished by remember{ mutableStateOf(false) }



    for (i in 0..5){
        var randomIndex1 = indexes.random()
        indexes.remove(randomIndex1)
        var randomIndex2 = indexes.random()
        indexes.add(randomIndex1)
        jobNames1[i] = remember{jobs[randomIndex1]}
        jobNames2[i] =remember{jobs[randomIndex2]}
        val answer = if(Random.nextBoolean()) jobs[randomIndex1] else jobs[randomIndex2]
        questions[i] = remember{JobQuestion(jobImages[randomIndex1],jobImages[randomIndex2], answer = answer, imageName1 = jobNames1[i], imageName2 = jobNames2[i])}

    }





    var remainingTime by remember { mutableStateOf(60) }

    val timer = remember{
        object : CountDownTimer(60000,1000){
            override fun onTick(p0: Long) {
                if(p0/1000>0){
                    remainingTime--
                }
            }

            override fun onFinish() {
                isFinished = true
            }

        }
    }
    timer.start()

    if(isFinished){
        ResultScreen(score = score, navController =navController )
    }

    else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(80.dp))
            Text(text = remainingTime.toString(), fontSize = 50.sp)
            Spacer(modifier = Modifier.size(100.dp))
            Text(text = "Choose the appropriate image for the relevant job")
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = questions[questionNumber].answer, fontSize = 30.sp)
            Spacer(modifier = Modifier.size(50.dp))
            Row() {


                Job(
                    imageRes = questions[questionNumber].imageIndex1,
                    name = jobNames1[questionNumber],
                    jobModifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
                {


                    if (jobNames1[questionNumber] == questions[questionNumber].answer) {
                        score++
                    }

                    if (questionNumber < questions.size-1) {
                        questionNumber++
                    } else {
                        isFinished = true
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))

                Job(
                    imageRes = questions[questionNumber].imageIndex2,
                    name = jobNames2[questionNumber],
                    jobModifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
                {
                    if (jobNames2[questionNumber] == questions[questionNumber].answer) {

                        score++
                    }
                    if (questionNumber < questions.size-1) {
                        questionNumber++
                    } else {
                        isFinished = true
                    }
                }


            }
        }
    }

}



@Composable
fun ResultScreen(score : Int, navController: NavController){

    Column(
        Modifier
            .background(Color(0xFF6200EE))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.award), contentDescription = "Konfetti",
            modifier = Modifier.size(300.dp))
        Text(text = "Congratulations !", fontSize = 50.sp)
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = "You have $score point.", fontSize = 50.sp)
        Spacer(modifier = Modifier.size(30.dp))

        Row {
            Button(onClick = {
                navController.navigate("MainScreen")
            },
                modifier = Modifier.size(100.dp)
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                navController.navigate("JobsScreen")
            },
                modifier = Modifier.size(100.dp)
            ) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Restar")
            }
        }
    }

}

@Composable
fun Job(imageRes : Int,name : String,jobModifier : Modifier, onClick : ()->Unit){
    val image : Painter = painterResource(id = imageRes)
    Image(painter = image, contentDescription = name, modifier = jobModifier.clickable(onClick = onClick) )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    EducationalGamesForKidsTheme {
        val navController = rememberNavController()
        ResultScreen(3,navController = navController)
    }
}