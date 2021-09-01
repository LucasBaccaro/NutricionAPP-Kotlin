package edu.neo.tpfinal.fragments

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.viewmodel.ComidaViewModel
import java.text.SimpleDateFormat
import java.util.*


class AlmuerzoFragment : Fragment(R.layout.fragment_almuerzo) {

    val usuarioActivo = FirebaseAuth.getInstance().currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var premio: Button = view.findViewById(R.id.btn_premioAlmuerzo)
        var comidaPrincipal_Almuerzo: TextInputEditText =view.findViewById(R.id.ComidaPrincipal_Almuerzo)
        var comidaSecundaria_Almuerzo: TextInputEditText = view.findViewById(R.id.ComidaSecundaria_Almuerzo)
        var bebida_Almuerzo: TextInputEditText = view.findViewById(R.id.Bebida_Almuerzo)
        var postreAlmuerzo: TextInputEditText = view.findViewById(R.id.Postre_Almuerzo)
        var alimentoAIngerir_Almuerzo: TextInputEditText = view.findViewById(R.id.AlimentoExtra_Almuerzo)
        var bSiAlmuerzo: RadioButton = requireView().findViewById(R.id.btnSiAlmuerzo)
        var bNoAlmuerzo: RadioButton = requireView().findViewById(R.id.btnNoAlmuerzo)
        var hambre: String = ""

        //Instancio VM
        val comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        //Obtengo fecha
        var fechaActual =comidaVM.obtenerFechaYHora()
        //Muestro elementos
        visibilidadElementos()

        //RaddioButton
        bSiAlmuerzo.setOnClickListener {
            hambre = "si"
        }
        bNoAlmuerzo.setOnClickListener {
            hambre = "no"
        }

        premio.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Esta seguro que desea confirmar?")
            builder.setMessage("Esta por confirmar su almuerzo")
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                //Valido campos vacios
                if (comidaPrincipal_Almuerzo.text.isNullOrBlank() || comidaSecundaria_Almuerzo.text.isNullOrBlank() || bebida_Almuerzo.text.isNullOrBlank() || hambre.isNullOrBlank()) {
                    Toast.makeText(context, "Complete los campos", Toast.LENGTH_SHORT).show()
                } else {
                    //seteo comidas en BD
                    comidaVM.setComidas(
                        Comida(
                            "Almuerzo",
                            comidaPrincipal_Almuerzo.text.toString(),
                            comidaSecundaria_Almuerzo.text.toString(),
                            bebida_Almuerzo.text.toString(),
                            postreAlmuerzo.text.toString(),
                            alimentoAIngerir_Almuerzo.text.toString(),
                            hambre,
                            fechaActual,usuarioActivo!!.email.toString()
                        )
                    )
                    //Viajo al premioFragment
                    findNavController().navigate(R.id.action_almuerzoFragment_to_premioFragment)
                }

                Toast.makeText(context, "Muchas gracias", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(context, "Permaneces en la APP", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    private fun visibilidadElementos() {
        var postreSIAlmuerzo: Button = requireView().findViewById(R.id.SiPostreAlmuerzo)
        var postreNOAlmuerzo: Button = requireView().findViewById(R.id.NoPostreAlmuerzo)
        var postreAlmuerzo: TextInputEditText = requireView().findViewById(R.id.Postre_Almuerzo)
        var postreLayoutAlmuerzo: TextInputLayout =
            requireView().findViewById(R.id.Postre_Layout_Almuerzo)

        var alimentoSIAlmuerzo: Button = requireView().findViewById(R.id.SiAlimentoAlmuerzo)
        var alimentoNOAlmuerzo: Button = requireView().findViewById(R.id.NoAlimentoAlmuerzo)
        var alimentoAlmuerzo: TextInputEditText =
            requireView().findViewById(R.id.AlimentoExtra_Almuerzo)
        var alimentoLayoutAlmuerzo: TextInputLayout =
            requireView().findViewById(R.id.AlimentoExtra_Layout_Almuerzo)

        postreAlmuerzo.setVisibility(View.GONE)
        postreLayoutAlmuerzo.setVisibility(View.GONE)
        postreSIAlmuerzo.setOnClickListener {
            postreAlmuerzo.setVisibility(View.VISIBLE)
            postreLayoutAlmuerzo.setVisibility(View.VISIBLE)
        }
        postreNOAlmuerzo.setOnClickListener {
            postreAlmuerzo.setVisibility(View.GONE)
            postreLayoutAlmuerzo.setVisibility(View.GONE)
        }

        alimentoAlmuerzo.setVisibility(View.GONE)
        alimentoLayoutAlmuerzo.setVisibility(View.GONE)

        alimentoSIAlmuerzo.setOnClickListener {
            alimentoAlmuerzo.setVisibility(View.VISIBLE)
            alimentoLayoutAlmuerzo.setVisibility(View.VISIBLE)
        }
        alimentoNOAlmuerzo.setOnClickListener {
            alimentoAlmuerzo.setVisibility(View.GONE)
            alimentoLayoutAlmuerzo.setVisibility(View.GONE)
        }
    }
}