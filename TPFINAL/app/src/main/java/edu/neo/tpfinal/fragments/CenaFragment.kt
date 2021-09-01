package edu.neo.tpfinal.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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


class CenaFragment : Fragment(R.layout.fragment_cena) {

    val usuarioActivo = FirebaseAuth.getInstance().currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        var premio: Button = view.findViewById(R.id.btn_premioCena)
        var comidaPrincipal_Cena: TextInputEditText = view.findViewById(R.id.ComidaPrincipal_Cena)
        var comidaSecundaria_Cena: TextInputEditText = view.findViewById(R.id.ComidaSecundaria_Cena)
        var bebida_Cena: TextInputEditText = view.findViewById(R.id.Bebida_Cena)
        var postreCena: TextInputEditText = view.findViewById(R.id.Postre_Cena)
        var alimentoAIngerir_Cena: TextInputEditText = view.findViewById(R.id.AlimentoExtra_Cena)
        var hambre: String = ""

        //Obtengo fecha
        var fechaActual =comidaVM.obtenerFechaYHora()

        var bSiCena: RadioButton = requireView().findViewById(R.id.btnsi)
        var bNoCena: RadioButton = requireView().findViewById(R.id.btnno)

        //RadioButton
        bSiCena.setOnClickListener {
            hambre = "si"
        }
        bNoCena.setOnClickListener {
            hambre = "no"
        }
        //Muestro elementos
        visibilidadElementos()

        premio.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Esta seguro que desea confirmar ?")
            builder.setMessage("Esta por confirmar su cena")
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                //Valido campos
                if (comidaPrincipal_Cena.text.isNullOrBlank() || comidaSecundaria_Cena.text.isNullOrBlank() || bebida_Cena.text.isNullOrBlank() || hambre.isNullOrBlank()) {
                    Toast.makeText(context, "Complete los campos", Toast.LENGTH_SHORT).show()
                } else {
                    //Seteo comidas en BD
                    comidaVM.setComidas(
                        Comida(
                            "Cena",
                            comidaPrincipal_Cena.text.toString(),
                            comidaSecundaria_Cena.text.toString(),
                            bebida_Cena.text.toString(),
                            postreCena.text.toString(),
                            alimentoAIngerir_Cena.text.toString(),
                            hambre,
                            fechaActual,usuarioActivo!!.email.toString())
                    )
                    //Navego a premioFragment
                    findNavController().navigate(R.id.action_cenaFragment_to_premioFragment)
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
        var postreSICena: Button = requireView().findViewById(R.id.SiPostreCena)
        var postreNOCena: Button = requireView().findViewById(R.id.NoPostreCena)
        var postreCena: TextInputEditText = requireView().findViewById(R.id.Postre_Cena)
        var postreLayoutCena: TextInputLayout = requireView().findViewById(R.id.Postre_Layout_Cena)

        var alimentoSICena: Button = requireView().findViewById(R.id.SiAlimentoCena)
        var alimentoNOCena: Button = requireView().findViewById(R.id.NoAlimentoCena)
        var alimentoCena: TextInputEditText = requireView().findViewById(R.id.AlimentoExtra_Cena)
        var alimentoLayoutCena: TextInputLayout =
            requireView().findViewById(R.id.AlimentoExtra_Layout_Cena)

        postreCena.setVisibility(View.GONE)
        postreLayoutCena.setVisibility(View.GONE)
        postreSICena.setOnClickListener {
            postreCena.setVisibility(View.VISIBLE)
            postreLayoutCena.setVisibility(View.VISIBLE)
        }
        postreNOCena.setOnClickListener {
            postreCena.setVisibility(View.GONE)
            postreLayoutCena.setVisibility(View.GONE)
        }

        alimentoCena.setVisibility(View.GONE)
        alimentoLayoutCena.setVisibility(View.GONE)

        alimentoSICena.setOnClickListener {
            alimentoCena.setVisibility(View.VISIBLE)
            alimentoLayoutCena.setVisibility(View.VISIBLE)
        }
        alimentoNOCena.setOnClickListener {
            alimentoCena.setVisibility(View.GONE)
            alimentoLayoutCena.setVisibility(View.GONE)
        }
    }

}