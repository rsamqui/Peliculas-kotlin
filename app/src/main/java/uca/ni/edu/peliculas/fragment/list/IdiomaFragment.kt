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
import uca.ni.edu.peliculas.adapters.Idioma_Adapter
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentIdiomaBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels
import uca.ni.edu.peliculas.bd.viewmodels.IdiomaViewModels


class IdiomaFragment : Fragment() {
    lateinit var binding: FragmentIdiomaBinding
    private lateinit var viewModel : IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdiomaBinding.inflate(inflater,container, false)

        val adapter = Idioma_Adapter()
        val recyclerView = binding.rvClasificacion

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this).get(IdiomaViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer {
                genero->adapter.setData(genero)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        binding.btnAdd.setOnClickListener {
            navController.navigate(R.id.idioma_to_addI)
        }
    }
}