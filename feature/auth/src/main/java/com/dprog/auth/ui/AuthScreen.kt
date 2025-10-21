package com.dprog.auth.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    onSignedIn: () -> Unit,
    vm: AuthViewModel = koinViewModel(),
) {
    val ui by vm.ui.collectAsState()
    val activity = LocalActivity.current

    LaunchedEffect(ui) {
        if (ui is AuthUiState.Ready && (ui as AuthUiState.Ready).isSignedIn) onSignedIn()
    }

    Scaffold { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Sign in to Expenseer", style = MaterialTheme.typography.headlineMedium)

            Button(
                onClick = { activity?.let { vm.signIn(it) } },
                enabled = ui !is AuthUiState.Loading,
            ) { Text("Continue with Google") }

            if (ui is AuthUiState.Error) {
                Text((ui as AuthUiState.Error).message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
