package uca.ni.edu.peliculas.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uca.ni.edu.peliculas.bd.entidades.tables.*

@Dao
interface GeneroDao {

    //GENERO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Genero)

    @Query("SELECT * FROM Genero")
    fun getAllRealData(): LiveData<List<Genero>>

    @Query("Select * from Genero")
    suspend fun getAll(): List<Genero>

    @Query("SELECT * FROM Genero where id_Genero= :id")
    suspend fun getByIdGenero(id: Int): Genero

    @Update
    fun update(usuario: Genero)

    @Delete
    fun delete(usuario: Genero)


}