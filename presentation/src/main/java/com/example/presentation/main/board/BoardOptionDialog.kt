package com.example.presentation.main.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.presentation.model.BoardCardModel
import com.example.presentation.theme.SNSTheme

@Composable
fun BoardOptionDialog(
    model: BoardCardModel?,
    onBoardDelete: (BoardCardModel) -> Unit,
    onDismissRequest: () -> Unit,
) {
    model?.run {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary
                    ), onClick = {
                        onBoardDelete(model)
                        onDismissRequest()
                    }) {
                    Text("삭제")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBoardOptionDialog() {
    SNSTheme {
        Surface {
            BoardOptionDialog(
                model = null,
                onBoardDelete = {},
                onDismissRequest = {},
            )
        }
    }

}