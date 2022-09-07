package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText


class RegisterActivity : AppCompatActivity() {
    private lateinit var nama: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var noTelp: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nama = findViewById(R.id.inputNama)
        email = findViewById(R.id.inputEmail)
        noTelp = findViewById(R.id.inputTelp)
        username = findViewById(R.id.inputUsername)
        password = findViewById(R.id.inputPassword)
        tanggalLahir = findViewById(R.id.inputTanggalLahir)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()

            mBundle.putString("nama", nama.text.toString())
            mBundle.putString("email", email.text.toString())
            mBundle.putString("noTelp", noTelp.text.toString())
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            mBundle.putString("tanggalLahir", tanggalLahir.text.toString())


            intent.putExtra("register",mBundle)

            startActivity(intent)
        }
    }
}