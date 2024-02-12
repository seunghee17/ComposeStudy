package com.example.textfield

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextFieldTest()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldTest(){
    var userInput by remember{ mutableStateOf(TextFieldValue()) }
    var phonenumberInput by remember{ mutableStateOf(TextFieldValue()) }
    var emailInput by remember{ mutableStateOf(TextFieldValue()) }
    //비밀번호 보이고 안보이고 처리
    val shouldShowPassword = remember{ mutableStateOf(false) }
    var passwordInput by remember{mutableStateOf(TextFieldValue())}

    val passwordResource: (Boolean) -> Int = {
        if(it == true){
            R.drawable.baseline_visibility_24
        }else{
            R.drawable.baseline_visibility_off_24
        }
    }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = true,
            onValueChange = {newValue -> userInput = newValue},
            label = {Text("사용자입력")},
            placeholder = {Text("작성해주세요")}
            )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phonenumberInput,
            singleLine = true,
            //키보드 옵션도 생성가능
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = {newValue -> phonenumberInput = newValue},
            label = {Text("사용자 입력")},
            placeholder = {Text("작성해주세요")}
        )
        //이메일 텍스트필드
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = null)},
            //trailingIcon = {Icon(imageVector = Icons.Default.Check, contentDescription = null)},
            trailingIcon = { IconButton(onClick = { Log.d("TAG", "체크버튼클릭") }) {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            }},
            //키보드 옵션도 생성가능
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {newValue -> emailInput = newValue},
            label = {Text("이메일주소")},
            placeholder = {Text("작성해주세요")}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = null)},
            //trailingIcon = {Icon(imageVector = Icons.Default.Check, contentDescription = null)},
            trailingIcon = { IconButton(onClick = {
                shouldShowPassword.value = !shouldShowPassword.value
                Log.d("TAG", "체크버튼클릭") }) {
                Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)), contentDescription = null)
            }},
            visualTransformation = if(shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            //키보드 옵션도 생성가능
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {newValue -> passwordInput = newValue},
            label = {Text("비밀번호")},
            placeholder = {Text("작성해주세요")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextFieldTheme {
        TextFieldTest()
    }
}