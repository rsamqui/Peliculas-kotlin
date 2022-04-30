package uca.ni.edu.peliculas.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uca.ni.edu.peliculas.bd.dao.dbPeliculas
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.repository.GeneroRepository

class GeneroViewModels(application:Application): AndroidViewModel(application) {
    val lista: LiveData<List<Genero>>
    private val repository: GeneroRepository

    init {
        val usuarioDao = dbPeliculas.getInstace(application).generoDAO()
        repository = GeneroRepository(usuarioDao)
        lista = repository.listado
    }

    fun agregarUsuario(usuario: Genero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(usuario)
        }
    }

    fun actualizarUsuario(usuario: Genero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(usuario)
        }
    }

    fun eliminarUsuario(usuario: Genero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(usuario)
        }
    }
}