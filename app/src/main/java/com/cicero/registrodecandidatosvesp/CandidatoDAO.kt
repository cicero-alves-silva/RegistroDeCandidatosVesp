package com.cicero.registrodecandidatosvesp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CandidatoDAO {
    @Insert
    fun inserir(candidato: Candidato)
    @Update
    fun atualizar(candidato: Candidato)
    @Delete
    fun deletar(candidato: Candidato)
    @Query("SELECT * FROM candidato")
    fun pesquisarTodos(): MutableList<Candidato>
}