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
import uca.ni.edu.peliculas.adapters.Genero_Adapter
import uca.ni.edu.peliculas.adapters.Nacionalidad_Adapter
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentNacionalidadBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels
import uca.ni.edu.peliculas.bd.viewmodels.NacionalidadViewModels


class NacionalidadFragment : Fragment() {
    private lateinit var binding: FragmentNacionalidadBinding
    private lateinit var viewModel : NacionalidadViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNacionalidadBinding.inflate(inflater,container, false)

        val adapter = Nacionalidad_Adapter()
        val recyclerView = binding.rvClasificacion

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this).get(NacionalidadViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer {
                nac->adapter.setData(nac)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        binding.btnAdd.setOnClickListener {
            navController.navigate(R.id.nacionalidad_to_addN)
        }
    }
}