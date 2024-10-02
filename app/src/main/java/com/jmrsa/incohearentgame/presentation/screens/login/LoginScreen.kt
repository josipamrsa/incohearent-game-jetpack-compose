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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.jmrsa.incohearentgame.R
import com.jmrsa.incohearentgame.base.composeViewModel
import com.jmrsa.incohearentgame.base.utils.collectInLaunchedEffect
import com.jmrsa.incohearentgame.navigation.Destination
import com.jmrsa.incohearentgame.navigation.typeComposable
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination: Destination

fun NavGraphBuilder.loginScreen(
    onNavigateToLobby: () -> Unit
) {
    typeComposable<LoginDestination> {
        val viewModel = composeViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val onEvent = viewModel::onEvent

        viewModel.effect.collectInLaunchedEffect { effect ->
            when (effect) {
                is LoginContract.Effect.ContinueLogin -> onNavigateToLobby()
            }
        }

        LoginScreen(
            state = state,
            event = onEvent
        )
    }
}

@Composable
fun LoginScreen(
    state: LoginContract.State,
    event: (LoginContract.Event) -> Unit
) {

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
            value = state.username,
            onValueChange = { event(LoginContract.Event.OnUsernameChanged(it)) },
            label = {
                Text(stringResource(id = R.string.inc_login_username))
            },
            supportingText = {
                Text(text = stringResource(id = R.string.inc_login_username_footnote))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { event(LoginContract.Event.OnContinueLogin) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.canLogin
        ) {
            Text(text = stringResource(id = R.string.inc_login_button))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        state = LoginContract.State(),
        event = {}
    )
}