package edu.neo.tpfinal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import edu.neo.tpfinal.R

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val foto: LottieAnimationView = findViewById(R.id.imageView)
        val usuarioActivo = FirebaseAuth.getInstance().currentUser

        //Pregunto si el current user es nulo, para evitar la pantalla de login. si es nulo va al login, sino al Menu
        if(usuarioActivo != null){
            foto.setAnimation(R.raw.splash)
            foto.playAnimation()
            foto.loop(true)
            Handler().postDelayed({
                startActivity(Intent(this, MenuActivity::class.java))
                this.finish()
            },4000)
        }else{
            foto.setAnimation(R.raw.splash)
            foto.playAnimation()
            foto.loop(true)
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            },4000)
        }



    }
}