package com.example.grettingcards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grettingcards.ui.theme.GrettingCardsTheme

class Formulario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            formulario()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formulario(){
    val lanzador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        // Manejar el resultado si es necesario
    }
    val contexto = LocalContext.current


    var nombre by remember {
        mutableStateOf("Persona a quien dirige la carta (Nombre)")
    }
    var apellido by remember {
        mutableStateOf("Persona a quien dirige la carta (Apellido)")
    }
    var dedicatoria by remember {
        mutableStateOf("Dedicatoria a la Persona")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    // 1 Cumplea;os 2 Boda, 3 San Valentin 4 Navidad 5 Dia de la madre.
    var TiposdeCarta:Int = 0
    var NombreTipo by remember {
        mutableStateOf("")
    }

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        //Texto del titulo
        Text("! Ingresa los Datos ",style = TextStyle(
                fontSize = 35.sp, fontWeight = FontWeight.Bold
                )
        )
        Text(" de tu Carta !",style = TextStyle(
            fontSize = 35.sp, fontWeight = FontWeight.Bold
        )
        )
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value =nombre, onValueChange = {newText -> nombre = newText},label= { Text(text = "Nombre",modifier = Modifier.background(Color.White))})
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =apellido, onValueChange = {newText -> apellido = newText},label= { Text(text = "Apellido",modifier = Modifier.background(Color.White))})
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =dedicatoria, onValueChange = {newText -> dedicatoria = newText},label= { Text(text = "Dedicatoria",modifier = Modifier.background(Color.White))})
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            OutlinedTextField(value =NombreTipo, onValueChange = {},
                readOnly = true, trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                modifier = Modifier.menuAnchor()
                ,label= { Text(text = "Tipo de Carta",modifier = Modifier.background(Color.White))})

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {isExpanded=false}) {
                DropdownMenuItem(
                    text = { Text(text = "Cumpleaños")},
                    onClick = {
                        NombreTipo = "Cumpleaños"
                        TiposdeCarta = 1
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Boda")},
                    onClick = {
                        NombreTipo = "Boda"
                        TiposdeCarta = 2
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "San Valentin")},
                    onClick = {
                        NombreTipo = "San Valentin"
                        TiposdeCarta = 3
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Navidad")},
                    onClick = {
                        NombreTipo = "Navidad"
                        TiposdeCarta = 4
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Dia de la Madre")},
                    onClick = {
                        NombreTipo = "Dia de la Madre"
                        TiposdeCarta = 5
                        isExpanded = false
                    })

            }
        }
        Spacer(modifier = Modifier.height(150.dp))
        Button(onClick = {
            val navegacion = Intent(contexto,Fragments::class.java)
            //agregamos los parametros.
            navegacion.putExtra("name",nombre)
            navegacion.putExtra("apell",apellido)
            navegacion.putExtra("dedica",dedicatoria)
            navegacion.putExtra("tipo",TiposdeCarta)

            lanzador.launch(navegacion)

        }) {
            Text(text = "CREAR DISEÑO  ", style = TextStyle(fontSize = 27.sp))
            Icon(imageVector = Icons.Default.Create, contentDescription = "Icono de Creacion")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun previewForm(){
    formulario()
}