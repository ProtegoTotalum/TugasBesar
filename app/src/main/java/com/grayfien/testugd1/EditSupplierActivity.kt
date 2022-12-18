package com.grayfien.testugd1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grayfien.testugd1.dataClass.ResponseDataSupplier
import com.grayfien.testugd1.dataClass.SupplierData
import com.grayfien.testugd1.databinding.ActivityEditSupplierBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditSupplierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditSupplierBinding
    private var b: Bundle? = null
    private var listSupplier = java.util.ArrayList<SupplierData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSupplierBinding.inflate(layoutInflater)

        setContentView(binding.root)

        b = intent.extras
        val id_supplier = b?.getString("id_supplier")


        id_supplier?.let { getDetailData(it) }

        binding.btnUpdateSupplier.setOnClickListener {
            with(binding) {
                val nama_supplier = editNamaSupplier.text.toString()
                val email_supplier = editEmailSupplier.text.toString()
                val noTelp_supplier = editNoTelpSupplier.text.toString()

                RClient.instances.updateDataSupplier(
                    id_supplier,
                    nama_supplier,
                    email_supplier,
                    noTelp_supplier
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

    fun getDetailData(id_supplier: String) {
        RClient.instances.getDataSupplier(id_supplier).enqueue(object : Callback<ResponseDataSupplier> {
            override fun onResponse(
                call: Call<ResponseDataSupplier>,
                response: Response<ResponseDataSupplier>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listSupplier.addAll(it.data) }
                    with(binding) {
                        editIdSupplier.setText(listSupplier[0].id_supplier)
                        editNamaSupplier.setText(listSupplier[0].nama_supplier)
                        editEmailSupplier.setText(listSupplier[0].email_supplier)
                        editNoTelpSupplier.setText(listSupplier[0].noTelp_supplier)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataSupplier>, t: Throwable) {

            }
        })
    }
}