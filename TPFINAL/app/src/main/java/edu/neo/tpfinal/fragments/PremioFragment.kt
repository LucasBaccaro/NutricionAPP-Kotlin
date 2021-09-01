package edu.neo.tpfinal.fragments

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import edu.neo.tpfinal.R
import edu.neo.tpfinal.api.ApiTragos
import edu.neo.tpfinal.api.implementation.TragosImplementation
import edu.neo.tpfinal.model.Tragos
import edu.neo.tpfinal.viewmodel.ComidaViewModel
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PremioFragment : Fragment(R.layout.fragment_premio) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var home: Button = view.findViewById(R.id.btn_Home)
        var nombreTrago:TextInputEditText = view.findViewById(R.id.nombre_trago)
        var imagenTrago:ImageView = view.findViewById(R.id.img_trago)

        //InstancioVM
        val comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)

        //Obtengo tragosRandom desde API y los muestro
        comidaVM.getTragos().enqueue(object : Callback<Tragos>{
            override fun onResponse(call: Call<Tragos>, response: Response<Tragos>) {
                if(response.body() != null){
                    val data = response.body()
                    nombreTrago.setText(data?.drinks!!.get(0).strDrink)
                    Glide
                        .with(view.context)
                        .load(data?.drinks?.get(0)?.strDrinkThumb)
                        .centerCrop()
                        .into(imagenTrago)
                }
            }
            override fun onFailure(call: Call<Tragos>, t: Throwable) {
                Toast.makeText(context, "Error con la API", Toast.LENGTH_SHORT).show()
            }
        })
        home.setOnClickListener {
            //Navego a homeFragment
            findNavController().navigate(R.id.action_premioFragment_to_homeFragment)
        }
    }
}