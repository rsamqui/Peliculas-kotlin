package uca.ni.edu.peliculas.bd.entidades.tables.relations.genero

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uca.ni.edu.peliculas.bd.entidades.tables.Genero
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula

data class GeneroWithPeliculas(
    @Embedded val genero: Genero,
    @Relation(
        parentColumn = "idGenero",
        entityColumn = "idPelicula",
        associateBy = Junction(PeliculaGenero::class)
    )
    val peliculas:List<Pelicula>
)
