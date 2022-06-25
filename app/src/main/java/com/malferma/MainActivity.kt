package com.malferma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.malferma.ui.mainView.MainViewModel
import com.malferma.ui.navigation.Screen
import com.malferma.ui.theme.IpSubnettingTheme
import com.malferma.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        val currentScreen= mutableStateOf<Screen>(Screen.Subnetting)

        setContent {
            MainScreen()
            /*IpSubnettingTheme {
                Text("hello ble")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) {
                                currentScreen.value=it
                            }
                        }){

                    }
                    Default()
                }
            }*/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IpSubnettingTheme {
        //Body(viewModel = MainViewModel())
    }
}

/*
@Composable
fun Default() {
    IpSubnettingTheme {
        Body(viewModel = MainViewModel())
    }
}

@Composable
fun ButtonTakeMainInput(viewModel: MainViewModel) {
    OutlinedButton(onClick = {
        viewModel.GetIpAtributs()

    }) {
        Text("Check")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Body(viewModel: MainViewModel) = with(viewModel.obj.value){
    Column(
        modifier = Modifier
            .fillMaxSize()
            ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LabelMain()
        TextFieldIp(viewModel)
        //ButtonTakeMainInput(viewModel)
        LabelSetResult(NetworkAddress, "Network Address")
        LabelSetResult(FirstHostAddress, "First Host Address")
        LabelSetResult(LastHostAddress, "Last Host Address")
        LabelSetResult(BroadcastAddress, "Broadcast Address")
        LabelSetResult("$NrOfHosts", "Nr. of Hosts")
        LabelSetResult("$NrOfFreeHosts", "Nr. of free Hosts to use")
        LabelSetResult(RangeOfNetwork, "Range of the Network")

       // LabelSetResult(BroadcastAddress)

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
fun LabelSetResult(output: String, labelName: String = "None"){
    Text(
        text = output,
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = labelName,
        style = TextStyle(
            color = Color.Gray,
        ),
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth()
            .padding(end = 10.dp)
            ,
        textAlign = TextAlign.Right,
        fontSize = 11.sp,

    )
}

/**
 * Create main textField
 * @param inputForm set hintText and label => 'ip' for ip form, 'subNet' for subnet form,
 * 'cdr' for cdr form
 * **/
@Composable
fun TextFieldIp(inputForm : MainViewModel, hintTxt: String = "192.168.0.1/24", labelTxt: String = "IP address"){
    val focusManager = LocalFocusManager.current

    var ip by remember { mutableStateOf("") }
    var netmask by remember { mutableStateOf("") }

    OutlinedTextField(
        placeholder = { Text(hintTxt) },
        value = ip,
        keyboardActions = KeyboardActions(onDone = {
            if(netmask == "")
                focusManager.moveFocus(FocusDirection.Down)
            else
                focusManager.clearFocus()
         }),
        onValueChange = {
            if (it.length <= 18){
                ip = it.replace("\\s".toRegex(), "")
                       .replace("[^0-9./]".toRegex(), "")
               // ip = it.replace("[^0-9./]".toRegex(), "")
                netmask = inputForm.SetIp(ip, netmask)
                inputForm.SetIpByNetmaskOrCidr(ip, netmask)
            }
        },
        label = {Text(labelTxt)},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth(),
        singleLine = true,
    )

    OutlinedTextField(
        placeholder = { Text("Netmask or CIDR") },
        value = netmask,
        keyboardActions = KeyboardActions(onDone = {
            if(ip == "")
                focusManager.moveFocus(FocusDirection.Up)
            else
                focusManager.clearFocus()
        }),
        onValueChange = {
            if (it.length <= 15) {
                netmask = it.replace("\\s".toRegex(), "")
                            .replace("[^0-9./]".toRegex(), "")
                ip = inputForm.SetIpByNetmaskOrCidr(ip, netmask)
            }
        },
        label = { Text("texts") },
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth(),
        singleLine = true,
    )
}*/