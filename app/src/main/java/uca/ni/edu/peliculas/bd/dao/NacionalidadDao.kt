package uca.ni.edu.peliculas.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uca.ni.edu.peliculas.bd.entidades.tables.*
import uca.ni.edu.peliculas.bd.entidades.tables.relations.genero.PeliculaGenero
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula

@Dao
interface NacionalidadDao {

    //NACIONALIDAD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Nacionalidad)

    @Query("SELECT * FROM Nacionalidad")
    fun getAllRealData(): LiveData<List<Nacionalidad>>

    @Query("Select * from Nacionalidad")
    suspend fun getAllNacionalidad(): List<Nacionalidad>

    @Query("SELECT * FROM Nacionalidad where id_Nacionalidad= :id")
    suspend fun getByIdNacionalidad(id: Int): Nacionalidad

    @Update
    fun update(usuario: Nacionalidad)

    @Delete
    fun delete(usuario: Nacionalidad)


}