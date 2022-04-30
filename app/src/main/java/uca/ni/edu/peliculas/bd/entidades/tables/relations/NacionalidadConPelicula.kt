package uca.ni.edu.peliculas.bd.entidades.tables.relations

import androidx.room.Embedded
import androidx.room.Relation
import uca.ni.edu.peliculas.bd.entidades.tables.Nacionalidad
import uca.ni.edu.peliculas.bd.entidades.tables.Pelicula

data class NacionalidadConPelicula(
    @Embedded val nacionalidad: Nacionalidad,
    @Relation(
        parentColumn = "id_Nacionalidad",
        entityColumn = "id_Nacionalidad"
    )
    val movies: List<Pelicula>

)
