package com.malferma.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malferma.ui.mainView.MainViewModel
import com.malferma.ui.theme.IpSubnettingTheme

@Composable
fun HomeScreen() {
    Default()
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}


@Composable
fun Default() {
    IpSubnettingTheme {
        Body(viewModel = MainViewModel())
    }
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
}