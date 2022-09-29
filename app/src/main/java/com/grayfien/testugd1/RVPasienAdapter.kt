package com.grayfien.testugd1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.package_room.Pasien
import kotlinx.android.synthetic.main.rv_pasien_adapter.view.*


class RVPasienAdapter(private val data:ArrayList<Pasien>, private val listener: OnAdapterListener) : RecyclerView.Adapter<RVPasienAdapter.viewHolder>() {

class RVPasienAdapter(private val pasiens:ArrayList<Pasien>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<RVPasienAdapter.PasienViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        return PasienViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_pasien_adapter, parent, false)
        )
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.view.tv_nama_pasien.text = currentItem.name
        holder.view.tv_nama_pasien.setOnClickListener{
            listener.onClick(currentItem)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(currentItem)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(currentItem)
        }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {

        val pasien = pasiens[position]
        
        holder.view.tv_nama_pasien.text = pasien.name
        holder.view.tv_email.text = pasien.email
        holder.view.tv_tgl_lahir.text = pasien.tglLahir
        holder.view.tv_nomor_telepon.text = pasien.noTelp

//        holder.view.text_title.setOnClickListener{
//
//            listener.onClick(pasien)
//
//        }
//
//        holder.view.icon_edit.setOnClickListener {
//
//            listener.onUpdate(pasien)
//
//        }
//
//        holder.view.icon_delete.setOnClickListener {
//
//            listener.onDelete(pasien)
//
//        }
        
//        holder.tvNamaPasien.text = currentItem.name
//        holder.tvTglLahir.text = currentItem.tglLahir
//        holder.tvEmail.text = currentItem.email
//        holder.tvNomorTelepon.text = currentItem.noTelp

    }

    override fun getItemCount() = pasiens.size
    
    inner class PasienViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Pasien>){

        pasiens.clear()
        pasiens.addAll(list)
        notifyDataSetChanged()

    }


    inner class viewHolder(val view: View) : RecyclerView.ViewHolder(view)

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
    }


    interface OnAdapterListener {

        fun onClick(pasien: Pasien)
        fun onUpdate(pasien: Pasien)
        fun onDelete(pasien: Pasien)

    }
    
//    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val tvNamaPasien : TextView = itemView.findViewById(R.id.tv_nama_pasien)
//        val tvTglLahir : TextView = itemView.findViewById(R.id.tv_tgl_lahir)
//        val tvEmail : TextView = itemView.findViewById(R.id.tv_email)
//        val tvNomorTelepon : TextView = itemView.findViewById(R.id.tv_nomor_telepon)
//    }
}