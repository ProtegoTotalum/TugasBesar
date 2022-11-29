package com.grayfien.testugd1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.databinding.ActivityAddPasienBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPasienActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPasienBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPasien.setOnClickListener {
            saveData()
        }
    }

    fun saveData() {
        with(binding) {
            val id_pasien = txtIdPasien.text.toString()
            val nama_pasien = txtNamaPasien.text.toString()
            val email_pasien = txtEmailPasien.text.toString()
            val tglLahir_pasien = txtTglLahirPasien.text.toString()
            val noTelp_pasien = txtNoTelpPasien.text.toString()

            RClient.instances.createDataPasien(
                id_pasien,
                nama_pasien,
                email_pasien,
                tglLahir_pasien,
                noTelp_pasien
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
                        txtIdPasien.setError(jsonObj.getString("message"))
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