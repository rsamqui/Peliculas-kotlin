package uca.ni.edu.peliculas.bd.entidades.tables.relations

import androidx.room.Embedded
import androidx.room.Relation
import uca.ni.edu.peliculas.bd.entidades.tables.Clasificacion
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula

data class ClasificacionConPelicula(
    @Embedded val clasificacion: Clasificacion,
    @Relation(
        parentColumn = "idClasificacion",
        entityColumn = "idClasificacion"
    )
    val movies: List<Pelicula>

)
