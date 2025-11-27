package com.cicero.registrodecandidatosvesp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Candidato (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nomeNaUrna: String,
    var numeroCandidato: Int,
    var cargoCandidato: String,
    var nomePartido: String
)