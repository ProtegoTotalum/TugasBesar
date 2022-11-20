package com.grayfien.testugd1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.databinding.ActivityEditPasienBinding
import com.grayfien.testugd1.databinding.PasienAdapterBinding
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import com.grayfien.testugd1.retrofit.PasienData
import com.grayfien.testugd1.retrofit.RClient
import com.grayfien.testugd1.retrofit.ResponseCreate
import com.grayfien.testugd1.retrofit.ResponseDataPasien
import kotlinx.android.synthetic.main.activity_edit_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.android.synthetic.main.pasien_adapter.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class EditPasienActivity : AppCompatActivity() {

//    val db by lazy { PasienDB(this) }
    private var pasienId: Int = 0
    private lateinit var binding: ActivityEditPasienBinding
    private val listPasien = ArrayList<PasienData>()
    private var b:Bundle? = null

    @SuppressLint("RestrictedApi")
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

                b = intent.extras
                val id_pasien = b?.getInt("id_pasien")
                id_pasien?.let { getPasien(it) }
            }
            Constant.TYPE_UPDATE -> {
                btnAdd.visibility = View.GONE

                b = intent.extras
                val id_pasien = b?.getInt("id_pasien")
                id_pasien?.let { getPasien(it) }
            }
        }
    }

    private fun setupListener() {

        btnAdd.setOnClickListener {
            saveData()
//            CoroutineScope(Dispatchers.IO).launch {
//                db.pasienDao().addPasien(
//                    Pasien(0,
//                        editNamaPasien.text.toString(),
//                        editEmailPasien.text.toString(),
//                        editTglLahirPasien.text.toString(),
//                        editNoTelpPasien.text.toString())
//                )
//                finish()
//            }
        }

        btnUpdate.setOnClickListener {

            b = intent.extras
            val id_pasien = b?.getInt("id_pasien")
            id_pasien?.let { getPasien(it) }

            with(binding) {

                val namaPasien = editNamaPasien.text.toString()
                val email = editEmailPasien.text.toString()
                val tglLahir = editTglLahirPasien.text.toString()
                val noTelp = editNoTelpPasien.text.toString()

                RClient.instances.updateData(id_pasien,namaPasien,email,tglLahir,noTelp).enqueue(object : Callback<ResponseCreate>{
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {

                            Toast.makeText(applicationContext,"${response.body()?.pesan}",
                                Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                    override fun onFailure(call:
                                           Call<ResponseCreate>, t: Throwable) {
                    }
                })
            }
//            startActivity(
//                Intent(this,
//                EditPasienActivity::class.java).apply {
//                    putExtra("id_pasien",id_pasien)
//                })
        }

        tv_tgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth ->
                binding.editTglLahirPasien.text =
                    dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
    }

    fun getPasien(id_pasien: Int) {
        RClient.instances.getData(id_pasien).enqueue(object :
            Callback<ResponseDataPasien> {
            override fun onResponse(
                call: Call<ResponseDataPasien>,
                response: Response<ResponseDataPasien>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listPasien.addAll(it.data) }
                    with(binding) {
                        editNamaPasien.setText(listPasien[0].nama_pasien)
                        editEmailPasien.setText(listPasien[0].email)
                        editTglLahirPasien.text = listPasien[0].tglLahir
                        editNoTelpPasien.setText(listPasien[0].noTelp)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPasien>, t: Throwable) {
            }
        })

    }

    fun saveData() {
        with(binding) {

            val namaPasien = editNamaPasien.text.toString()
            val email = editEmailPasien.text.toString()
            val tglLahir = editTglLahirPasien.text.toString()
            val noTelp = editNoTelpPasien.text.toString()

            RClient.instances.createData(pasienId,namaPasien,email,tglLahir,noTelp).enqueue(object : Callback<ResponseCreate>{
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){

                        Toast.makeText(applicationContext,"${response.body()?.pesan}",
                            Toast.LENGTH_LONG).show()
                        finish()
                    }else {
                        val jsonObj =
                            JSONObject(response.errorBody()!!.charStream().readText())

                        editEmailPasien.setError(jsonObj.getString("message"))
                        Toast.makeText(applicationContext,"There's a duplicate data", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call:
                                       Call<ResponseCreate>, t: Throwable) {
                }
            })
        }
    }

    private fun dateToString(year: Int, month: Int, dayofMonth: Int): String {
        return year.toString()+"-"+(month+1)+"-"+dayofMonth.toString()
    }

    private fun dateDialog(context: Context, datePicker:
    DatePickerDialog.OnDateSetListener):DatePickerDialog {
        val calender = Calendar.getInstance()
        return DatePickerDialog(
            context, datePicker,
            calender[Calendar.YEAR],
            calender[Calendar.MONTH],
            calender[Calendar.DAY_OF_MONTH],
        )
    }

//    fun getPasien() {
//        pasienId = intent.getIntExtra("intent_id", 0)
//        CoroutineScope(Dispatchers.IO).launch {
//            val pasiens = db.pasienDao().getPasien(pasienId)[0]
//            editNamaPasien.setText(pasiens.name)
//            editEmailPasien.setText(pasiens.email)
//            editTglLahirPasien.setText(pasiens.tglLahir)
//            editNoTelpPasien.setText(pasiens.noTelp)
//        }
//    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}