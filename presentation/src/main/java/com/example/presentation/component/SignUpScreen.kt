package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.SNSTheme
import com.example.presentation.theme.Typography

@Composable
fun SignUpScreen(
    id: String,
    name: String,
    pwd1: String,
    pwd2: String,
    onIdChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPwd1Change: (String) -> Unit,
    onPwd2Change: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "츠쿠루 어카운토",
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
                    onValueChange = onIdChange
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "UserName",
                    style = MaterialTheme.typography.labelLarge
                )
                FCTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = name,
                    onValueChange = onNameChange
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Pwd1",
                    style = MaterialTheme.typography.labelLarge
                )
                FCTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = pwd1,
                    onValueChange = onPwd1Change
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Pwd2",
                    style = MaterialTheme.typography.labelLarge
                )
                FCTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = pwd2,
                    onValueChange = onPwd2Change
                )
                FCButton(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    text = "Sign Up",
                    onClick = onSignUpClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreView() {
    SNSTheme {
        SignUpScreen(
            id = "Idddd",
            name = "TODO()",
            pwd1 = "TODO()",
            pwd2 = "TODO()",
            onIdChange = {},
            onNameChange = { },
            onPwd1Change = { },
            onPwd2Change = { },
            onSignUpClick = { }
        )
    }
}