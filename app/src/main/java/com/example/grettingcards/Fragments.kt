package com.example.grettingcards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class Fragments : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nombre = intent.getStringExtra("name") ?: ""
            val apellido = intent.getStringExtra("apell") ?: ""
            val dedicatoria = intent.getStringExtra("dedica") ?: ""
            val tipoCarta = intent.getIntExtra("tipo", 0)

            menuFragment(nombre, apellido, dedicatoria, tipoCarta)
        }
    }
}


@Composable
fun menuFragment(nombre: String, apellido: String, dedicatoria: String, tipo_fondo: Int) {

    val lanzador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        // Manejar el resultado si es necesario
    }
    val contexto = LocalContext.current

    val fondos: Map<Int, Int> = dameFondo(tipo_fondo)

    // Id del fondo 0
    var id_actual by remember {
        mutableStateOf(0)
    }
    var fondo_actual by remember {

        mutableStateOf(id_actual)
    }

    // Color del texto
    // Utilizar la detección de color dominante en la imagen de fondo
    val colorContraste =
        MaterialTheme.colorScheme.primary

    val colorFinal = if (colorContraste.isColorDark()) {
        Color.Black // Usar color oscuro si el fondo es claro

    } else {
        Color.White // Usar color claro si el fondo es oscuro
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
                .paint(
                    painterResource(id = fondos[id_actual]!!),
                    contentScale = ContentScale.FillBounds
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column() {
                Spacer(modifier = Modifier.height(50.dp)) // Espacio vertical
                // Nombre
                Text(
                    text = "De:" + nombre + "\n" + "Para: " + apellido,
                    color = colorContraste,
                    style = TextStyle(fontSize = 30.sp) // Tamaño grande
                )
                Spacer(modifier = Modifier.height(8.dp)) // Espacio vertical
                // Dedicatoria
                Text(
                    text = dedicatoria,
                    color = colorContraste,
                    style = TextStyle(fontSize = 18.sp)
                )
            }

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
                onClick = {
                    val navegacion = Intent(contexto,MainActivity::class.java)
                    lanzador.launch(navegacion)
                },
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

/**
 * Función que detecta si el fondo es oscuro o claro
 * */
fun Color.isColorDark(): Boolean {
    val darkness = (0.299 * red + 0.587 * green + 0.114 * blue) / 255
    return darkness >= 0.5
}

/**
 * Función que determina qué fondo usar
 */
fun dameFondo(tipo_fondo: Int): Map<Int, Int> {
    return when (tipo_fondo) {
        // Cumpleaños
        1 -> mapOf(
            0 to R.drawable.fondo_cumplenos1,
            1 to R.drawable.fondo_cumplenos2,
            2 to R.drawable.fondo_cumplenos3,
            3 to R.drawable.fondo_cumplenos4,
            4 to R.drawable.fondo_cumplenos5
        )
        // Boda
        2 -> mapOf(
            0 to R.drawable.fondo_boda1,
            1 to R.drawable.fondo_boda2,
            2 to R.drawable.fondo_boda3,
            3 to R.drawable.fondo_boda3,
            4 to R.drawable.fondo_boda3
        )
        // San Valentín
        3 -> mapOf(
            0 to R.drawable.fondo_sanvalentin1,
            1 to R.drawable.fondo_sanvalentin2,
            2 to R.drawable.fondo_sanvalentin3,
            3 to R.drawable.fondo_sanvalentin4,
            4 to R.drawable.fondo_sanvalentin5
        )
        // Navidad
        4 -> mapOf(
            0 to R.drawable.fondo_navidad1,
            1 to R.drawable.fondo_navidad2,
            2 to R.drawable.fondo_navidad3,
            3 to R.drawable.fondo_navidad4,
            4 to R.drawable.fondo_navidad5
        )
        // Día de la madre
        else -> mapOf(
            0 to R.drawable.fondo_diadelamadre1,
            1 to R.drawable.fondo_diadelamadre2,
            2 to R.drawable.fondo_diadelamadre3,
            3 to R.drawable.fondo_diadelamadre4,
            4 to R.drawable.fondo_diadelamadre4
        )
    }
}


@Composable
@Preview
fun disenioPreview() {
    //menuFragment("Josue", "Perez", "¡Pásatelo bien!", 1)

}