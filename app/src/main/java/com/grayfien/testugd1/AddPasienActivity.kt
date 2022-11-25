package com.grayfien.testugd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grayfien.testugd1.databinding.ActivityAddPasienBinding
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

    fun saveData(){
        with(binding){
            val id_pasien = txtIdPasien.text.toString()
            val nama_pasien = txtNamaPasien.text.toString()
            val email_pasien = txtEmailPasien.text.toString()
            val tglLahir_pasien = txtTglLahirPasien.text.toString()
            val noTelp_pasien = txtNoTelpPasien.text.toString()

            RClient.instances.createDataPasien(id_pasien,nama_pasien, email_pasien,tglLahir_pasien, noTelp_pasien).enqueue(object : Callback<ResponseCreate>{
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext, "${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtIdPasien.setError(jsonObj.getString("message"))
                        Toast.makeText(applicationContext,"Maaf sudah ada data", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

                }
            })
        }
    }
}