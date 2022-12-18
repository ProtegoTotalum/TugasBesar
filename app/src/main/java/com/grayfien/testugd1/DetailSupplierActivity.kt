package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.grayfien.testugd1.dataClass.ResponseDataSupplier
import com.grayfien.testugd1.dataClass.SupplierData
import com.grayfien.testugd1.databinding.ActivityDetailSupplierBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_detail_supplier.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSupplierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSupplierBinding
    private var b: Bundle? = null
    private val listSupplier = ArrayList<SupplierData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id_supplier = b?.getString("id_supplier")

        id_supplier?.let { getDataDetail(it) }

        binding.btnDeleteSupplier.setOnClickListener {
            id_supplier?.let { it1 -> deleteDataSupplier(it1) }
        }

        binding.btnEditSupplier.setOnClickListener {
            startActivity(Intent(this, EditSupplierActivity::class.java).apply {
                putExtra("id_supplier", id_supplier)
            })
        }
    }

    fun getDataDetail(id_supplier: String) {
        RClient.instances.getDataSupplier(id_supplier).enqueue(object : Callback<ResponseDataSupplier> {
            override fun onResponse(
                call: Call<ResponseDataSupplier>,
                response: Response<ResponseDataSupplier>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listSupplier.addAll(it.data)
                    }
                    with(binding) {
                        tvIdSupplier.text = listSupplier[0].id_supplier
                        tvNamaSupplier.text = listSupplier[0].nama_supplier
                        tvEmailSupplier.text = listSupplier[0].email_supplier
                        tvNoTelpSupplier.text = listSupplier[0].noTelp_supplier
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataSupplier>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun deleteDataSupplier(id_supplier: String) {
        val builder = AlertDialog.Builder(this@DetailSupplierActivity)
        builder.setMessage("Yakin mau hapus data ?")
            .setCancelable(false)
            .setPositiveButton("Ya") { dialog, id ->
                doDeleteData(id_supplier)
            }
            .setNegativeButton("Tidak") { dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    fun doDeleteData(id_supplier: String) {
        RClient.instances.deleteDataSupplier(id_supplier).enqueue(object : Callback<ResponseCreate> {
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if (response.isSuccessful) {
                    FancyToast.makeText(
                        applicationContext,
                        "Data berhasil dihapus",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

            }
        })
    }
}