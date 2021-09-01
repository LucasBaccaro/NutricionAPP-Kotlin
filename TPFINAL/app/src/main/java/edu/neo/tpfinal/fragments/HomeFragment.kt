package edu.neo.tpfinal.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R
import edu.neo.tpfinal.view.MainActivity
import edu.neo.tpfinal.view.MenuActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    val usuarioActivo = FirebaseAuth.getInstance().currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var almuerzo: Button = view.findViewById(R.id.btn_almuerzo)
        var cena: Button = view.findViewById(R.id.btn_cena)
        var desayuno: Button = view.findViewById(R.id.btn_desayuno)
        var merienda: Button = view.findViewById(R.id.btn_merienda)
        var cerrarSesion: Button = view.findViewById(R.id.btn_CerrarSesion)
        var informes : Button = view.findViewById(R.id.btn_Informes)

        almuerzo.setOnClickListener {
            //Navego entre fragmentos
            findNavController().navigate(R.id.action_homeFragment_to_almuerzoFragment)
        }
        cena.setOnClickListener {
            //Navego entre fragmentos
            findNavController().navigate(R.id.action_homeFragment_to_cenaFragment)
        }
        desayuno.setOnClickListener {
            //Navego entre fragmentos
            findNavController().navigate(R.id.action_homeFragment_to_desayunoFragment)
        }
        merienda.setOnClickListener {
            //Navego entre fragmentos
            findNavController().navigate(R.id.action_homeFragment_to_meriendaFragment)
        }
        informes.setOnClickListener {
            //Navego entre fragmentos
            findNavController().navigate(R.id.action_homeFragment_to_informesFragment)
        }

        //Cierro sesion y vuelvo al main
        cerrarSesion.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Esta seguro que desea cerrar sesion?")
            builder.setMessage("Esta por cerrar sesion")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                FirebaseAuth.getInstance().signOut()
                val intent: Intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(context, "Permaneces en la APP", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}