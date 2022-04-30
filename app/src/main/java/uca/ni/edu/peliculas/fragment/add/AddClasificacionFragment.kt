package uca.ni.edu.peliculas.fragment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentAddClasificacionBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.viewmodels.ClasificacionViewModels
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels

class AddClasificacionFragment : Fragment() {

    private lateinit var binding:FragmentAddClasificacionBinding
    private lateinit var viewModel : ClasificacionViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddClasificacionBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this)[ClasificacionViewModels::class.java]

        binding.btnNew.setOnClickListener {
            guardar()
        }

        return binding.root
    }

    private fun guardar() {
        val name = binding.etNombre.text.toString()
        val short = binding.etAbreviacion.text.toString()

        if (name.isNotEmpty() && short.isNotEmpty())
        {
            val cl = Clasificacion(0,short,name, true)

            viewModel.agregarUsuario(cl)

            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.addC_to_clasificacion)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }

}