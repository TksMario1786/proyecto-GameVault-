package com.example.gamevault.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamevault.domain.model.Videojuego

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    videojuego: Videojuego,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(videojuego.titulo) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Plataforma: ${videojuego.plataforma}", style = MaterialTheme.typography.bodyLarge)
                    Text("Horas jugadas: ${videojuego.horasJugadas}", style = MaterialTheme.typography.bodyLarge)
                    Text("Fecha de adquisición: ${videojuego.fechaAdquisicion}", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "Estado: ${if (videojuego.completado) "Completado" else "Pendiente"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar Registro")
            }

            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}