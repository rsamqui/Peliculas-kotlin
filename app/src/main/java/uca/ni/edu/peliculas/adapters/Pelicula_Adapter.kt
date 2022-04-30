package uca.ni.edu.peliculas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.databinding.ItemPeliculaBinding
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula
import uca.ni.edu.peliculas.fragment.list.NacionalidadFragmentDirections
import uca.ni.edu.peliculas.fragment.list.PeliculaFragmentDirections

class Pelicula_Adapter:RecyclerView.Adapter<Pelicula_Adapter.PeliculaHolder>()  {
    var lista:List<vw_Pelicula> = emptyList()
    var listaC:List<Clasificacion> = emptyList()
    var listaN:List<Nacionalidad> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeliculaHolder {
        val binding = ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PeliculaHolder(binding)
    }

    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        holder.bind(lista[position])
    }

    fun setDataPelicula(cl: List<vw_Pelicula>) {
        this.lista = cl
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lista.size

    class PeliculaHolder(val binding:ItemPeliculaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pelicula: vw_Pelicula){
            with(binding){
                titulo.text = pelicula.titulo
                tipoMetraje.text = pelicula.tipoMetraje
                nacionalidad.text = pelicula.nacionalidad
                clasificacion.text = pelicula.clasificacion
                duracion.text = pelicula.duracion
                sinopsis.text = pelicula.sinopsis

                llItem.setOnClickListener {

                    val action= PeliculaFragmentDirections.peliculaToUpdel(pelicula)
                    it.findNavController().navigate(action)
                }

            }
        }
    }

}