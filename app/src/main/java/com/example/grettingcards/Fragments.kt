package com.example.grettingcards

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Fragments : ComponentActivity() {


    // Lista de los fondos
    val fondos_cumple: List<String> = listOf("fondo_cumple1", "fondo_cumple2", "fondo_cumple3")
    val fondos_boda: List<String> = listOf("fondo_boda1", "fondo_boda2", "fondo_boda3")
    val fondos_valentin: List<String> =
        listOf("fondo_valentin1", "fondo_valentin2", "fondo_valentin3")

    val fondos_madre: List<String> = listOf("fondo_madre1", "fondo_madre2", "fondo_madre3")
}

//PRUEBAS
val fondos_navidad = mapOf(
    0 to R.drawable.fondo_navidad1,
    1 to R.drawable.fondo_navidad2,
    2 to R.drawable.fondo_navidad3
)


@Composable
fun menuFragment(fondos: Map<Int, Int>) {

    // Id del fondo 0
    var id_actual by remember {
        mutableStateOf(0)
    }
    var fondo_actual by remember {

        mutableStateOf(id_actual)
    }



    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupar todo el espacio disponible
    ) {

        /**
         * FONDO
         *
         * Esta fila  ocupará la mayoría de espacio del diseño
         * */
        Row(
            modifier = Modifier
                .weight(1f) // Ocupa 1f de espacio
                .fillMaxWidth() // Ocupar todo el espacio horizontal
            //.background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = fondos[id_actual]!!),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        /**
         * BOTONES
         *
         * Esta fila ocupará el espacio necesario para los botones
         * */
        Row(
            modifier = Modifier
                .wrapContentHeight() // Altura dinámica de los botones
                .fillMaxWidth() // Ocupar todo el espacio horizontal
                .background(Color.White) // Color de fondo blanco
        ) {

            // Separador de del borde izquierdo
            Spacer(modifier = Modifier.width(20.dp))
            // Boton de Regresar
            IconButton(
                // Acción del boton
                onClick = {
                    id_actual = (id_actual - 1 + fondos.size) % fondos.size
                },
                // Tamaño del boton
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    // Traer la imagen llamada "icon_atras"
                    painter = painterResource(id = R.drawable.icon_atras),
                    // Descripción
                    contentDescription = "Diseño anterior",
                    // Ocupar el espacio máximo del elemento
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Separador entre botones
            Spacer(modifier = Modifier.weight(1f))
            // Boton de Inicio
            IconButton(
                // Acción del boton
                onClick = { /* TODO: Implementar acción de regresar */ },
                // Tamaño del boton
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    // Traer la imagen llamada "icon_inicio"
                    painter = painterResource(id = R.drawable.icon_inicio),
                    // Descripción
                    contentDescription = "Regresar a Inicio",
                    // Ocupar el espacio máximo del elemento
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Separador entre botones
            Spacer(modifier = Modifier.weight(1f))
            // Boton de Avanzar
            IconButton(
                // Acción del boton
                onClick = {
                    id_actual = (id_actual + 1) % fondos.size
                },
                // Tamaño del boton
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    // Traer la imagen llamada "icon_inicio"
                    painter = painterResource(id = R.drawable.icon_siguiente),
                    // Descripción
                    contentDescription = "Diseño siguiente",
                    // Ocupar el espacio máximo del elemento
                    modifier = Modifier.fillMaxSize()
                )
            }
            // Separadorde del borde derecho
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
@Preview
fun disenioPreview() {

    menuFragment(fondos_navidad)

}