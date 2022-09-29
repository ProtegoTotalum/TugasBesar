package com.grayfien.testugd1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.package_room.PasienDB

class EditActivity : AppCompatActivity() {

    val db by lazy { PasienDB(this) }
    private var pasienId: Int = 0
    var fragobj: FragmentPasien = FragmentPasien()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        //Toast.makeText(this,noteId.toString(),Toast.LENGTH_SHORT).show()

    }

    /*
    private fun setupUpdate(){
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.pasienDao().updatePasien(
                    Pasien(pasienId,
                        editNama.text.toString(),
                    editUsername.text.toString(),
                    editPas.text.toString(),
                    editEmail.text.toString(),
                    editTglLahir.text.toString(),
                    editNoTelp.text.toString())
                )
                finish()
            }
        }
    }

     */

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}