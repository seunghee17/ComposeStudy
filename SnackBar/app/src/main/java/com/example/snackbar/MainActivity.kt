package com.example.snackbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbar.ui.theme.SnackBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MySnackbar()
                }
            }
        }
    }
}

@Composable
fun MySnackbar(){
    //스낵바를 관리하기 위함
    val snackbarHostState = remember{SnackbarHostState()}

    val coroutineScope = rememberCoroutineScope()
    val buttonTitle:(SnackbarData?) -> String = { snackbarData->
        if(snackbarData != null){
            "스낵바 숨기기"
        }else{
            "스낵바 보여주기"
        }
    }
    val buttonColor:(SnackbarData?) -> Color = { snackbarData->
        if(snackbarData != null){
            Color.Green
        }else{
            Color.Black
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor(snackbarHostState.currentSnackbarData),
                contentColor = Color.White
            ),
            onClick = {
                if(snackbarHostState.currentSnackbarData != null){
                    //스낵바 자동으로 닫는법
                    snackbarHostState.currentSnackbarData?.dismiss()
                    return@Button
                }
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    "이거 나오나요??",
                    "확인",
                    false,
                    SnackbarDuration.Short
                ).let {
                    when(it){
                        SnackbarResult.Dismissed -> {
                            Log.d("TAG", "스낵바 닫아짐")
                        }
                        SnackbarResult.ActionPerformed -> {
                            Log.d("TAG", "스낵바 버튼 클릭")
                        }
                    }
                }
            }
        }){
            //버튼내부 텍스트
            Text(buttonTitle(snackbarHostState.currentSnackbarData))
        }
        //스낵바가 보여지는 부분
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SnackBarTheme {

    }
}