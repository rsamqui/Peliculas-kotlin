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
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.viewmodels.ClasificacionViewModels
import uca.ni.edu.peliculas.bd.viewmodels.GeneroViewModels
import uca.ni.edu.peliculas.databinding.FragmentUpDelClasificacionBinding
import uca.ni.edu.peliculas.databinding.FragmentUpDelGeneroBinding

class UpDelClasificacionFragment : Fragment() {
    lateinit var binding: FragmentUpDelClasificacionBinding
    private val args by navArgs<UpDelClasificacionFragmentArgs>()

    private lateinit var viewModel : ClasificacionViewModels

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentUpDelClasificacionBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClasificacionViewModels::class.java)

        with(binding){
            etId.setText(args.currentClasif.idClasificacion.toString())
            etAbreviacion.setText(args.currentClasif.abreviacion)
            etNombre.setText(args.currentClasif.nombre)

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
            viewModel.eliminarUsuario(args.currentClasif)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.nav_clasif)
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
        val short = binding.etAbreviacion.text.toString()

        if (name.isNotEmpty() && short.isNotEmpty())
        {
            val cl = Clasificacion(binding.etId.text.toString().toInt(),short,name, true)

            viewModel.actualizarUsuario(cl)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_clasif)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }

}