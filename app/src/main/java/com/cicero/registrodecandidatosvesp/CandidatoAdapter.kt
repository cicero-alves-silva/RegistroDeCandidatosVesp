package com.cicero.registrodecandidatosvesp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.cicero.registrodecandidatosvesp.CandidatoAdapter.CandidatoViewHolder
import com.cicero.registrodecandidatosvesp.databinding.ItemCandidatoBinding

class CandidatoAdapter(var candidatoDAO: CandidatoDAO) :
    RecyclerView.Adapter<CandidatoViewHolder>() {
    private var listaCandidatos = mutableListOf<Candidato>()

    fun atualizarListaCandidatos(lista: MutableList<Candidato>) {
        listaCandidatos = lista
        notifyDataSetChanged()
    }

    inner class CandidatoViewHolder(var binding: ItemCandidatoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(posicao: Int) {
            var candidato = listaCandidatos[posicao]
            binding.tvNomeUrna.text = candidato.nomeNaUrna
            binding.tvNumero.text = candidato.numeroCandidato.toString()
            binding.tvCargo.text = candidato.cargoCandidato
            binding.tvPartido.text = candidato.nomePartido

            binding.btEditar.setOnClickListener { view ->
                val intent = Intent(view.context, CadastroCandidatoActivity::class.java)
                intent.putExtra("CANDIDATO", candidato)
                view.context.startActivity(intent)
            }

            binding.btExcluir.setOnClickListener { view ->
                val alertDialog = AlertDialog.Builder(view.context)
                alertDialog.setTitle("Confirmar exclusão")
                alertDialog.setMessage("Deseja realmente excluir o(a) candidato(a)? ")
                alertDialog.setPositiveButton("Sim") { dialog, which ->
                    candidatoDAO.deletar(candidato)
                    val indiceDoRemovido = listaCandidatos.indexOf(candidato)
                    listaCandidatos.removeAt(indiceDoRemovido)
                    notifyItemRemoved(indiceDoRemovido)
                }
                alertDialog.setNegativeButton("Não") { dialog, which -> }
                alertDialog.create().show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CandidatoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCandidatoBinding.inflate(layoutInflater, parent, false)
        return CandidatoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CandidatoViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listaCandidatos.size
    }
}