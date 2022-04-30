package uca.ni.edu.peliculas.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uca.ni.edu.peliculas.bd.entidades.tables.*

@Dao
interface ClasificacionDao {
    //CLASIFICACION
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Clasificacion)

    @Query("Select * from Clasificacion")
    suspend fun getAllClasificacion(): List<Clasificacion>

    @Query("SELECT * FROM Clasificacion where idClasificacion= :id")
    suspend fun getByIdClasificacion(id: Int): Clasificacion

    @Update
    fun update(usuario: Clasificacion)

    @Delete
    fun delete(usuario: Clasificacion)

    @Query("SELECT * FROM Clasificacion")
    fun getAllRealData(): LiveData<List<Clasificacion>>

}