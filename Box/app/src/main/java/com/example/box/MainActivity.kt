package com.example.box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.box.ui.theme.BoxTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //BoxContainer()
                    BoxWithConstraintContainer()
                }
            }
        }
    }
}

@Composable
fun BoxContainer(){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        //가장 작은애를 full로 채워준다
        propagateMinConstraints = true
    ){
        //색상변경
        DummyBox(modifier = Modifier.size(200.dp), color = Color.Gray)
        DummyBox(modifier = Modifier.size(150.dp),color = Color.Yellow)
        DummyBox(color = Color.Blue)
    }
}

@Composable
fun BoxWithConstraintContainer(){
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        //propagateMinConstraints = true
    ){
        //화면 크기에 대한 대응
        if(this.minHeight > 400.dp){
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        }else{
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
        }

//        Column() {
//            BoxWithConstraintItem(modifier = Modifier.size(200.dp).background(Color.Yellow))
//            BoxWithConstraintItem(modifier = Modifier.size(300.dp).background(Color.Green))
//        }

//        DummyBox(modifier = Modifier.size(200.dp), color = Color.Gray)
//        DummyBox(modifier = Modifier.size(150.dp),color = Color.Yellow)
//        DummyBox(color = Color.Blue)
    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier){
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true
    ){
        if(this.minWidth > 200.dp){
            Text(text = "이것은 큰 상자이다")
        }else{
            Text(text = "이것은 작은 상자이다")
        }
    }
}

@Composable
fun DummyBox(modifier:Modifier = Modifier, color:Color? = null){
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = color?.let{it} ?: Color(red,green,blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor)
    )
}