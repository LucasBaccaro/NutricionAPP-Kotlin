package edu.neo.tpfinal.model

import java.io.Serializable

data class Comida(
    var tipoComidaDiaria: String? = "",
    var comidaPrincipal: String? = "",
    var comidaSecundaria: String? = "",
    var bebida: String? = "",
    var postre: String? = "",
    var alimentoAIngerir: String? = "",
    var hambre: String? = "",
    var fechaIngresada: String? = "",
    var email:String = ""
) : Serializable