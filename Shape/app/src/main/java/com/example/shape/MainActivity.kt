package com.example.shape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.shape.ui.theme.ShapeTheme
import java.lang.Math.cos
import java.lang.Math.sin
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShapeContainer()
        }
    }
}

@Composable
fun ShapeContainer(){
    //특정 변수에 대한 상태를 가지고 있어야한다
    var polySides by remember { mutableStateOf(3) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //1번 원하는대로 도형 깎기
        //clip에 대해서 여기서 참조해 정리하기
        //DummyBox(modifier = Modifier.clip(RectangleShape))
        DummyBox(modifier = Modifier.clip(CircleShape))
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(CutCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(TriangleShape()))
        DummyBox(modifier = Modifier.clip(PolyShape(polySides,100f)))

        Text(text = "polysides: $polySides")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = {
                polySides += 1
            }){
                Text(text = "polySides + 1")
            }
            Button(onClick = {
                polySides = 3
            }){
                Text(text = "polySides = 3")
            }
        }
    }
}
//2번
class TriangleShape(): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply{
            //점을 이동한후에 선을 긋는다
            moveTo(size.width /2f,0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

class PolyShape(private val sides:Int, private val radius:Float):Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply{
            this.polygon(sides, radius, size.center)
        })
    }
}

fun Path.polygon(sides:Int, radius:Float, center: Offset){
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y = center.y + (radius * sin(0.0)).toFloat()
    )
    for(i in 1 until sides){
        lineTo(
            x = center.x + (radius * cos(angle * i)).toFloat(),
            y = center.y + (radius * sin(angle * i)).toFloat()
        )
    }
    close()
}

@Composable
fun DummyBox(modifier:Modifier=Modifier, color:Color?=null){
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = color?.let{ it } ?: Color(red,green,blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShapeTheme {
        ShapeContainer()
    }
}