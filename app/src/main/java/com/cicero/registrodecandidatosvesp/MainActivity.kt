package com.cicero.registrodecandidatosvesp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cicero.registrodecandidatosvesp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener { view ->
            //Abrir tela cadastro
            val intent = Intent(this, CadastroCandidatoActivity::class.java)
            startActivity(intent)
        }
    }
}