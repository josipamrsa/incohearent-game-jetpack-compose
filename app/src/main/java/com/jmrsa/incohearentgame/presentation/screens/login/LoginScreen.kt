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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmrsa.incohearentgame.R

@Composable
fun LoginScreen() {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

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
            text = stringResource(id = R.string.inc_description_main),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        OutlinedTextField(
            value = state.value.username,
            onValueChange = { onEvent(LoginContract.Event.OnUsernameChanged(it)) },
            label = {
                Text(stringResource(id = R.string.inc_login_username))
            },
            supportingText = {
                Text(text = stringResource(id = R.string.inc_login_username_footnote))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.value.canLogin
        ) {
            Text(text = stringResource(id = R.string.inc_login_button))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}