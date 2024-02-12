package com.example.checkbox

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkbox.ui.theme.CheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckBoxNewContainer()
                }
            }
        }
    }
}
//colors -> 체크박스에 대한 색깔
@Composable
fun CheckBoxContainer(){
    //checkbox 상태 확인하기 위해서는 MutableState로 가지고 있어야함
    val checkedStatusForFirst = remember{ mutableStateOf(false) }
    //두번째 체크박스
    var checkedStatusForSecond by remember { mutableStateOf(false) }

    //세번째 체크박스
    var (checkedStatusForThird, setcheckedStatusForThird) = remember { mutableStateOf(false) }

    //네번째
    var (checkedStatusForFour,setcheckedStatusForFour) = remember{ mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //checked는 말 그대로 체크된 상태
//        Checkbox(
//            checked = checkedStatusForFirst.value,
//            onCheckedChange = {isChecked ->
//            Log.d("TAG", "$isChecked")
//                checkedStatusForFirst.value = isChecked
//        })
        CheckBoxWithTitle("첫번째 체크박스", checkedStatusForFirst)
        Checkbox(
            //두번째 방식일때는 이렇게 접근하는 것임
            checked = checkedStatusForSecond,
            enabled = true,
            onCheckedChange = {isChecked ->
            Log.d("TAG", "$isChecked")
                checkedStatusForSecond = isChecked
        })
        Checkbox(checked = checkedStatusForThird,
            onCheckedChange = {isChecked ->
            Log.d("TAG", "$isChecked")
                setcheckedStatusForThird.invoke(isChecked)
        })
        Spacer(modifier = Modifier.height(30.dp))

        Checkbox(checked = checkedStatusForFour,
            colors = CheckboxDefaults.colors(
                //선택된 상태일때 체크박스 배경색
                checkedColor = Color.Red,
                //선택되지 않았을때의 체크박스 테두리색
                uncheckedColor = Color.Yellow,
                //체크했을때의 체크 마크 컬러
                checkmarkColor = Color.Black,
                //enable가 false일때의 컬러
                //disabledColor
            ),
            onCheckedChange = {isChecked ->
                Log.d("TAG", "$isChecked")
                setcheckedStatusForFour.invoke(isChecked)
            })
    }
}

@Composable
fun CheckBoxNewContainer(){
    val checkedStatusForFirst = remember{ mutableStateOf(false) }
    val checkedStatusForSecond = remember{ mutableStateOf(false) }
    val checkedStatusForThird = remember{ mutableStateOf(false) }
    //val checkedStatusForFour = remember{ mutableStateOf(false) }

    val checkedStateArray = listOf(
        checkedStatusForFirst,
        checkedStatusForSecond,
        checkedStatusForThird)

    val allBoxChecked:(Boolean) -> Unit={isAllBoxChecked->
        Log.d("TAG","checkBoxContainer: isAllBoxChecked: $isAllBoxChecked")
        //checkedStateArray.forEach{it.value = isAllBoxChecked }
        //하나라도 체크가 안되어있으면 체크 해제
        checkedStateArray.forEach{it.value = isAllBoxChecked}
    }
    //모든 체크박스에 표시되어있을때 마지막 모두동의 체크박스도 true상태 만들어주는 것
    val checkedStatusForFour : Boolean = checkedStateArray.all{ it.value }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CheckBoxWithTitle("1번 확인사항",checkedStatusForFirst)
        CheckBoxWithTitle("2번 확인사항",checkedStatusForSecond)
        CheckBoxWithTitle("3번 확인사항",checkedStatusForThird)
        CheckBoxAll("모두 동의",checkedStatusForFour, allBoxChecked)
        CustomCheckBox("이건 커스텀", withRipple = true)
        CustomCheckBox("이건 커스텀", withRipple = false)
    }
}

@Composable
fun CheckBoxWithTitle(title: String, isCheckedState: MutableState<Boolean>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            enabled = true,
            checked = isCheckedState.value,
            onCheckedChange = {isChecked->
                Log.d("TAG", "$isChecked")
                isCheckedState.value = isChecked
            }
        )
        Text(text = title, textAlign = TextAlign.Center)
    }
}

//모두 동의하기
@Composable
fun CheckBoxAll(title:String, isCheckedState: Boolean, allBoxChecked:(Boolean)-> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            enabled = true,
            checked = isCheckedState,
            onCheckedChange = {isChecked->
                Log.d("TAG", "$isChecked")
                allBoxChecked(isChecked)
            }
        )
        Text(text = title, textAlign = TextAlign.Center)
    }
}
//커스텀 체크박스를 만들기 위한
@Composable
fun CustomCheckBox(title:String, withRipple: Boolean = false){
    //var isCheckedState by remember { mutableStateOf(false) }
    var (isChecked, setIsChecked) = remember { mutableStateOf(false) }
    var togglePainter = if (isChecked == true) R.drawable.check_box_enabled else R.drawable.check_box_disabled
    var checkedInfoString = if(isChecked == true) "체크됨" else "체크안됨"

    var rippleEffect = if(withRipple) rememberRipple(radius = 20.dp) else null

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            //clickable에서는 기본적으로 ripple효과가 내장되어있다. 해제 필수!!
            //해제하고 싶으면 indication = null로 지정하기
            modifier = Modifier.clickable(
                indication = rippleEffect,
                interactionSource = remember { MutableInteractionSource() }
            ) {
              setIsChecked.invoke(!isChecked)
            },
            painter = painterResource(id = togglePainter),
            contentDescription = null
        )
        Text(text = checkedInfoString, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckBoxTheme {
        CheckBoxNewContainer()
    }
}