package edu.neo.tpfinal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import edu.neo.tpfinal.R
import edu.neo.tpfinal.api.ApiTragos
import edu.neo.tpfinal.api.implementation.TragosImplementation
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Tragos
import edu.neo.tpfinal.recyclerview.MyAdapterInformes
import edu.neo.tpfinal.viewmodel.ComidaViewModel
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformesFragment : Fragment(R.layout.fragment_informes) {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapterInformes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usuarioActivo = FirebaseAuth.getInstance().currentUser?.email
        var btn:Button = view.findViewById(R.id.btnInicioComidas)

        recyclerView = view.findViewById(R.id.rv_informes)
        recyclerView.layoutManager = LinearLayoutManager(context)

        getComidas(usuarioActivo.toString())

        btn.setOnClickListener {

            //Navego a homeFragment
            findNavController().navigate(R.id.action_informesFragment_to_homeFragment)
        }
    }
    //Me traigo las comidas por usuario logueado en Firebase

    //No pude hacerlo desde el DAO
    fun getComidas(usuarioActivo:String){

        val db = FirebaseFirestore.getInstance()
        db.collection("comidas").whereEqualTo("email",usuarioActivo).get().addOnSuccessListener {
            val userList = it.toObjects(Comida::class.java)
            myAdapter = MyAdapterInformes(userList)
            recyclerView.adapter = myAdapter
        }
    }
}