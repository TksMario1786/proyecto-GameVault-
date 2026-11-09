package com.example.gamevault.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameFormScreen(
    viewModel: GameFormViewModel,
    gameId: String?,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(gameId) {
        if (gameId != null && uiState.id != gameId) {
            viewModel.loadVideojuego(gameId)
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onNavigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (gameId == null) "Nuevo Juego" else "Editar Juego") })
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uiState.titulo,
                onValueChange = { viewModel.updateField("titulo", it) },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.plataforma,
                onValueChange = { viewModel.updateField("plataforma", it) },
                label = { Text("Plataforma") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.horasJugadas,
                onValueChange = { viewModel.updateField("horasJugadas", it) },
                label = { Text("Horas Jugadas") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.fechaAdquisicion,
                onValueChange = { viewModel.updateField("fechaAdquisicion", it) },
                label = { Text("Fecha de Adquisición (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = uiState.completado,
                    onCheckedChange = { viewModel.updateField("completado", it) }
                )
                Text("Juego Completado")
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Button(onClick = { viewModel.saveVideojuego() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Guardar")
                }
            }

            if (uiState.error != null) {
                Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}