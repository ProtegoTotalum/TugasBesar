package com.grayfien.testugd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grayfien.testugd1.databinding.ActivityAddObatBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_add_obat.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddObatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddObatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddObatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAddObat.setOnClickListener {
            val id_obat = txt_id_obat.text.toString()
            val nama_obat = txt_nama_obat.text.toString()
            val jenis_obat = txt_jenis_obat.text.toString()
            val deskripsi_obat = txt_deskripsi_obat.text.toString()


            if (id_obat.isEmpty()){
                FancyToast.makeText(this@AddObatActivity, "ID Obat tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (nama_obat.isEmpty()){
                FancyToast.makeText(this@AddObatActivity, "Nama obat tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (jenis_obat.isEmpty()){
                FancyToast.makeText(this@AddObatActivity, "Jenis obat tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (deskripsi_obat.isEmpty()){
                FancyToast.makeText(this@AddObatActivity, "Deskripsi obat tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else{
                saveData()
            }
        }
    }

    fun saveData() {
        with(binding) {
            val id_obat = txt_id_obat.text.toString()
            val nama_obat = txt_nama_obat.text.toString()
            val jenis_obat = txt_jenis_obat.text.toString()
            val deskripsi_obat = txt_deskripsi_obat.text.toString()

            RClient.instances.createDataObat(
                id_obat,
                nama_obat,
                jenis_obat,
                deskripsi_obat
            ).enqueue(object : Callback<ResponseCreate> {
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
                    } else {
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtIdObat.setError(jsonObj.getString("message"))
                        FancyToast.makeText(
                            applicationContext,
                            "Maaf sudah ada data",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

                }
            })
        }
    }
}