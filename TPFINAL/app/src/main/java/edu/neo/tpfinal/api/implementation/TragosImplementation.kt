package edu.neo.tpfinal.api.implementation

import edu.neo.tpfinal.api.ApiTragos
import edu.neo.tpfinal.model.Tragos
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TragosImplementation {



    //Llamo a retrofit con la pagina principal de la API
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thecocktaildb.com/")
            .build()
    }

    //Llamo a la interface y devuelve un call
    fun getTrago(random:String): Call<Tragos>{
        return getRetrofit().create(ApiTragos::class.java).getTrago(random)
    }

}