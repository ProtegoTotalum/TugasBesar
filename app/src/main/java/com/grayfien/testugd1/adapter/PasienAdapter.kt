package com.grayfien.testugd1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.EditPasienActivity
import com.grayfien.testugd1.PasienActivity
import com.grayfien.testugd1.databinding.PasienAdapterBinding
import com.grayfien.testugd1.retrofit.PasienData


class PasienAdapter(

    private val listPasien: ArrayList<PasienData>,
    private val context: Context,

    ) :
    RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    inner class
    PasienViewHolder(item: PasienAdapterBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(pasienData: PasienData){
            with(binding) {
                tvNamaPasien.text = pasienData.nama_pasien
                cvData.setOnClickListener {
                    var i = Intent(context,
                        PasienActivity::class.java).apply {
                        putExtra("id_pasien",pasienData.id)
                        putExtra("nama_pasien",pasienData.nama_pasien)
                    }
                    context.startActivity(i)
                }
                iconDelete.setOnClickListener{
                    (context as PasienActivity).deleteData(pasienData.id)
                }
                iconEdit.setOnClickListener{
                    var i = Intent(context,
                        EditPasienActivity::class.java).apply {
                        putExtra("id_pasien",pasienData.id)
                        putExtra("nama_pasien",pasienData.nama_pasien)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
//        return PasienViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.pasien_adapter, parent, false)
//        )
        return PasienViewHolder(
            PasienAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
        ))
    }


    override fun onBindViewHolder(holder: PasienViewHolder, position: Int, ) {

        holder.bind(listPasien[position])

//        holder.view.tv_nama_pasien.setOnClickListener {
//            listener.onClick(pasien)
//        }
//        holder.view.icon_edit.setOnClickListener {
//            listener.onUpdate(pasien)
//        }
//        holder.view.icon_delete.setOnClickListener {
//            listener.onDelete(pasien)
//        }

    }

    override fun getItemCount():Int = listPasien.size


//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(list: List<Pasien>){
//        data.clear()
//        data.addAll(list)
//        notifyDataSetChanged()
//    }
//
//    interface OnAdapterListener {
//        fun onClick(pasien: PasienData)
//        fun onUpdate(pasien: PasienData)
//        fun onDelete(pasien: PasienData)
//    }

}