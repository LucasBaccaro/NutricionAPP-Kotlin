package edu.neo.tpfinal.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R

class MenuActivity : AppCompatActivity() {

    //Aca solamente tengo el navigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

}