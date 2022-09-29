package com.grayfien.testugd1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.activity_edit_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPasienActivity : AppCompatActivity() {

    val db by lazy { PasienDB(this) }
    private var pasienId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pasien)
        setupView()
        setupListener()

        //Toast.makeText(this,noteId.toString(),Toast.LENGTH_SHORT).show()
    }
    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
                Constant.TYPE_CREATE -> {
                btnAdd.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnAdd.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                getPasien()
            }
            Constant.TYPE_UPDATE -> {
                btnUpdate.visibility = View.GONE
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
    }
}