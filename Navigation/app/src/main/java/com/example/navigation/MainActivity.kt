package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //navcontroller을 얻어야한다 가장 핵심적인 요소 1번
            val navController = rememberNavController()

            //2번 Navhost정의
            NavHost(navController = navController, startDestination = "first"){
                composable("first"){
                    FirstScreen(navController)
                }
                composable("second"){
                    SecondScreen(navController)
                }
                //backStackEntry를 통해 value로 넘어올 값을 전달받을 수 있다?
                composable("third/{value}"){backStackEntry->
                    ThirdScreen(navController,backStackEntry.arguments?.getString("value") ?: "")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    val (value, setValue) = remember{
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "첫화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("second")
        }){
            Text(text = "두번째")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value , onValueChange = setValue)
        Button(onClick = {
            //작성한 text가 세번째 화면에서도 뜨게 하기
            if(value.isNotEmpty()){
                navController.navigate("third/$value")
            }
        }){
            Text(text = "세번째")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "두번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            //뒤로가기를 구현하기 위해
            //둘다 같은 동작 차이점을 찾아보기
            navController.navigateUp()
            //navController.popBackStack()
        }){
            Text(text = "뒤로가기")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController,value: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "세번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = value)
        Button(onClick = {
            navController.navigateUp()
        }){
            Text(text = "뒤로가기!")
        }
    }
}

