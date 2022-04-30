package uca.ni.edu.peliculas.bd.repository

import androidx.lifecycle.LiveData
import uca.ni.edu.peliculas.bd.dao.ClasificacionDao
import uca.ni.edu.peliculas.bd.dao.GeneroDao
import uca.ni.edu.peliculas.bd.dao.IdiomaDao
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma

class IdiomaRepository(private val dao: IdiomaDao) {
    val list : LiveData<List<Idioma>> = dao.getAllRealData()

    suspend fun add(idioma: Idioma){
        dao.insert(idioma)
    }

    suspend fun update(idioma: Idioma){
        dao.update(idioma)
    }
    suspend fun delete(idioma: Idioma){
        dao.delete(idioma)
    }

}