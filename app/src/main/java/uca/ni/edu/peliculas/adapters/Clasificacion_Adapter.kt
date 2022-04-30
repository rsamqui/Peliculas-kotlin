package uca.ni.edu.peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.databinding.ItemListBinding
import uca.ni.edu.peliculas.fragment.list.ClasificacionFragmentDirections

class Clasificacion_Adapter:RecyclerView.Adapter<Clasificacion_Adapter.ClasificacionHolder>()  {
    var compra:List<Clasificacion> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClasificacionHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ClasificacionHolder(binding)
    }

    override fun onBindViewHolder(holder: ClasificacionHolder, position: Int) {
        holder.bind(
            compra[position]
        )
    }

    override fun getItemCount(): Int = compra.size

    fun setData(cl: List<Clasificacion>) {
        this.compra = cl
        notifyDataSetChanged()
    }

    class ClasificacionHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cl:Clasificacion){

            with(binding){
                itemTitle.text = cl.abreviacion
                itemSub.text =cl.nombre

                llItem.setOnClickListener {

                    val action= ClasificacionFragmentDirections.clasificacionToUpdel(cl)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

}