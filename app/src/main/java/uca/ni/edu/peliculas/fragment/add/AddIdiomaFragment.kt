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
import uca.ni.edu.peliculas.databinding.FragmentAddIdiomaBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.viewmodels.ClasificacionViewModels
import uca.ni.edu.peliculas.bd.viewmodels.IdiomaViewModels

class AddIdiomaFragment : Fragment() {

    private lateinit var binding:FragmentAddIdiomaBinding
    private lateinit var viewModel : IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddIdiomaBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(IdiomaViewModels::class.java)

        binding.btnNew.setOnClickListener {
            guardar()
        }

        return binding.root
    }

    private fun guardar() {
        with(binding){
            val name = etNombre.text.toString()

            if(name.isNotEmpty())
            {
                val cl = Idioma(0,name, true)

                viewModel.agregarUsuario(cl)

                Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.nav_idioma)
            }
            else
            {
                Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

}