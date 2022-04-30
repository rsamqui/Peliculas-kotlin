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
import uca.ni.edu.peliculas.databinding.FragmentAddGeneroBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels

class AddGeneroFragment : Fragment() {

    private lateinit var binding:FragmentAddGeneroBinding
    private lateinit var viewModel : GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddGeneroBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(GeneroViewModels::class.java)

        binding.btnNew.setOnClickListener {
            guardar()
        }
        return binding.root
    }

    private fun guardar() {
        val name = binding.etNombre.text.toString()

        if(name.isNotEmpty())
        {
            val genero = Genero(0,name, true)

            viewModel.agregarUsuario(genero)
            Toast.makeText(requireContext(), "Registro guardado",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.addG_to_Genero)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }

    }


}