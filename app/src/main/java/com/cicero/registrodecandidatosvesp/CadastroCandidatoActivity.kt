package com.cicero.registrodecandidatosvesp

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.cicero.registrodecandidatosvesp.databinding.ActivityCadastroCandidatoBinding

class CadastroCandidatoActivity : AppCompatActivity() {
    private lateinit var candidatoDAO: CandidatoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCadastroCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cargos = listOf("VEREADOR(A)", "VICE-PREFEITO(A)", "PREFEITO(A)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cargos)
        binding.tietCargoCandidato.setAdapter(adapter)

        candidatoDAO = BancoDeDados.getInstance(this).candidatoDAO()

        binding.btSalvar.setOnClickListener { view ->
            val nomeUrna = binding.tietNomeUrna.text.toString()
            val numeroCandidato = binding.tietNumeroCandidato.text.toString()
            val cargoCandidato = binding.tietCargoCandidato.text.toString()
            val nomePartido = binding.tietNomePartido.text.toString()

            if (nomeUrna.isEmpty()) {
                binding.tilNomeUrna.error = "Digite o nome do(a) candidato(a)"
            } else {
                binding.tilNomeUrna.error = ""
            }

            if (numeroCandidato.isEmpty()) {
                binding.tilNumeroCandidato.error = "Digite o número do(a) candidato(a)"
            } else {
                binding.tilNumeroCandidato.error = ""
            }

            if (cargoCandidato.isEmpty()) {
                binding.tilCargoCandidato.error = "Digite o cargo do(a) candidato(a)"
            } else {
                binding.tilCargoCandidato.error = ""
            }

            if (nomePartido.isEmpty()) {
                binding.tilNomePartido.error = "Digite o nome do partido"
            } else {
                binding.tilNomePartido.error = ""
            }

            if (nomeUrna.isNotEmpty() && numeroCandidato.isNotEmpty()
                && cargoCandidato.isNotEmpty() && nomePartido.isNotEmpty()
            ) {
                //salvar no BD
                val novoCandidado =
                    Candidato(
                        0,
                        nomeUrna,
                        numeroCandidato.toInt(),
                        cargoCandidato,
                        nomePartido)
                candidatoDAO.inserir(novoCandidado)
                finish()
            }
        }
    }
}