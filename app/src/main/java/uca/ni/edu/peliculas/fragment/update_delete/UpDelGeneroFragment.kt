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
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels
import uca.ni.edu.peliculas.databinding.FragmentUpDelGeneroBinding


class UpDelGeneroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentUpDelGeneroBinding
    private val args by navArgs<UpDelGeneroFragmentArgs>()

    private lateinit var viewModel : GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUpDelGeneroBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(GeneroViewModels::class.java)

        with(binding){
            etId.setText(args.currentGenero.id_Genero.toString())
            etNombre.setText(args.currentGenero.nombre)

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
            viewModel.eliminarUsuario(args.currentGenero)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.nav_genero)
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
            val genero = Genero(binding.etId.text.toString().toInt(),name, true)

            viewModel.actualizarUsuario(genero)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_genero)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }

}