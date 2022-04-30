package uca.ni.edu.peliculas.bd.entidades.tables

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="Nacionalidad")
data class Nacionalidad (
    @PrimaryKey(autoGenerate = true)
    val id_Nacionalidad:Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "activo")
    val activo:Boolean
        ): Parcelable