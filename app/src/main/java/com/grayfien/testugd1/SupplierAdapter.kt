package com.grayfien.testugd1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.dataClass.SupplierData
import com.grayfien.testugd1.databinding.SupplierAdapterBinding


class SupplierAdapter(private val data: ArrayList<SupplierData>, private val context: Context) :
    RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() {

    inner class SupplierViewHolder(item: SupplierAdapterBinding) :
        RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(supplierData: SupplierData) {
            with(binding) {
                txtIdSupplier.text = supplierData.id_supplier
                txtNamaSupplier.text = supplierData.nama_supplier
                cvDataSupplier.setOnClickListener {
                    var i = Intent(
                        context,
                        DetailSupplierActivity::class.java
                    ).apply {
                        putExtra("id_supplier", supplierData.id_supplier)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        return SupplierViewHolder(
            SupplierAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}