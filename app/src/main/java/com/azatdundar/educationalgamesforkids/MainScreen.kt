package com.azatdundar.educationalgamesforkids

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

private val gaamliFontFamily = FontFamily(
Font(R.font.gaamli)
)

@Composable

fun MainScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            InformationText()
            Spacer(modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth()
                .weight(1f),

                horizontalArrangement = Arrangement.Center) {
                CategoryImage(imageRes = R.drawable.math, categoryName = "Math") {
                    navController.navigate("MathScreen")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .scale(0.7f)
               ,
                horizontalArrangement = Arrangement.Center
            ) {
                CategoryImage(imageRes = R.drawable.alphabet, categoryName = "Alfabe") {
                    navController.navigate("AlphabetScreen")
                }


            }

            Row(modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .scale(0.75f),
                horizontalArrangement = Arrangement.Center
            ) {
                val localContext = LocalContext.current
                CategoryImage(imageRes = R.drawable.jobs, categoryName = "Jobs") {

                    val intent = Intent(localContext, JobsActivity::class.java)
                    localContext.startActivity(intent)
                }
            }



        }

    }


}

@Composable
 fun InformationText() {
    Text(
        text = "This application is designed for kids to help them learn english, basit math etc.",
        color = Color.Blue,
        style = TextStyle(
            fontFamily = gaamliFontFamily,
            fontSize = 22.sp
        )
    )
}

@Composable
fun CategoryImage(imageRes : Int, categoryName : String, onClick: ()->Unit){
    val image : Painter = painterResource(id = imageRes)

    Image(painter = image,
        contentDescription = categoryName,
        modifier = Modifier.clickable(onClick = onClick))
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    // MainScreen fonksiyonunun önizlemesini burada gösteriyoruz
    MainScreen(navController = NavHostController(LocalContext.current))
}