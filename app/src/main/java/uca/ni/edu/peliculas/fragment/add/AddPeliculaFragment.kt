package uca.ni.edu.peliculas.fragment.add

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_pelicula.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.R
import uca.ni.edu.peliculas.adapters.Pelicula_Adapter
import uca.ni.edu.peliculas.bd.dao.ClasificacionDao
import uca.ni.edu.peliculas.bd.dao.NacionalidadDao
import uca.ni.edu.peliculas.bd.dao.PeliculaDao
import uca.ni.edu.peliculas.databinding.FragmentAddPeliculaBinding
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula
import uca.ni.edu.peliculas.bd.viewmodels.IdiomaViewModels
import uca.ni.edu.peliculas.bd.viewmodels.PeliculaViewModels


class AddPeliculaFragment : Fragment() {

    lateinit var binding:FragmentAddPeliculaBinding
    private lateinit var viewModel : PeliculaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPeliculaBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(this)[PeliculaViewModels::class.java]

        initSpinner(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNew.setOnClickListener {
            guardar()
        }
    }

    private fun guardar() {
        val clasificacion = binding.spClasificacion.selectedItem.toString()
        val nacionalidad = binding.spNacionalidad.selectedItem.toString()
        val titulo = binding.etTitulo.text.toString()
        val sinopsis = binding.etSinopsis.text.toString()
        val duracion = binding.etDuracion.text.toString()
        val tipoMetraje = binding.etTipoMetraje.text.toString()

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

                        val peli = Pelicula(0,idC,duracion,idN,sinopsis,tipoMetraje,titulo)
                        viewModel.agregarUsuario(peli)

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

    private fun initSpinner(context:Context){
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