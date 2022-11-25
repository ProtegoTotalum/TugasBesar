package com.grayfien.testugd1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.dataClass.PasienData
import com.grayfien.testugd1.dataClass.ResponseDataPasien
import com.grayfien.testugd1.databinding.ActivityEditPasienBinding
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.activity_edit_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPasienActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditPasienBinding
    private var b:Bundle? = null
    private var listPasien = java.util.ArrayList<PasienData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPasienBinding.inflate(layoutInflater)

        setContentView(binding.root)
        /*supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Pasien"*/

        b = intent.extras
        val id_pasien = b?.getString("id_pasien")


        id_pasien?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener{
            with(binding){
                val nama_pasien = editNamaPasien.text.toString()
                val email_pasien = editEmailPasien.text.toString()
                val tglLahir_pasien = editTglLahirPasien.text.toString()
                val noTelp_pasien = editNoTelpPasien.text.toString()

                RClient.instances.updateDataPasien(id_pasien,nama_pasien,email_pasien,tglLahir_pasien,noTelp_pasien).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(call: Call<ResponseCreate>, response: Response<ResponseCreate>){
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext,"${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun getDetailData(id_pasien:String){
        RClient.instances.getDataPasien(id_pasien).enqueue(object : Callback<ResponseDataPasien> {
            override fun onResponse(
                call: Call<ResponseDataPasien>,
                response: Response<ResponseDataPasien>
            ) {
                if (response.isSuccessful){
                    response.body()?.let{listPasien.addAll(it.data)}
                    with(binding){
                        editIdPasien.setText(listPasien[0].id_pasien)
                        editNamaPasien.setText(listPasien[0].nama_pasien)
                        editEmailPasien.setText(listPasien[0].email_pasien)
                        editTglLahirPasien.setText(listPasien[0].tglLahir_pasien)
                        editNoTelpPasien.setText(listPasien[0].noTelp_pasien)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPasien>, t: Throwable) {

            }
        })
    }


}



/*
val db by lazy { PasienDB(this) }
private var pasienId: Int = 0

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_pasien)
    setupView()
    setupListener()


}
fun setupView(){
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    val intentType = intent.getIntExtra("intent_type", 0)
    when (intentType){
        Constant.TYPE_CREATE -> {
            btnUpdate.visibility = View.GONE
        }
        Constant.TYPE_READ -> {
            btnAdd.visibility = View.GONE
            btnUpdate.visibility = View.GONE
            getPasien()
        }
        Constant.TYPE_UPDATE -> {
            btnAdd.visibility = View.GONE
            getPasien()
        }
    }
}

private fun setupListener() {
    btnAdd.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            db.pasienDao().addPasien(
                Pasien(0,
                    editNamaPasien.text.toString(),
                    editEmailPasien.text.toString(),
                    editTglLahirPasien.text.toString(),
                    editNoTelpPasien.text.toString())
            )
            finish()
        }
    }
    btnUpdate.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            db.pasienDao().updatePasien(
                Pasien(pasienId,
                    editNamaPasien.text.toString(),
                    editEmailPasien.text.toString(),
                    editTglLahirPasien.text.toString(),
                    editNoTelpPasien.text.toString())
            )
            finish()
        }
    }
}

fun getPasien() {
    pasienId = intent.getIntExtra("intent_id", 0)
    CoroutineScope(Dispatchers.IO).launch {
        val pasiens = db.pasienDao().getPasien(pasienId)[0]
        editNamaPasien.setText(pasiens.name)
        editEmailPasien.setText(pasiens.email)
        editTglLahirPasien.setText(pasiens.tglLahir)
        editNoTelpPasien.setText(pasiens.noTelp)
    }
}


override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return super.onSupportNavigateUp()
}*/
