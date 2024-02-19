package com.example.wellness

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun WaterCounter(modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)){
        //val count : MutableState<Int> = remember { mutableStateOf(0) }
        var count by remember{ mutableStateOf(0) }
        if(count > 0){
            var showTask by remember { mutableStateOf(true) }
            if(showTask){
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = { showTask = false })
            }
            Text("You've had $count glasses")
        }
        Row(Modifier.padding(top = 8.dp)){
            Button(onClick = {count++}, enabled = count <10){
                Text("Add one")
            }
            Button(
                onClick = {count = 0},
                Modifier.padding(start = 8.dp)
            ){
                Text("clear water count")
            }
        }

    }
}

@Composable
fun StatelessCounter(count:Int, onIncrement: () -> Unit, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)){
        if(count > 0){
            Text("You've had $count glasses")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count<10){
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier){
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    StatelessCounter(count = count, onIncrement = { count++ }, modifier)
}

