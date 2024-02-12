package com.example.button

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonsContainer()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonsContainer(){
    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))
    //interactionSource에 대해서
    //remember 로 정의된 값이 변경되면 뷰를 다시 그린다
    val interactionSource = remember{ MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressStatusTitle = if(isPressed) "버튼을 누르고 있다" else "버튼에서 손을 뗐다"

    val interactionSourceForSecond = remember{ MutableInteractionSource() }
    val isPressedForSecond by interactionSourceForSecond.collectIsPressedAsState()

    val pressedBtnRadius = if (isPressedForSecond) 0.dp else 20.dp

    //버튼누를때 그림자 반경 변경되는 것을 애니메이션 처리
    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(
        targetValue = if (isPressedForSecond) 0.dp else 10.dp
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                //눌렀을때 그림자가 사라지는 효과
                pressedElevation = 0.dp,
                //버튼이 disabled되었을때 어떻게 처리할것인가 enable이 false일때
                disabledElevation = 0.dp
            ),
            onClick = {
            Log.d("TAG", " 버튼 1 클릭")
        }){
            //버튼에 대한 세부사항 지정하기
            Text(text = "버튼 1")
        }
        Button(
            enabled = true,
            shape = RectangleShape,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                //눌렀을때 그림자가 사라지는 효과
                pressedElevation = 0.dp,
                //버튼이 disabled되었을때 어떻게 처리할것인가 enable이 false일때
                disabledElevation = 0.dp
            ),
            onClick = {
                Log.d("TAG", " 버튼 2 클릭")
            }){
            //버튼에 대한 세부사항 지정하기
            Text(text = "버튼 2")
        }
        Button(
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 4.dp, color = Color.DarkGray),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 5.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                //눌렀을때 그림자가 사라지는 효과
                pressedElevation = 0.dp,
                //버튼이 disabled되었을때 어떻게 처리할것인가 enable이 false일때
                disabledElevation = 0.dp
            ),
            onClick = {
                Log.d("TAG", " 버튼 3 클릭")
            }){
            //버튼에 대한 세부사항 지정하기
            Text(text = "버튼 3")
        }
        Button(
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            //stroke에 그라데이션 효과도 줄 수 있음
            border = BorderStroke(width = 4.dp, brush = buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                //버튼의 배경색
              containerColor = Color.Black,
                contentColor = Color.LightGray
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                //눌렀을때 그림자가 사라지는 효과
                pressedElevation = 0.dp,
                //버튼이 disabled되었을때 어떻게 처리할것인가 enable이 false일때
                disabledElevation = 0.dp
            ),
            //여기에 interactionSource넣어주기
            interactionSource = interactionSource,
            onClick = {
                Log.d("TAG", " 버튼 4 클릭")
            }){
            //버튼에 대한 세부사항 지정하기
            Text(text = "버튼 4")
        }
        Text(text = "$pressStatusTitle")

        //커스텀 그림자 만들기 실습
        Button(
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            //stroke에 그라데이션 효과도 줄 수 있음
            border = BorderStroke(width = 4.dp, brush = buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                //버튼의 배경색
                containerColor = Color.Black,
                contentColor = Color.LightGray
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                //눌렀을때 그림자가 사라지는 효과
                pressedElevation = 0.dp,
                //버튼이 disabled되었을때 어떻게 처리할것인가 enable이 false일때
                disabledElevation = 0.dp
            ),
            //여기에 interactionSource넣어주기
            interactionSource = interactionSourceForSecond,
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                //투명도
                alpha = 0.5f,
                borderRadius = 0.dp,
                //퍼짐 정도
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetY = 0.dp,
                offsetX = 0.dp
            ),
            onClick = {
                Log.d("TAG", " 버튼 5 클릭")
            }){
            //버튼에 대한 세부사항 지정하기
            Text(text = "버튼 5")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonTheme {
        ButtonsContainer()
    }
}