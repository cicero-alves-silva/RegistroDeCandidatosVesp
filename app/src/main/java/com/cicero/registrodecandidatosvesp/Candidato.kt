package com.cicero.registrodecandidatosvesp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Candidato (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nomeNaUrna: String,
    var numeroCandidato: Int,
    var cargoCandidato: String,
    var nomePartido: String
) : Parcelable