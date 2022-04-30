package uca.ni.edu.peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.databinding.ItemListBinding
import uca.ni.edu.peliculas.fragment.list.GeneroFragmentDirections

class Genero_Adapter:RecyclerView.Adapter<Genero_Adapter.GeneroHolder>()  {
    var compra:List<Genero> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneroHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return GeneroHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneroHolder, position: Int) {
        holder.bind(
            compra[position]
        )
    }

    fun setData(users: List<Genero>) {
        this.compra = users
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = compra.size

    inner class GeneroHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genero:Genero){

            with(binding){
                itemTitle.text = genero.nombre
                itemSub.text ="ID = ${ genero.id_Genero}"

                llItem.setOnClickListener {
                    val action=GeneroFragmentDirections.generoToUpdel(genero)
                    it.findNavController().navigate(action)
                }
            }
        }

    }

}