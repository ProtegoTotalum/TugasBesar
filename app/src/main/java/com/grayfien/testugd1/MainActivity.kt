package com.grayfien.testugd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var db: PasienDB
    private lateinit var shareP: Preference
    lateinit var mBundle: Bundle
    lateinit var vUsername : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shareP = Preference(this)


        getBundle()

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputPassword = findViewById(R.id.inputLayoutPassword)
        mainLayout = findViewById(R.id.mainLayout)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnDaftar)

        btnClear.setOnClickListener {
            inputUsername.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")

            Snackbar.make(mainLayout,"Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }


        btnLogin.setOnClickListener (View.OnClickListener {
            var checkLogin = false
            val username: String =inputUsername.getEditText()?.getText().toString()
            val password: String =inputPassword.getEditText()?.getText().toString()
            var checkUser :String
            var checkPas : String
            db = Room.databaseBuilder(applicationContext,PasienDB::class.java,"pasien-db").build()
            var userId : Int = 0
            var pasId : Int = 0

            if(username.isEmpty()){
                inputUsername.setError("Username must be filled with text")
                checkLogin = false
            }
            if(password.isEmpty()){
                inputPassword.setError("Password must be filled with text")
                checkLogin = false
            }


            CoroutineScope(Dispatchers.IO).launch {
                val pasien = db.pasienDao().getUser(username, password)

                if(pasien == null){
                    Log.d("MainActivity", "PASIEN TIDAK ADA ")
                    withContext(Dispatchers.Main){
                        inputUsername.setError("Username Tidak Sesuai !")
                        inputPassword.setError("Password Tidak Sesuai !")
                        checkLogin = false
                    }
                }else{
                    Log.d("Login Activity", "PASIEN DITEMUKAN")
                    withContext(Dispatchers.Main){
                        val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(moveHome)
                        checkLogin = true
                        shareP.setUser(pasien)
                    }
                }
            }


        })

        btnRegister.setOnClickListener (View.OnClickListener {
            val moveRegister = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(moveRegister)
        })
    }

    fun getBundle(){
        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("username")

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputUsername.getEditText()?.setText(name)
    }
}