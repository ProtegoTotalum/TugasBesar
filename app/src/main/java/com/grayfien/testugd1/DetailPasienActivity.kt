package com.grayfien.testugd1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.dataClass.PasienData
import com.grayfien.testugd1.dataClass.ResponseDataPasien
import com.grayfien.testugd1.databinding.ActivityDetailPasienBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPasienActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPasienBinding
    private var b: Bundle? = null
    private val listPasien = ArrayList<PasienData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id_pasien = b?.getString("id_pasien")

        id_pasien?.let { getDataDetail(it) }

        binding.btnDeletePasien.setOnClickListener {
            id_pasien?.let { it1 -> deleteDataPasien(it1) }
        }

        binding.btnEditPasien.setOnClickListener {
            startActivity(Intent(this, EditPasienActivity::class.java).apply {
                putExtra("id_pasien", id_pasien)
            })
        }
    }

    fun getDataDetail(id_pasien: String) {
        RClient.instances.getDataPasien(id_pasien).enqueue(object : Callback<ResponseDataPasien> {
            override fun onResponse(
                call: Call<ResponseDataPasien>,
                response: Response<ResponseDataPasien>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listPasien.addAll(it.data)
                    }
                    with(binding) {
                        tvIdPasien.text = listPasien[0].id_pasien
                        tvNamaPasien.text = listPasien[0].nama_pasien
                        tvEmailPasien.text = listPasien[0].email_pasien
                        tvTglLahirPasien.text = listPasien[0].tglLahir_pasien
                        tvNoTelpPasien.text = listPasien[0].noTelp_pasien
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPasien>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun deleteDataPasien(id_pasien: String) {
        val builder = AlertDialog.Builder(this@DetailPasienActivity)
        builder.setMessage("Yakin mau hapus data ?")
            .setCancelable(false)
            .setPositiveButton("Ya") { dialog, id ->
                doDeleteData(id_pasien)
            }
            .setNegativeButton("Tidak") { dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    fun doDeleteData(id_pasien: String) {
        RClient.instances.deleteDataPasien(id_pasien).enqueue(object : Callback<ResponseCreate> {
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