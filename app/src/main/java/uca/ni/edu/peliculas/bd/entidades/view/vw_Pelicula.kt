package uca.ni.edu.peliculas.bd.entidades.view

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import kotlinx.android.parcel.Parcelize

@Parcelize
@DatabaseView("Select " +
                    "p.idPelicula," +
                    "p.titulo," +
                    "p.sinopsis," +
                    "p.tipoMetraje," +
                    "p.duracion," +
                    "p.idNacionalidad," +
                    "p.idClasificacion," +
                    "c.nombre as clasificacion," +
                    "n.nombre as nacionalidad " +
                    "from Pelicula as p " +
                    "inner join Nacionalidad as n on n.id_Nacionalidad=p.idNacionalidad " +
                    "inner join Clasificacion as c on c.idClasificacion=p.idClasificacion")
data class vw_Pelicula(
    val idPelicula: Int,
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "sinopsis")
    val sinopsis: String,
    @ColumnInfo(name = "tipoMetraje")
    val tipoMetraje: String,
    @ColumnInfo(name = "duracion")
    val duracion: String,
    @ColumnInfo(name = "idNacionalidad")
    val id_Nacionalidad: Int,
    @ColumnInfo(name = "idClasificacion")
    val id_Clasificacion: Int,
    @ColumnInfo(name = "clasificacion")
    val clasificacion: String,
    @ColumnInfo(name = "nacionalidad")
    val nacionalidad: String
): Parcelable
