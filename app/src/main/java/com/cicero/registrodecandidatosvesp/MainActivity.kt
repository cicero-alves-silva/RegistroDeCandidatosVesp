package com.cicero.registrodecandidatosvesp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cicero.registrodecandidatosvesp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var candidatoDAO: CandidatoDAO
    private lateinit var candidatoAdapter: CandidatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener { view ->
            //Abrir tela cadastro
            val intent = Intent(this, CadastroCandidatoActivity::class.java)
            startActivity(intent)
        }

        candidatoDAO = BancoDeDados.getInstance(this).candidatoDAO()
        candidatoAdapter = CandidatoAdapter(candidatoDAO)
        binding.rvCandidatos.adapter = candidatoAdapter
        binding.rvCandidatos.layoutManager = LinearLayoutManager(this)
    }
}