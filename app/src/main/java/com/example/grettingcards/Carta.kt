package com.example.grettingcards

class Carta {
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