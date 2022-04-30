package uca.ni.edu.peliculas.fragment.update_delete

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.bd.dao.ClasificacionDao
import uca.ni.edu.peliculas.bd.dao.NacionalidadDao
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula
import uca.ni.edu.peliculas.bd.viewmodels.PeliculaViewModels
import uca.ni.edu.peliculas.databinding.FragmentAddPeliculaBinding
import uca.ni.edu.peliculas.databinding.FragmentUpDelPeliculaBinding


class UpDelPeliculaFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentUpDelPeliculaBinding
    private val args by navArgs<UpDelPeliculaFragmentArgs>()
    private lateinit var viewModel : PeliculaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpDelPeliculaBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(this)[PeliculaViewModels::class.java]

        initSpinner(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            etId.setText(args.currentPelicula.idPelicula.toString())
            etDuracion.setText(args.currentPelicula.duracion)
            etSinopsis.setText(args.currentPelicula.sinopsis)
            etTipoMetraje.setText(args.currentPelicula.tipoMetraje)
            etTitulo.setText(args.currentPelicula.titulo)

            btnUpdate.setOnClickListener {
                guardar()
            }

            btnDelete.setOnClickListener {
                eliminar()
            }
        }

    }


    private fun eliminar() {
        val vw = args.currentPelicula
        val peli = Pelicula(vw.idPelicula,vw.id_Clasificacion,vw.duracion,vw.id_Nacionalidad,vw.sinopsis,vw.tipoMetraje,vw.titulo)

        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarUsuario(peli)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.nav_pelicula)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }

        alerta.setTitle("Eliminando los registro")
        alerta.setMessage("¿Esta seguro de eliminar el registro permanentemente?")
        alerta.create().show()

5
    }

    private fun guardar() {
        val clasificacion = binding.spClasificacion.selectedItem.toString()
        val nacionalidad = binding.spNacionalidad.selectedItem.toString()
        val titulo = binding.etTitulo.text.toString()
        val sinopsis = binding.etSinopsis.text.toString()
        val duracion = binding.etDuracion.text.toString()
        val tipoMetraje = binding.etTipoMetraje.text.toString()
        val id = binding.etId.text.toString().toInt()

        if(clasificacion != "Seleccione..")
        {
            if (nacionalidad !="Seleccione..")
            {
                if (titulo.isNotEmpty() && sinopsis.isNotEmpty() && duracion.isNotEmpty() && tipoMetraje.isNotEmpty())
                {
                    val db: dbPeliculas = dbPeliculas.getInstace(requireContext().applicationContext)
                    val daoP: PeliculaDao = db.peliculaDao()

                    CoroutineScope(Dispatchers.Main).launch {
                        val idN = daoP.getByStringNacionalidad(nacionalidad)
                        val idC = daoP.getByStringClasificacion(clasificacion)

                        val peli = Pelicula(id,idC,duracion,idN,sinopsis,tipoMetraje,titulo)
                        viewModel.actualizarUsuario(peli)

                        Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.nav_pelicula)
                    }

                }
                else
                {
                    Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Debe Seleccionar una Nacionalidad", Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            Toast.makeText(requireContext(), "Debe Seleccionar una Clasificacion", Toast.LENGTH_LONG).show()
        }

    }

    private fun initSpinner(context: Context){
        val db: dbPeliculas = dbPeliculas.getInstace(context)
        val daoC: ClasificacionDao = db.clasificacionDAO()
        val daoN: NacionalidadDao = db.nacionalidadDao()

        var arrayNac:ArrayList<String> = arrayListOf("Seleccione..")
        var arrayAbr:ArrayList<String> = arrayListOf("Seleccione..")

        CoroutineScope(Dispatchers.Main).launch {
            val listaC: List<Clasificacion> =daoC.getAllClasificacion()
            val listaN: List<Nacionalidad> = daoN.getAllNacionalidad()

            listaN.forEach {
                arrayNac.add(it.nombre)
            }

            listaC.forEach {
                arrayAbr.add(it.abreviacion)
            }
        }

        val adapterN: ArrayAdapter<String> = ArrayAdapter<String>(context,R.layout.sp_item, arrayNac)
        binding.spNacionalidad.adapter = adapterN

        val adapterC: ArrayAdapter<String> = ArrayAdapter<String>(context,R.layout.sp_item, arrayAbr)
        binding.spClasificacion.adapter = adapterC

    }

}