package com.example.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.presentation.main.MainActivity
import com.example.presentation.component.FCButton
import com.example.presentation.component.FCTextField
import com.example.presentation.theme.SNSTheme
import com.example.presentation.theme.Typography
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * 로직을 위한 컴포저블
 * 뷰모델은 프리뷰에 들어갈수 없나봄
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateSignUpScreen: () -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { effect ->
        when (effect) {
            is LoginSideEffect.Toast -> {
                Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                context.startActivity(
                    Intent(
                        context, MainActivity::class.java
                    ).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
        }

    }
    LoginScreen(
        id = state.id,
        pwd = state.pwd,
        onIdChange = viewModel::onIdChange,
        onPwdChange = viewModel::onPwdChange,
        onNavigateSignUpScreen = onNavigateSignUpScreen,
        onLoginClick = viewModel::onLoginClick
    )
}

@Composable
private fun LoginScreen(
    id: String,
    pwd: String,
    onIdChange: (String) -> Unit,
    onPwdChange: (String) -> Unit,
    onNavigateSignUpScreen: () -> Unit,
    onLoginClick: () -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Connected",
                    style = Typography.displaySmall
                )

                Text(
                    "키미가 스키나 소샤루 네트와쿠",
                    style = Typography.labelSmall
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "Log in",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Id",
                    style = MaterialTheme.typography.labelLarge
                )
                FCTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueChange = onIdChange,
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Pwd",
                    style = MaterialTheme.typography.labelLarge
                )
                FCTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = pwd,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onPwdChange,
                )
                FCButton(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    text = "로그인",
                    onClick = onLoginClick
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 24.dp)
                        .clickable(onClick = onNavigateSignUpScreen)
                ) {
                    Text("프로피루가 나레바")
                    Text(
                        "카이인 도로쿠 시테쿠다사이",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreView() {
    SNSTheme {
        LoginScreen(
            id = "Iddd",
            pwd = "Pwddd",
            onIdChange = {},
            onPwdChange = { },
            onNavigateSignUpScreen = {},
            onLoginClick = {}
        )
    }
}