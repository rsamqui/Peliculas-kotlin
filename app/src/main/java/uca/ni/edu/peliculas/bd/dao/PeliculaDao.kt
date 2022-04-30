package uca.ni.edu.peliculas.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uca.ni.edu.peliculas.bd.entidades.tables.*
import uca.ni.edu.peliculas.bd.entidades.tables.relations.genero.PeliculaGenero
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula

@Dao
interface PeliculaDao {
    //VW_PELICULA
    @Query("SELECT * FROM vw_Pelicula p")
    fun getAllRealData(): LiveData<List<vw_Pelicula>>

    //PELICULA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Pelicula)

    @Update
    fun update(usuario: Pelicula)

    @Delete
    fun delete(usuario: Pelicula)


    //SPINERS
    @Query("SELECT id_Nacionalidad FROM Nacionalidad where nombre= :nombre")
    suspend fun getByStringNacionalidad(nombre: String): Int

    @Query("SELECT idClasificacion FROM Clasificacion where abreviacion= :abreviacion")
    suspend fun getByStringClasificacion(abreviacion: String): Int

}