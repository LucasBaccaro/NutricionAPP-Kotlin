package edu.neo.tpfinal.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Usuario
import edu.neo.tpfinal.view.MainActivity
import edu.neo.tpfinal.view.MenuActivity
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import java.lang.Exception

class Register : Fragment() {

    private var tipoSexoSeleccionado: String = ""
    private var tipoTratamientoSeleccionado: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var usuario: TextInputEditText = view.findViewById(R.id.Email_Register)
        var nombre: TextInputEditText = view.findViewById(R.id.Nombre)
        var apellido: TextInputEditText = view.findViewById(R.id.Apellido)
        var dni: TextInputEditText = view.findViewById(R.id.Dni)
        var fechaNac: TextInputEditText = view.findViewById(R.id.fechaNac)
        var contraseña: TextInputEditText = view.findViewById(R.id.Password_Register)
        var localidad:TextInputEditText = view.findViewById(R.id.Localidad)

        //Inicializo Spinner
        initializeSpinnerSexo()
        //Inicializo Spinner
        initializeSpinnerTratamiento()
        //Inicializo Spicker
        spicker()

        var aceptar: Button = view.findViewById(R.id.btn_alta)
        //Instancio VM
        val usuarioVM = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        aceptar.setOnClickListener {

            //Valido campos
            if(usuario.text.isNullOrBlank() || contraseña.text.isNullOrBlank() || nombre.text.isNullOrBlank() || apellido.text.isNullOrBlank() || dni.text.isNullOrBlank() || fechaNac.text.isNullOrBlank()){
                Toast.makeText(context, "Complete los campos", Toast.LENGTH_SHORT).show()
            }else{
                //Creo Usuario y lo seteo en BD en una tabla aparte
                usuarioVM.crearUsuario(usuario.text.toString(), contraseña.text.toString(), requireContext(), nombre.text.toString(), apellido.text.toString(), dni.text.toString(), tipoSexoSeleccionado, fechaNac.text.toString(), tipoTratamientoSeleccionado)
                val intent = Intent (it.context,MainActivity::class.java)
                intent.putExtra("email",usuario.toString())
                startActivity(intent)
            }
        }
    }

    private fun initializeSpinnerSexo() {
        val tiposSexoPersona = arrayOf("Femenino", "Masculino", "Otro")
        var sp_tiposSexo: Spinner = view?.findViewById(R.id.s_tipoSexo)!!

        var adaptadorTiposSexo = ArrayAdapter(
            requireView().context,
            android.R.layout.simple_spinner_item,
            tiposSexoPersona
        )
        sp_tiposSexo.adapter = adaptadorTiposSexo

        sp_tiposSexo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // TODO: cargar la lista
                Toast.makeText(context, "no hay items seleccionados", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoSexoSeleccionado = tiposSexoPersona[position]
            }
        }
    }

    private fun initializeSpinnerTratamiento() {
        val tiposTratamiento = arrayOf("Anorexia", "Bulimia", "Obesidad")
        var sp_tiposTratamiento: Spinner = view?.findViewById(R.id.s_tipoTratamiento)!!

        var adaptadorTiposSexo = ArrayAdapter(
            requireView().context,
            android.R.layout.simple_spinner_item,
            tiposTratamiento
        )
        sp_tiposTratamiento.adapter = adaptadorTiposSexo

        sp_tiposTratamiento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // TODO: cargar la lista
                Toast.makeText(context, "no hay items seleccionados", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoTratamientoSeleccionado = tiposTratamiento[position]
            }
        }
    }

    private fun spicker() {
        view?.findViewById<ImageButton?>(R.id.picker)?.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val pickerVerdadero: MaterialDatePicker<*> = builder.build()
            pickerVerdadero.show(childFragmentManager, pickerVerdadero.toString())
            pickerVerdadero.addOnPositiveButtonClickListener {
                view?.findViewById<TextInputEditText?>(R.id.fechaNac)
                    ?.setText(pickerVerdadero.headerText)
            }
        }
    }
}
