package com.grayfien.testugd1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.dataClass.PasienData
import com.grayfien.testugd1.databinding.PasienAdapterBinding


class PasienAdapter(private val data: ArrayList<PasienData>, private val context: Context) :
    RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    inner class PasienViewHolder(item: PasienAdapterBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(pasienData: PasienData) {
            with(binding) {
                txtId.text = pasienData.id_pasien
                txtNama.text = pasienData.nama_pasien
                cvData.setOnClickListener {
                    var i = Intent(
                        context,
                        DetailPasienActivity::class.java
                    ).apply {
                        putExtra("id_pasien", pasienData.id_pasien)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        return PasienViewHolder(
            PasienAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    /*override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        val currentItem = data[position]
        holder.view.tv_nama_pasien.text = currentItem.name
        holder.view.tv_nama_pasien.setOnClickListener {
            listener.onClick(currentItem)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(currentItem)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(currentItem)
        }
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Pasien>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(pasien: Pasien)
        fun onUpdate(pasien: Pasien)
        fun onDelete(pasien: Pasien)
    }*/

}