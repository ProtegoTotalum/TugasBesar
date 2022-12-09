package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.grayfien.testugd1.dataClass.ObatData
import com.grayfien.testugd1.dataClass.ResponseDataObat
import com.grayfien.testugd1.databinding.ActivityDetailObatBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailObatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailObatBinding
    private var b: Bundle? = null
    private val listObat = ArrayList<ObatData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id_obat = b?.getString("id_obat")

        id_obat?.let { getDataDetail(it) }

        binding.btnDeleteObat.setOnClickListener {
            id_obat?.let { it1 -> deleteDataObat(it1) }
        }

        binding.btnEditObat.setOnClickListener {
            startActivity(Intent(this, EditObatActivity::class.java).apply {
                putExtra("id_obat", id_obat)
            })
        }
    }

    fun getDataDetail(id_obat: String) {
        RClient.instances.getDataObat(id_obat).enqueue(object : Callback<ResponseDataObat> {
            override fun onResponse(
                call: Call<ResponseDataObat>,
                response: Response<ResponseDataObat>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listObat.addAll(it.data)
                    }
                    with(binding) {
                        tvIdObat.text = listObat[0].id_obat
                        tvNamaObat.text = listObat[0].nama_obat
                        tvJenisObat.text = listObat[0].jenis_obat
                        tvDeskripsiObat.text = listObat[0].deskripsi_obat
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataObat>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun deleteDataObat(id_obat: String) {
        val builder = AlertDialog.Builder(this@DetailObatActivity)
        builder.setMessage("Yakin mau hapus data ?")
            .setCancelable(false)
            .setPositiveButton("Ya") { dialog, id ->
                doDeleteData(id_obat)
            }
            .setNegativeButton("Tidak") { dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    fun doDeleteData(id_obat: String) {
        RClient.instances.deleteDataObat(id_obat).enqueue(object : Callback<ResponseCreate> {
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