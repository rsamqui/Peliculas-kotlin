package uca.ni.edu.peliculas.fragment.update_delete

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.viewmodels.IdiomaViewModels
import uca.ni.edu.peliculas.bd.viewmodels.NacionalidadViewModels
import uca.ni.edu.peliculas.databinding.FragmentUpDelIdiomaBinding
import uca.ni.edu.peliculas.databinding.FragmentUpDelNacionalidadBinding


class UpDelIdiomaFragment : Fragment() {
    lateinit var binding: FragmentUpDelIdiomaBinding
    private val args by navArgs<UpDelIdiomaFragmentArgs>()
    private lateinit var viewModel : IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentUpDelIdiomaBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[IdiomaViewModels::class.java]

        with(binding){
            etId.setText(args.currentIdioma.id_Idioma.toString())
            etNombre.setText(args.currentIdioma.nombre)

            btnUpdate.setOnClickListener {
                guardarCambios()
            }

            btnDelete.setOnClickListener {
                eliminar()
            }
        }

        return binding.root
    }

    private fun eliminar() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarUsuario(args.currentIdioma)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.nav_idioma)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }

        alerta.setTitle("Eliminando todos los registro")
        alerta.setMessage("¿Esta seguro de eliminar el registro permanentemente?")
        alerta.create().show()


    }

    private fun guardarCambios() {
        val name = binding.etNombre.text.toString()

        if(name.isNotEmpty())
        {
            val genero = Idioma(binding.etId.text.toString().toInt(),name, true)

            viewModel.actualizarUsuario(genero)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_idioma)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }
}