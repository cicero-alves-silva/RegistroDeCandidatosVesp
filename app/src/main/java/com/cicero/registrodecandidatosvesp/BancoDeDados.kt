package com.cicero.registrodecandidatosvesp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Candidato::class], version = 1)
abstract class BancoDeDados : RoomDatabase() {
    abstract fun candidatoDAO(): CandidatoDAO

    companion object {
        @Volatile
        private var INSTANCE: BancoDeDados? = null

        fun getInstance(context: Context): BancoDeDados {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BancoDeDados::class.java, "candidatos_database"
                )
                    .allowMainThreadQueries()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
