package uca.ni.edu.peliculas.bd.repository

import androidx.lifecycle.LiveData
import uca.ni.edu.peliculas.bd.dao.ClasificacionDao
import uca.ni.edu.peliculas.bd.dao.GeneroDao
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero

class ClasificacionRepository(private val dao:ClasificacionDao) {
    val list : LiveData<List<Clasificacion>> = dao.getAllRealData()

    suspend fun add(clasif: Clasificacion){
        dao.insert(clasif)
    }

    suspend fun update(clasif: Clasificacion){
        dao.update(clasif)
    }
    suspend fun delete(clasif: Clasificacion){
        dao.delete(clasif)
    }

}