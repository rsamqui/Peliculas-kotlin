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
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels
import uca.ni.edu.peliculas.bd.viewmodels.NacionalidadViewModels
import uca.ni.edu.peliculas.databinding.FragmentUpDelGeneroBinding
import uca.ni.edu.peliculas.databinding.FragmentUpDelNacionalidadBinding


class UpDelNacionalidadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentUpDelNacionalidadBinding
    private val args by navArgs<UpDelNacionalidadFragmentArgs>()
    private lateinit var viewModel : NacionalidadViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentUpDelNacionalidadBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(NacionalidadViewModels::class.java)

        with(binding){
            etId.setText(args.currentNacionalidad.id_Nacionalidad.toString())
            etNombre.setText(args.currentNacionalidad.nombre)

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
            viewModel.eliminarUsuario(args.currentNacionalidad)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.nav_nacionalidad)
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
            val genero = Nacionalidad(binding.etId.text.toString().toInt(),name, true)

            viewModel.actualizarUsuario(genero)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_nacionalidad)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }
}