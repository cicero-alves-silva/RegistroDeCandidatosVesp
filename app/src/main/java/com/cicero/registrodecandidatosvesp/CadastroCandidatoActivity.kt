package com.cicero.registrodecandidatosvesp

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.cicero.registrodecandidatosvesp.databinding.ActivityCadastroCandidatoBinding
import kotlin.toString

class CadastroCandidatoActivity : AppCompatActivity() {
    private lateinit var candidatoDAO: CandidatoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCadastroCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cargos = listOf("VEREADOR(A)", "VICE-PREFEITO(A)", "PREFEITO(A)")
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, cargos)
        binding.tietCargoCandidato.setAdapter(adapter)

        candidatoDAO = BancoDeDados.getInstance(this).candidatoDAO()

        val bundle = intent.extras
        var candidato: Candidato? = null
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                candidato = bundle.getParcelable("CANDIDATO", Candidato::class.java)
            } else {
                candidato = bundle.getParcelable("CANDIDATO")
            }
            if (candidato != null) {
                binding.tietNomeUrna.setText(candidato.nomeNaUrna)
                binding.tietNumeroCandidato.setText(candidato.numeroCandidato.toString())
                binding.tietCargoCandidato.setText(candidato.cargoCandidato, false)
                binding.tietNomePartido.setText(candidato.nomePartido)
            }
        }

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
                if (candidato == null) { //salvar no BD
                    val novoCandidado =
                        Candidato(0,
                            nomeUrna,
                            numeroCandidato.toInt(),
                            cargoCandidato,
                            nomePartido
                        )
                    candidatoDAO.inserir(novoCandidado)
                } else { //alterar no BD
                    candidato.nomeNaUrna = nomeUrna
                    candidato.numeroCandidato = numeroCandidato.toInt()
                    candidato.cargoCandidato = cargoCandidato
                    candidato.nomePartido = nomePartido
                    candidatoDAO.atualizar(candidato)
                }
                finish()
            }
        }
    }
}