package uca.ni.edu.peliculas.bd.repository

import androidx.lifecycle.LiveData
import uca.ni.edu.peliculas.bd.dao.GeneroDao
import uca.ni.edu.peliculas.bd.entidades.tables.Genero

class GeneroRepository(private val dao:GeneroDao) {
    val listado : LiveData<List<Genero>> = dao.getAllRealData()

    suspend fun add(genero: Genero){
        dao.insert(genero)
    }

    suspend fun update(genero: Genero){
        dao.update(genero)
    }
    suspend fun delete(genero: Genero){
        dao.delete(genero)
    }

}