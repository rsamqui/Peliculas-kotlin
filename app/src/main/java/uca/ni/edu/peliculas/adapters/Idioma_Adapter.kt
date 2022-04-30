package uca.ni.edu.peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.databinding.ItemListBinding
import uca.ni.edu.peliculas.fragment.list.ClasificacionFragmentDirections
import uca.ni.edu.peliculas.fragment.list.IdiomaFragmentDirections

class Idioma_Adapter:RecyclerView.Adapter<Idioma_Adapter.IdiomaHolder>()  {
    var compra:List<Idioma> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdiomaHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return IdiomaHolder(binding)
    }

    override fun onBindViewHolder(holder: IdiomaHolder, position: Int) {
        holder.bind(
            compra[position]
        )
    }

    fun setData(cl: List<Idioma>) {
        this.compra = cl
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = compra.size

    class IdiomaHolder(val binding: ItemListBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(cl:Idioma){

            with(binding){
                itemTitle.text = cl.nombre
                itemSub.text =cl.id_Idioma.toString()

                llItem.setOnClickListener {

                    val action= IdiomaFragmentDirections.idiomaToUpdel(cl)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

}