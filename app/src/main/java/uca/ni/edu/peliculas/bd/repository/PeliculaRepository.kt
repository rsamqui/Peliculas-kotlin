package uca.ni.edu.peliculas.bd.repository

import androidx.lifecycle.LiveData
import uca.ni.edu.peliculas.bd.dao.*
import uca.ni.edu.peliculas.bd.entidades.tables.*
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula

class PeliculaRepository
    (
    private val daoP: PeliculaDao,
    private val daoC:ClasificacionDao,
    private val daoN:NacionalidadDao
    ) {

    val listAll : LiveData<List<vw_Pelicula>> = daoP.getAllRealData()

    suspend fun clasificacion(): List<Clasificacion>{
        return daoC.getAllClasificacion()
    }

    suspend fun nacionalidad():List<Nacionalidad> {
        return daoN.getAllNacionalidad()
    }

    suspend fun add(nac: Pelicula){
        daoP.insert(nac)
    }

    suspend fun update(nac: Pelicula){
        daoP.update(nac)
    }
    suspend fun delete(nac: Pelicula){
        daoP.delete(nac)
    }

    suspend fun getNacByName(nac: String):Int{
        return daoP.getByStringNacionalidad(nac)
    }

    suspend fun getClassByName(nac: String){
        daoP.getByStringClasificacion(nac)
    }

}