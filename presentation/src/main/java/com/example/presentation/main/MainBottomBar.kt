package com.example.presentation.main

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.presentation.theme.SNSTheme
import com.example.presentation.main.writing.WritingActivity

@Composable
fun MainBottomBar(
    navController: NavController
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: MainRoute = navBackStackEntry?.destination?.route?.let { currentRoute ->
        MainRoute.entries.find { route ->
            currentRoute == route.route
        }
    } ?: MainRoute.BOARD
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) {
        context.startActivity(
            Intent(context, WritingActivity::class.java)
        )
    }
    MainBottomBar(
        currentRoute = currentRoute,
        onItemClick = { newRoute ->
            if (newRoute == MainRoute.WRITING) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissionLauncher.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
                } else {
                    permissionLauncher.launch(arrayOf(READ_EXTERNAL_STORAGE))
                }
            } else {
                navController.navigate(newRoute.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) {
                            saveState = true
                        }
                        this.launchSingleTop = true
                        this.restoreState = true
                    }
                }
            }

        }
    )
}

@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit,
) {
    Column() {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MainRoute.entries.forEach { item ->
                IconButton(onClick = { onItemClick(item) }) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription,
                        tint = if (currentRoute == item) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.White
                        }
                    )

                }
            }

        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    SNSTheme {
        Surface {
            var currentRoute by remember { mutableStateOf(MainRoute.BOARD) }
            MainBottomBar(
                currentRoute = currentRoute,
                onItemClick = { newRoute -> currentRoute = newRoute }
            )
        }
    }

}