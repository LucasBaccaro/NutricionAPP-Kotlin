package edu.neo.tpfinal.model

import java.io.Serializable

data class Usuario(
    var nombre: String? = "",
    var apellido: String? = "",
    var dni: String? = "",
    var sexo: String? = "",
    var fechaNac: String? = "",
    var localidad: String? = "",
    var usuario: String? = "",
    var contraseña: String? = "",
    var tipoDeTratamiento: String? = ""
) : Serializable