package com.grayfien.testugd1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grayfien.testugd1.dataClass.ObatData
import com.grayfien.testugd1.dataClass.ResponseDataObat
import com.grayfien.testugd1.databinding.ActivityEditObatBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditObatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditObatBinding
    private var b: Bundle? = null
    private var listObat = java.util.ArrayList<ObatData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditObatBinding.inflate(layoutInflater)

        setContentView(binding.root)
        /*supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Pasien"*/

        b = intent.extras
        val id_obat = b?.getString("id_obat")


        id_obat?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama_obat = editNamaObat.text.toString()
                val jenis_obat = editJenisObat.text.toString()
                val deskripsi_obat = editDeskripsiObat.text.toString()

                RClient.instances.updateDataObat(
                    id_obat,
                    nama_obat,
                    jenis_obat,
                    deskripsi_obat
                ).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if (response.isSuccessful) {
                            FancyToast.makeText(
                                applicationContext,
                                "${response.body()?.pesan}",
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
    }

    fun getDetailData(id_obat: String) {
        RClient.instances.getDataObat(id_obat).enqueue(object : Callback<ResponseDataObat> {
            override fun onResponse(
                call: Call<ResponseDataObat>,
                response: Response<ResponseDataObat>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listObat.addAll(it.data) }
                    with(binding) {
                        editIdObat.setText(listObat[0].id_obat)
                        editNamaObat.setText(listObat[0].nama_obat)
                        editJenisObat.setText(listObat[0].jenis_obat)
                        editDeskripsiObat.setText(listObat[0].deskripsi_obat)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataObat>, t: Throwable) {

            }
        })
    }
}