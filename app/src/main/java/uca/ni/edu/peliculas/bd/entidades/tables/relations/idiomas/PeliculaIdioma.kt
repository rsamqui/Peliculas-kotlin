package uca.ni.edu.peliculas.bd.entidades.tables.relations.idiomas

import androidx.room.Entity

@Entity(primaryKeys = ["idIdioma","idPelicula"])
data class PeliculaIdioma(
    val idIdioma:Int,
    val idPelicula:Int
)
