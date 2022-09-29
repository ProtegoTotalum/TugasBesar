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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_pasien_adapter, parent, false)
        return viewHolder(itemView)
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
    }

    override fun getItemCount(): Int {
        return data.size
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

}