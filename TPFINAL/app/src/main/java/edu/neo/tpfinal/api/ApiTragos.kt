package edu.neo.tpfinal.api

import edu.neo.tpfinal.model.Drink
import edu.neo.tpfinal.model.Tragos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiTragos {


    //Seteo el path que le falta al url
    @GET("api/json/v1/1/{random}")
        fun getTrago(@Path("random")random:String): Call<Tragos>

}