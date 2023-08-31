package com.example.grettingcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grettingcards.ui.theme.GrettingCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun principal()  {
    Column(modifier = Modifier
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "CREADOR DE CARTAS", color = Color.White, modifier = Modifier.background(Color.Gray).padding(21.dp),style = TextStyle(
            fontSize = 35.sp, fontWeight = FontWeight.Bold))

    Column(modifier = Modifier
        .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        //Boton para ingresar Datos
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "INGRESAR DATOS  ", color = Color.Black, style = TextStyle(fontSize = 27.sp))
            Icon(imageVector = Icons.Default.Info, contentDescription = "Icono de Creacion",tint = Color.Black)
        }
        Spacer(modifier = Modifier.height(45.dp))
        //Boton para ingresar Datos
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "VER DISEÑOS ",color = Color.Black, style = TextStyle(fontSize = 27.sp))
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Icono de Creacion", tint = Color.Black)
        }
    }
    }

}


@Preview(showSystemUi = true)
@Composable
fun previewInicial(){
    principal()
}

/**
 * Clase para rectangulo
 */
class Carta{
    var dedicadonombre: String = ""
    var dedicadoapelli: String = ""
    var dedicatoria:String = ""
    var tipo:Int = 0
    constructor(_nombre:String,_apellido:String,_dedica:String,_tipo:Int){
        dedicadonombre = _nombre
        dedicadoapelli = _apellido
        dedicatoria = _dedica
        tipo = _tipo

    }
}


