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
import uca.ni.edu.peliculas.bd.repository.ClasificacionRepository
import uca.ni.edu.peliculas.bd.repository.GeneroRepository
import uca.ni.edu.peliculas.bd.repository.IdiomaRepository

class IdiomaViewModels(application:Application): AndroidViewModel(application) {
    val lista: LiveData<List<Idioma>>
    private val repository: IdiomaRepository

    init {
        val usuarioDao = dbPeliculas.getInstace(application).idiomaDao()
        repository = IdiomaRepository(usuarioDao)
        lista = repository.list
    }

    fun agregarUsuario(idioma: Idioma) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(idioma)
        }
    }

    fun actualizarUsuario(idioma: Idioma) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(idioma)
        }
    }

    fun eliminarUsuario(idioma: Idioma) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(idioma)
        }
    }
}