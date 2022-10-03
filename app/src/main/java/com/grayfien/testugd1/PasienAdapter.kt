package com.grayfien.testugd1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.package_room.Pasien
import kotlinx.android.synthetic.main.pasien_adapter.view.*


class PasienAdapter(private val data:ArrayList<Pasien>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        return PasienViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pasien_adapter, parent, false)
        )
    }


    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
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

    inner class PasienViewHolder(val view: View) : RecyclerView.ViewHolder(view)

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

}