package com.jmrsa.incohearentgame.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.jmrsa.incohearentgame.R
import com.jmrsa.incohearentgame.core.base.composeViewModel
import com.jmrsa.incohearentgame.core.base.utils.collectInLaunchedEffect
import com.jmrsa.incohearentgame.core.navigation.Destination
import com.jmrsa.incohearentgame.core.navigation.typeComposable
import com.jmrsa.incohearentgame.ui.theme.IncButtonFillColor
import com.jmrsa.incohearentgame.ui.theme.IncInputFillColor
import com.jmrsa.incohearentgame.ui.theme.IncInputFillText
import com.jmrsa.incohearentgame.ui.theme.IncohearentGameTheme
import com.jmrsa.incohearentgame.ui.theme.SpaceGroteskFontFamily
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination : Destination

fun NavGraphBuilder.loginScreen(
    onNavigateToLobby: (username: String) -> Unit
) {
    typeComposable<LoginDestination> {
        val viewModel = composeViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val onEvent = viewModel::onEvent

        viewModel.effect.collectInLaunchedEffect { effect ->
            when (effect) {
                is LoginContract.Effect.ContinueLogin -> onNavigateToLobby(state.username)
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

    Image(
        painter = painterResource(R.drawable.inc_header_strip),
        contentDescription = ""
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
    ) {
        Text(
            fontFamily = SpaceGroteskFontFamily,
            text = stringResource(id = R.string.inc_header_main)
                .uppercase(),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.W700
        )

        Text(
            fontFamily = SpaceGroteskFontFamily,
            text = stringResource(id = R.string.inc_description_main),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 15.sp
        )

        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            value = state.username,
            onValueChange = { event(LoginContract.Event.OnUsernameChanged(it)) },
            placeholder = {
                Text(
                    stringResource(id = R.string.inc_login_username),
                    style = TextStyle(
                        color = IncInputFillText,
                        fontFamily = SpaceGroteskFontFamily,
                        fontWeight = FontWeight.W700,
                        fontSize = 15.sp
                    )
                )
            },
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.inc_login_username_footnote),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = SpaceGroteskFontFamily,
                        textAlign = TextAlign.Right,
                        color = Color.Black
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors().copy(
                // TODO replace colors
                unfocusedIndicatorColor = IncInputFillColor,
                unfocusedContainerColor = IncInputFillColor,
                focusedIndicatorColor = IncInputFillColor
            ),
            textStyle = TextStyle(
                color = IncInputFillText,
                fontFamily = SpaceGroteskFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                textDecoration = TextDecoration.None
            ),
            maxLines = 1,
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 30.dp,
                bottomStart = 25.dp,
                bottomEnd = 15.dp
            )
        )

        Spacer(Modifier.height(2.dp))

        Button(
            onClick = { event(LoginContract.Event.OnContinueLogin) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = IncButtonFillColor
            ),
            shape = RoundedCornerShape(15.dp),
            contentPadding = PaddingValues(15.dp),
            enabled = state.canLogin
        ) {
            Text(
                text = stringResource(id = R.string.inc_login_button),
                style = TextStyle(
                    fontFamily = SpaceGroteskFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    IncohearentGameTheme {
        LoginScreen(
            state = LoginContract.State(),
            event = {}
        )
    }
}