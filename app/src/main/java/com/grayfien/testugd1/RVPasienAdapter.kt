package com.grayfien.testugd1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.entity.Pasien

class RVPasienAdapter(private val data:Array<Pasien>) : RecyclerView.Adapter<RVPasienAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_pasien_adapter, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNamaPasien.text = currentItem.name
        holder.tvTglLahir.text = currentItem.tglLahir
        holder.tvEmail.text = currentItem.email
        holder.tvNomorTelepon.text = currentItem.noTelp

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNamaPasien : TextView = itemView.findViewById(R.id.tv_nama_pasien)
        val tvTglLahir : TextView = itemView.findViewById(R.id.tv_tgl_lahir)
        val tvEmail : TextView = itemView.findViewById(R.id.tv_email)
        val tvNomorTelepon : TextView = itemView.findViewById(R.id.tv_nomor_telepon)
    }
}