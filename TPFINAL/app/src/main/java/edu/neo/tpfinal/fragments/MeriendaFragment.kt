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

class MeriendaFragment : Fragment(R.layout.fragment_merienda) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usuarioActivo = FirebaseAuth.getInstance().currentUser
        var premio: Button = view.findViewById(R.id.btnPremioMerienda)
        var comidaPrincipal_Merienda: TextInputEditText =view.findViewById(R.id.ComidaPrincipal_Merienda)
        var comidaSecundaria_Merienda: TextInputEditText =view.findViewById(R.id.ComidaSecundaria_Merienda)
        var bebida_Merienda: TextInputEditText =view.findViewById(R.id.Bebida_Merienda)
        //Instancio VM
        val comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        //Obtengo fecha
        var fechaActual =comidaVM.obtenerFechaYHora()

        premio.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Esta seguro que desea confirmar ?")
            builder.setMessage("Esta por confirmar su merienda")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->

                //Valido campos
                if(comidaPrincipal_Merienda.text.isNullOrBlank() || comidaSecundaria_Merienda.text.isNullOrBlank() || bebida_Merienda.text.isNullOrBlank())
                {
                    Toast.makeText(context, "Complete los campos.", Toast.LENGTH_SHORT).show()
                }else{
                    //Seteo comidas en bd
                    comidaVM.setComidas(
                        Comida(
                            "Merienda",
                            comidaPrincipal_Merienda.text.toString(),
                            comidaSecundaria_Merienda.text.toString(),
                            bebida_Merienda.text.toString(),
                            null,
                            null,
                            null,
                            fechaActual,usuarioActivo!!.email.toString()
                        )
                    )
                    findNavController().navigate(R.id.action_meriendaFragment_to_premioFragment)
                }
                Toast.makeText(context, "Muchas gracias!", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(context, "Permaneces en la APP", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}