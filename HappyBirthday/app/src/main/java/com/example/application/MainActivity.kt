package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    //GreetingText(message = "Happy Birthday Sam!", from = "From 뜽")
                    GreetingImage(stringResource(R.string.생일_축하), stringResource(R.string.발신자))
                }
            }
        }
    }
}

@Composable
//text의 기능을 정의하는 함수가 될것임
fun GreetingText(message:String,from:String,modifier: Modifier = Modifier){
    Column(verticalArrangement = Arrangement.Center,modifier = modifier.padding(8.dp)) {
        Text(
            text = message,
            fontSize =36.sp,  //----여기까지 작성하면 메시지가 겹쳐보임 행간을 지정해줘야함
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color = Color.Gray)
                .padding(8.dp)
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message:String, from: String, modifier:Modifier=Modifier){
    val image = painterResource(R.drawable.star)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(10.dp)
        )
        GreetingText(message = message, from = from, modifier = Modifier
            .fillMaxSize()
            .padding(8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    MyApplicationTheme {
        GreetingImage(message = "Happy Birthday Sam", from = "from 뜽")
    }
}