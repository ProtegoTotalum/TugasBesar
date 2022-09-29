package com.grayfien.testugd1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.grayfien.testugd1.databinding.ActivityEditUserBinding
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.activity_edit_user.*
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.User
import com.grayfien.testugd1.package_room.UserDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding
    private lateinit var shareP: Preference
    private lateinit var _currentUser: User
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var db: UserDB
    private var userId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        shareP = Preference(this)
        sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        db = Room.databaseBuilder(applicationContext,UserDB::class.java,"user-db").build()



        val id = shareP.getUser()?.id
        val nama = shareP.getUser()?.name
        val username = shareP.getUser()?.username
        val password = shareP.getUser()?.password
        val email = shareP.getUser()?.email
        val noTelp = shareP.getUser()?.noTelp
        val tglLahir = shareP.getUser()?.tglLahir

        binding.editNama.setText(nama)
        binding.editUsername.setText(username)
        binding.editEmail.setText(email)
        binding.editTglLahir.setText(tglLahir)
        binding.editNoTelp.setText(noTelp)


        binding!!.btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val loggedUser : User = db.userDao().getUserID(id)
                db.userDao().updateUser(
                    User(loggedUser.id,
                        binding!!.editNama.text.toString(),
                        binding!!.editUsername.text.toString(),
                        loggedUser.password,
                        binding!!.editEmail.text.toString(),
                        binding!!.editTglLahir.text.toString(),
                        binding!!.editNoTelp.text.toString())
                )
                val intent = Intent(this@EditUserActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            val cancel = Intent(this@EditUserActivity, UserActivity::class.java)
            startActivity(cancel)
        }

        binding.btnClear.setOnClickListener {
            edit_nama.setText("")
            edit_email.setText("")
            edit_username.setText("")
            edit_pas.setText("")
            edit_tglLahir.setText("")
            edit_noTelp.setText("")
        }

        setContentView(binding?.root)
    }


/*    fun getUser(){
        userId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val user = db.userDao().getUser(userId) [0]
            edit_nama.setText(user.name)
            edit_email.setText(user.email)
            edit_tglLahir.setText(user.tglLahir)
            edit_noTelp.setText(user.noTelp)
        }
    }*/
}