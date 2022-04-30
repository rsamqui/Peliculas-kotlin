package uca.ni.edu.peliculas.bd.repository

import androidx.lifecycle.LiveData
import uca.ni.edu.peliculas.bd.dao.ClasificacionDao
import uca.ni.edu.peliculas.bd.dao.GeneroDao
import uca.ni.edu.peliculas.bd.dao.IdiomaDao
import uca.ni.edu.peliculas.bd.dao.NacionalidadDao
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad

class NacionalidadRepository(private val dao: NacionalidadDao) {
    val list : LiveData<List<Nacionalidad>> = dao.getAllRealData()

    suspend fun add(nac: Nacionalidad){
        dao.insert(nac)
    }

    suspend fun update(nac: Nacionalidad){
        dao.update(nac)
    }
    suspend fun delete(nac: Nacionalidad){
        dao.delete(nac)
    }

}