package com.grayfien.testugd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grayfien.testugd1.databinding.ActivityAddSupplierBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_add_supplier.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSupplierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSupplierBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSupplierBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAddSupplier.setOnClickListener {
            val id_supplier = txt_id_supplier.text.toString()
            val nama_supplier = txt_nama_supplier.text.toString()
            val email_supplier = txt_email_supplier.text.toString()
            val noTelp_supplier = txt_noTelp_Supplier.text.toString()


            if (id_supplier.isEmpty()){
                FancyToast.makeText(this@AddSupplierActivity, "ID Supplier tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (nama_supplier.isEmpty()){
                FancyToast.makeText(this@AddSupplierActivity, "Nama supplier tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (email_supplier.isEmpty()){
                FancyToast.makeText(this@AddSupplierActivity, "Email supplier tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (noTelp_supplier.isEmpty()){
                FancyToast.makeText(this@AddSupplierActivity, "Nomor telepon supplier tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else{
                saveData()
            }
        }
    }

    fun saveData() {
        with(binding) {
            val id_supplier = txt_id_supplier.text.toString()
            val nama_supplier = txt_nama_supplier.text.toString()
            val email_supplier = txt_email_supplier.text.toString()
            val noTelp_supplier = txt_noTelp_Supplier.text.toString()

            RClient.instances.createDataSupplier(
                id_supplier,
                nama_supplier,
                email_supplier,
                noTelp_supplier
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
                        txtIdSupplier.setError(jsonObj.getString("message"))
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