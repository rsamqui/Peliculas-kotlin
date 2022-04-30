package uca.ni.edu.peliculas.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.*
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula
import uca.ni.edu.peliculas.bd.repository.*

class PeliculaViewModels(application:Application): AndroidViewModel(application) {
    val listaPeliculas: LiveData<List<vw_Pelicula>>
    lateinit var listaClasificacion: List<Clasificacion>
    var listaNacionalidad: List<Nacionalidad> = emptyList()

    private val repository: PeliculaRepository

    init
    {
        val nacionalidadDao = dbPeliculas.getInstace(application).nacionalidadDao()
        val clasificacionDao = dbPeliculas.getInstace(application).clasificacionDAO()
        val peliculaDao = dbPeliculas.getInstace(application).peliculaDao()


        repository = PeliculaRepository(peliculaDao,clasificacionDao,nacionalidadDao)
        listaPeliculas = repository.listAll

        viewModelScope.launch (Dispatchers.IO){
            listaNacionalidad = repository.nacionalidad()
        }

    }



    fun initClas(): List<Clasificacion>{
        CoroutineScope(Dispatchers.Main).launch {
            listaClasificacion = repository.clasificacion()
        }

        return listaClasificacion
    }

    fun agregarUsuario(nac: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(nac)
        }
    }

    fun actualizarUsuario(nac: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(nac)
        }
    }

    fun eliminarUsuario(nac: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(nac)
        }
    }

    fun getNacbyName(nac:String):Int{
        var x= 0
        viewModelScope.launch(Dispatchers.IO){
            x = repository.getNacByName(nac).toInt()
        }
        return x
    }

    fun getClassByName(nac:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.getClassByName(nac)
        }
    }
}