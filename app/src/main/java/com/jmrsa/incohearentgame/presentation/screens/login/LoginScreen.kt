package com.jmrsa.incohearentgame.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Text(
            text = "Incohearent is the adult party game " +
                "that will get you talking! Let the laughs " +
                "begin as you compete to make sense out of " +
                "gibberish from one of three categories — kinky, " +
                "party and pop culture.",
            textAlign = TextAlign.Center,
            color = Color.White
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text("Pick any name, but don't go overboard")
            },
            placeholder = {
                Text(text = "Type it in here")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get in there")
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}