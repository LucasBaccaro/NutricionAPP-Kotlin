package edu.neo.tpfinal.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.viewmodel.ComidaViewModel

class DesayunoFragment : Fragment(R.layout.fragment_desayuno) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usuarioActivo = FirebaseAuth.getInstance().currentUser
        var premio: Button = view.findViewById(R.id.btnPremioDesayuno)
        var comidaPrincipal_Desayuno:TextInputEditText=view.findViewById(R.id.ComidaPrincipal_Desayuno)
        var comidaSecundaria_Desayuno:TextInputEditText=view.findViewById(R.id.ComidaSecundaria_Desayuno)
        var bebida_Desayuno:TextInputEditText=view.findViewById(R.id.Bebida_Desayuno)
        //Instancio VM
        val comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        //Obtengo fecha y hora
        var fechaActual =comidaVM.obtenerFechaYHora()


        premio.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Esta seguro que desea confirmar ?")
            builder.setMessage("Esta por confirmar su desayuno")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                //Valido campos
                if(comidaPrincipal_Desayuno.text.isNullOrBlank() || comidaSecundaria_Desayuno.text.isNullOrBlank() || bebida_Desayuno.text.isNullOrBlank())
                {
                    Toast.makeText(context, "Complete los campos.", Toast.LENGTH_SHORT).show()
                }else{
                    //Seteo Comidas en bd
                    comidaVM.setComidas(
                        Comida(
                            "Desayuno",
                            comidaPrincipal_Desayuno.text.toString(),
                            comidaSecundaria_Desayuno.text.toString(),
                            bebida_Desayuno.text.toString(),
                            null,
                            null,
                            null,
                            fechaActual,usuarioActivo!!.email.toString()
                        )
                    )//Navego a premioFragment
                    findNavController().navigate(R.id.action_desayunoFragment_to_premioFragment)

                }
                Toast.makeText(context, "Muchas gracias", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(context, "Permaneces en la APP", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}