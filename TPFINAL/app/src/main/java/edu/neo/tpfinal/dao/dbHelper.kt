package edu.neo.tpfinal.dao

import com.google.firebase.firestore.FirebaseFirestore
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Usuario

class dbHelper {

    // Seteo las comidas de los usuarios en bd
    fun comidasFirebase(comida: Comida): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()
            db.collection("comidas").document().set(comida)
            true
        } catch (e: Exception) {
            false
        }
    }
}