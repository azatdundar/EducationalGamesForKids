package com.azatdundar.educationalgamesforkids

import androidx.compose.runtime.Composable
import kotlin.random.Random

fun GenerateQuestion() : Map<String, Any>{
    var num1 = Random.nextInt(0,10)
    var num2 = Random.nextInt(0,10)

    val signs = listOf('+', '-')
    val sign = signs.random()

    if((num1<num2) && sign == '-'){
        val temp = num1
        num1 = num2
        num2 = temp
    }

    val question = "$num1   $sign  $num2 "

    val answer = when(sign) {
        '+' -> num1 + num2
        '-' -> num1-num2
        else -> 0 // Which is technically impossible
    }
    return mapOf("question" to question, "answer" to answer)

}