package com.malferma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malferma.ui.mainView.MainViewModel
import com.malferma.ui.theme.IpSubnettingTheme
import java.lang.NullPointerException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            IpSubnettingTheme {
                Text("hello ble")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IpSubnettingTheme {
        Body()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Body(){
    Column(
        modifier = Modifier.fillMaxSize(),
    ){
        LabelMain()
        TextFieldMain()
        TextFieldMainEx()
        LabelSetResult()
        LabelSetResult()
        LabelSetResult()

    }
}

@Composable
fun LabelMain(imputedIP : String = "182.168.0.1/28"){
    Text(
        text = "IP: $imputedIP",
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun LabelSetResult(result : String = "182.168.0.1/28"){
    Text(
        text = "IP: $result",
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

/**
 * Create main textField
 * @param inputForm set hintText and label => 'ip' for ip form, 'subNet' for subnet form,
 * 'cdr' for cdr form
 * **/
@Composable
fun TextFieldMain(inputForm : String = "ip"){
    var text by remember { mutableStateOf("") }
    val hintTxt = "192.168.0.1/24"
    val labelTxt = "IP address"

    OutlinedTextField(
        placeholder = { Text(hintTxt) },
        value = text,
        onValueChange = { text = it },
        label = {Text(labelTxt)},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth(),
    )
}

@Composable
fun TextFieldMainEx(){
    var text by remember { mutableStateOf("") }
    val hintText = "192.168.0.1/24"
    var labelText = ""

    OutlinedTextField(
        placeholder = { Text("Enter Email") },
        value = text,
        onValueChange = { text = it },
        label = { Text("texts") },
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth(),
    )
}

@Composable
fun OutlinedButtonExample() {
    OutlinedButton(onClick = { /* Do something! */ }) {
        Text("I'm an Outlined Button")
    }
}
