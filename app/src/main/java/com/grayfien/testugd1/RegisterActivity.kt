package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity() {
    private lateinit var vNama: TextInputLayout
    private lateinit var vEmail: TextInputLayout
    private lateinit var vNoTelp: TextInputLayout
    private lateinit var vUsername: TextInputLayout
    private lateinit var vPassword: TextInputLayout
    private lateinit var vTglLahir: TextInputLayout
    private lateinit var namaInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var noTelpInput: TextInputEditText
    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var tanggalLahirInput: TextInputEditText

    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        namaInput = findViewById(R.id.inputNama)
        emailInput = findViewById(R.id.inputEmail)
        noTelpInput = findViewById(R.id.inputTelp)
        usernameInput = findViewById(R.id.inputUsername)
        passwordInput = findViewById(R.id.inputPassword)
        tanggalLahirInput = findViewById(R.id.inputTanggalLahir)

        vNama = findViewById(R.id.layNama)
        vEmail = findViewById(R.id.layEmail)
        vNoTelp = findViewById(R.id.layTelp)
        vUsername = findViewById(R.id.layUsername)
        vPassword = findViewById(R.id.layPassword)
        vTglLahir = findViewById(R.id.layTanggalLahir)

        btnRegister = findViewById(R.id.btnRegister)


        btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()

            val nama : String = vNama.getEditText()?.getText().toString()
            val email : String = vEmail.getEditText()?.getText().toString()
            val noTelp : String = vNoTelp.getEditText()?.getText().toString()
            val tglLahir : String = vTglLahir.getEditText()?.getText().toString()
            val username : String = vUsername.getEditText()?.getText().toString()
            val password : String = vPassword.getEditText()?.getText().toString()


            if(nama.isEmpty()) {
                namaInput.setError("Nama Tidak Boleh Kosong")
            }
            if(email.isEmpty()){
                emailInput.setError("Email Tidak Boleh Kosong")
            }
            if(noTelp.isEmpty()) {
                noTelpInput.setError("Nomor Telepon Tidak Boleh Kosong")
            }
            if(username.isEmpty()) {
                usernameInput.setError("Username Tidak Boleh Kosong")
            }
            if(password.isEmpty()) {
                passwordInput.setError("Password Tidak Boleh Kosong")
            }
            if(tglLahir.isEmpty()){
                tanggalLahirInput.setError("Tanggal Lahir Tidak Boleh Kosong")
            }else{
                mBundle.putString("nama", vNama.getEditText()?.getText().toString())
                mBundle.putString("email", vEmail.getEditText()?.getText().toString())
                mBundle.putString("noTelp", vNoTelp.getEditText()?.getText().toString())
                mBundle.putString("username", vUsername.getEditText()?.getText().toString())
                mBundle.putString("password", vPassword.getEditText()?.getText().toString())
                mBundle.putString("tanggalLahir", vTglLahir.getEditText()?.getText().toString())

                intent.putExtras(mBundle)
                startActivity(intent)
            }
        }
    }
}