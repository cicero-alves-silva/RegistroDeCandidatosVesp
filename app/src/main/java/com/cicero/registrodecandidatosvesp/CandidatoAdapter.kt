package com.cicero.registrodecandidatosvesp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cicero.registrodecandidatosvesp.CandidatoAdapter.CandidatoViewHolder
import com.cicero.registrodecandidatosvesp.databinding.ItemCandidatoBinding

class CandidatoAdapter(var candidatoDAO: CandidatoDAO) :
    RecyclerView.Adapter<CandidatoViewHolder>() {
    private var listaCandidatos = mutableListOf<Candidato>()

    inner class CandidatoViewHolder(var binding: ItemCandidatoBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CandidatoViewHolder {
    }

    override fun onBindViewHolder(holder: CandidatoViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
    }
}