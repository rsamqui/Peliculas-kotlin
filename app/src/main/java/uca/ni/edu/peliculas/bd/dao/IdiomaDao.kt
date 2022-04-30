package uca.ni.edu.peliculas.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uca.ni.edu.peliculas.bd.entidades.tables.*
import uca.ni.edu.peliculas.bd.entidades.tables.relations.genero.PeliculaGenero
import uca.ni.edu.peliculas.bd.entidades.view.vw_Pelicula

@Dao
interface IdiomaDao {

    //IDIOMA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Idioma)

    @Query("SELECT * FROM Idioma")
    fun getAllRealData(): LiveData<List<Idioma>>

    @Query("Select * from Idioma")
    suspend fun getAllIdioma(): List<Idioma>

    @Query("SELECT * FROM Idioma where id_Idioma= :id")
    suspend fun getByIdIdioma(id: Int): Idioma

    @Update
    fun update(usuario: Idioma)

    @Delete
    fun delete(usuario: Idioma)

}