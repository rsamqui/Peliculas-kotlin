package uca.ni.edu.peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.databinding.ItemListBinding
import uca.ni.edu.peliculas.fragment.list.IdiomaFragmentDirections
import uca.ni.edu.peliculas.fragment.list.NacionalidadFragmentDirections

class Nacionalidad_Adapter :RecyclerView.Adapter<Nacionalidad_Adapter.NacionalidadHolder>()  {
    var compra:List<Nacionalidad> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NacionalidadHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NacionalidadHolder(binding)
    }

    override fun onBindViewHolder(holder: NacionalidadHolder, position: Int) {
        holder.bind(
            compra[position]
        )
    }

    fun setData(cl: List<Nacionalidad>) {
        this.compra = cl
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = compra.size

    class NacionalidadHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cl:Nacionalidad){

            with(binding){
                itemTitle.text = cl.nombre
                itemSub.text =cl.id_Nacionalidad.toString()

                llItem.setOnClickListener {

                    val action= NacionalidadFragmentDirections.nacionalidadToUpdel(cl)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

}