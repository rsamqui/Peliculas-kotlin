package uca.ni.edu.peliculas.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.adapters.Nacionalidad_Adapter
import uca.ni.edu.peliculas.adapters.Pelicula_Adapter
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentPeliculaBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.viewmodels.NacionalidadViewModels
import uca.ni.edu.peliculas.bd.viewmodels.PeliculaViewModels


class PeliculaFragment : Fragment() {

    lateinit var binding: FragmentPeliculaBinding
    private lateinit var viewModel : PeliculaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPeliculaBinding.inflate(inflater,container, false)
        val adapter = Pelicula_Adapter()
        val recyclerView = binding.rvClasificacion

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[PeliculaViewModels::class.java]
        viewModel.listaPeliculas.observe(viewLifecycleOwner, Observer {
                nac->adapter.setDataPelicula(nac)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        binding.btnAdd.setOnClickListener{
            navController.navigate(R.id.pelicula_toAddP)
        }
    }
}