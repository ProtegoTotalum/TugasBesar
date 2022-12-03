package com.grayfien.testugd1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.databinding.ActivityAddPasienBinding
import com.grayfien.testugd1.databinding.ActivityRegisterBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_add_pasien.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPasienActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPasienBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPasienBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAddPasien.setOnClickListener {
            val id_pasien = txt_id_pasien.text.toString()
            val nama_pasien = txt_nama_pasien.text.toString()
            val email_pasien = txt_email_pasien.text.toString()
            val tglLahir_pasien = txt_tglLahir_pasien.text.toString()
            val noTelp_pasien = txt_noTelp_pasien.text.toString()

            if (id_pasien.isEmpty()){
                FancyToast.makeText(this@AddPasienActivity, "ID tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (nama_pasien.isEmpty()){
                FancyToast.makeText(this@AddPasienActivity, "Nama pasien tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (email_pasien.isEmpty()){
                FancyToast.makeText(this@AddPasienActivity, "Email pasien tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (tglLahir_pasien.isEmpty()){
                FancyToast.makeText(this@AddPasienActivity, "Tanggal lahir pasien tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (noTelp_pasien.isEmpty()){
                FancyToast.makeText(this@AddPasienActivity, "Nomor telepon tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else{
                saveData()
            }
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