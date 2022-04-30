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
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.repository.ClasificacionRepository
import uca.ni.edu.peliculas.bd.repository.GeneroRepository
import uca.ni.edu.peliculas.bd.repository.IdiomaRepository
import uca.ni.edu.peliculas.bd.repository.NacionalidadRepository

class NacionalidadViewModels(application:Application): AndroidViewModel(application) {
    val lista: LiveData<List<Nacionalidad>>
    private val repository: NacionalidadRepository

    init {
        val usuarioDao = dbPeliculas.getInstace(application).nacionalidadDao()
        repository = NacionalidadRepository(usuarioDao)
        lista = repository.list
    }

    fun agregarUsuario(nac: Nacionalidad) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(nac)
        }
    }

    fun actualizarUsuario(nac: Nacionalidad) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(nac)
        }
    }

    fun eliminarUsuario(nac: Nacionalidad) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(nac)
        }
    }
}