package com.example.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertDialogScreen(showDialog: MutableState<Boolean>){
    Column {
        //val showDialog = remember{ mutableStateOf(false) }
        if(showDialog.value){
            AlertDialog(
                onDismissRequest ={},
                title = {Text(text = "Title", color = Color.Red)},
                text = {Text(text = "content")},
                dismissButton = {
                    Button(onClick = {showDialog.value = false}){
                        Text("cancel")
                    }
                },
                confirmButton = {
                    Button(onClick = {showDialog.value = false}){
                        Text("ok")
                    }
                },
                containerColor = Color.DarkGray,
                shape = RoundedCornerShape(20.dp),
                titleContentColor = Color.Red,
                textContentColor = Color.Yellow,
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            )
        }
    }
}