package edu.neo.tpfinal.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R
import edu.neo.tpfinal.fragments.Register
import edu.neo.tpfinal.viewmodel.UsuarioViewModel

class MainActivity : AppCompatActivity() {
    lateinit var login: Button
    lateinit var register: Button
    lateinit var usuario: TextInputEditText
    lateinit var contraseña: TextInputEditText



    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    val registerUsuario = Register()

    val usuarioActivo = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializar()

        //Infla el fragmento de registrar usuario
        register.setOnClickListener {
            transaction.replace(R.id.frame, registerUsuario)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.frame, registerUsuario)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        }


        login.setOnClickListener {
            //Valido campos
            if (usuario.text.isNullOrBlank() || contraseña.text.isNullOrBlank()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            } else {
                //Logueo con Authentication de Firebase
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    usuario.text.toString(),
                    contraseña.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //Si es correcto, te llevo al menu
                        showHome(usuario.toString())
                        Toast.makeText(this, "Bienvenido nuevamente!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        //Sino te tiro un dialogo de alerta
                        showAlert()
                    }
                }
            }

        }
    }

    fun inicializar() {
        login = findViewById(R.id.btnLogin)
        register = findViewById(R.id.btnRegister)
        usuario = findViewById(R.id.Usuario_login)
        contraseña = findViewById(R.id.Contraseña_login)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la autenticacion")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {

        val intent: Intent = Intent(this, MenuActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }

}