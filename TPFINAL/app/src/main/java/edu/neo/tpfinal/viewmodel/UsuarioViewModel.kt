package edu.neo.tpfinal.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import edu.neo.tpfinal.dao.dbHelper
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Usuario
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UsuarioViewModel : ViewModel() {

    var firebase = dbHelper()

    //Creo usuario , no tuve manera de hacerlo desde dao, creo que es un problema de asincronismo y no le pude encontrar la vuelta
    fun crearUsuario(
        usuario: String, contraseña: String, context: Context, nombre: String,
        apellido: String, dni: String, sexo: String, fechaNac: String, tratamiento: String
    ): Boolean {
        var retorno: Boolean = false

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(usuario, contraseña).addOnSuccessListener {
                altaUsuario(usuario, nombre, apellido, dni, sexo, fechaNac, tratamiento)
                Toast.makeText(context, "Completado, inicie sesion por favor", Toast.LENGTH_SHORT)
                    .show()
                retorno = true
            }.addOnFailureListener {
                Toast.makeText(context, "Error al darse de alta", Toast.LENGTH_SHORT).show()
                retorno = false
            }
        return retorno
    }

    //Lo mismo, solamente que lo uso directamente en el CREARUSUARIO, para que me setee los datos del usuario en firebase
    fun altaUsuario(
        email: String, nombre: String, apellido: String, dni: String, sexo: String,
        fechaNac: String, tratamiento: String
    ) {
        try {
            val db = FirebaseFirestore.getInstance()
            db.collection("usuarios").document(email).set(
                hashMapOf(
                    "nombre" to nombre,
                    "apellido" to apellido,
                    "dni" to dni,
                    "sexo" to sexo,
                    "fechaNac" to fechaNac,
                    "tratamiento" to tratamiento
                )
            )
        } catch (e: Exception) {
        }
    }

}