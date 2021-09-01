package edu.neo.tpfinal.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Comida
import org.w3c.dom.Text

class MyAdapterInformes(private val comidasList: MutableList<Comida>) :

    RecyclerView.Adapter<MyAdapterInformes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_personas, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comidasList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tipoComida.text = comidasList[position].tipoComidaDiaria.toString()
        holder.fecha.text = comidasList[position].fechaIngresada.toString()
        holder.comidaPrincipal.text = comidasList[position].comidaPrincipal.toString()
        holder.comidaSecundario.text = comidasList[position].comidaSecundaria.toString()
        holder.bebida.text = comidasList[position].bebida.toString()
    }


    //Creo clase Viewholder y mapeo los datos
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tipoComida:TextView
        var fecha:TextView
        var comidaPrincipal:TextView
        var comidaSecundario:TextView
        var bebida:TextView

        init {
            tipoComida= view.findViewById(R.id.idTipoComida)
            fecha = view.findViewById(R.id.idFecha)
            comidaPrincipal = view.findViewById(R.id.idPlatoPrincipal)
            comidaSecundario = view.findViewById(R.id.idPlatoSecundario)
            bebida = view.findViewById(R.id.id_Bebida)
        }
    }
}

