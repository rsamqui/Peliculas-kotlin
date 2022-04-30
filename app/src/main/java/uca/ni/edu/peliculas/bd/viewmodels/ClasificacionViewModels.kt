package uca.ni.edu.peliculas.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.repository.ClasificacionRepository
import uca.ni.edu.peliculas.bd.repository.GeneroRepository

class ClasificacionViewModels(application:Application): AndroidViewModel(application) {
    val lista: LiveData<List<Clasificacion>>
    private val repository: ClasificacionRepository

    init {
        val usuarioDao = dbPeliculas.getInstace(application).clasificacionDAO()
        repository = ClasificacionRepository(usuarioDao)
        lista = repository.list
    }

    fun agregarUsuario(clasif: Clasificacion) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(clasif)
        }
    }

    fun actualizarUsuario(clasif: Clasificacion) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(clasif)
        }
    }

    fun eliminarUsuario(clasif: Clasificacion) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(clasif)
        }
    }
}