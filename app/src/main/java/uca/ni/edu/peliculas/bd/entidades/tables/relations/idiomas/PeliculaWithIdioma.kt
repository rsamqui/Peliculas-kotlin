package uca.ni.edu.peliculas.bd.entidades.tables.relations.idiomas

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uca.ni.edu.peliculas.bd.entidades.tables.Idioma
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula

data class PeliculaWithIdioma(
    @Embedded val pelicula: Pelicula,
    @Relation(
        parentColumn = "idPelicula",
        entityColumn = "idIdioma",
        associateBy = Junction(PeliculaIdioma::class)
    )
    val idioma:List<Idioma>
)
