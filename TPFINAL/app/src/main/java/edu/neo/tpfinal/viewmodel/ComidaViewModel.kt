package edu.neo.tpfinal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import edu.neo.tpfinal.api.implementation.TragosImplementation
import edu.neo.tpfinal.dao.dbHelper
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Tragos
import edu.neo.tpfinal.recyclerview.MyAdapterInformes
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class ComidaViewModel : ViewModel() {

    var firebase = dbHelper()

    //Llamo a dbhelper para setear las comidas
    fun setComidas(comida:Comida) : Boolean{

        firebase.comidasFirebase(comida)
        return true
    }

    //Obtengo tragos desde la API
    fun getTragos(): Call<Tragos> {
        val api: TragosImplementation = TragosImplementation()
        return api.getTrago("random.php")
    }

    //Obtengo fecha y hora
    fun obtenerFechaYHora(): String {
        var calendar = Calendar.getInstance()
        var formatoFecha = SimpleDateFormat("dd/MM/yy")
        var formatoHora = SimpleDateFormat("HH:mm")
        var fechaActual = formatoFecha.format(calendar.time)
        var horaActual = formatoHora.format(calendar.time)

        return fechaActual + " " + horaActual
    }
}