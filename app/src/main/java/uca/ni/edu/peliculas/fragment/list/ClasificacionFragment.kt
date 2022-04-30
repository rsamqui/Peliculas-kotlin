package uca.ni.edu.peliculas.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.adapters.Clasificacion_Adapter
import uca.ni.edu.peliculas.adapters.Genero_Adapter
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentClasificacionBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.viewmodels.ClasificacionViewModels
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels


class ClasificacionFragment : Fragment() {

    lateinit var binding: FragmentClasificacionBinding
    private lateinit var viewModel : ClasificacionViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClasificacionBinding.inflate(inflater,container, false)
        val adapter = Clasificacion_Adapter()
        val recyclerView = binding.rvClasificacion

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this).get(ClasificacionViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer {
                clasif->adapter.setData(clasif)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.btnAdd.setOnClickListener {
            navController.navigate(R.id.clasificacion_to_addC)
        }
    }

}