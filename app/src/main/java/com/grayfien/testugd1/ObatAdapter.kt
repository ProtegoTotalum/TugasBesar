package com.grayfien.testugd1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.dataClass.ObatData
import com.grayfien.testugd1.databinding.ObatAdapterBinding

class ObatAdapter(private val data: ArrayList<ObatData>, private val context: Context) :
    RecyclerView.Adapter<ObatAdapter.ObatViewHolder>() {

    inner class ObatViewHolder(item: ObatAdapterBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(obatData: ObatData) {
            with(binding) {
                txtIdObat.text = obatData.id_obat
                txtNamaObat.text = obatData.nama_obat
                cvDataObat.setOnClickListener {
                    var i = Intent(
                        context,
                        DetailObatActivity::class.java
                    ).apply {
                        putExtra("id_obat", obatData.id_obat)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        return ObatViewHolder(
            ObatAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}