package com.grayfien.testugd1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.grayfien.testugd1.dataClass.ResponseDataUser
import com.grayfien.testugd1.dataClass.UserData
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    //private lateinit var _currentUser: User
    // lateinit var sharedPreferences: SharedPreferences
    // private lateinit var shareP: Preference


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        b = intent.extras
        val id = b?.getString("id")

        id?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener {
            with(binding){
                val nama = editNama.text.toString()
                val email = editEmail.text.toString()
                val username = editUsername.text.toString()
                val password = editPas.text.toString()
                val tglLahir = editTglLahir.text.toString()
                val noTelp = editNoTelp.text.toString()

                RClient.instances.updateData(id, nama, username, password, email, tglLahir, noTelp).enqueue(object : Callback<ResponseCreate>{
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "${response.body()?.pesan}",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

                    }
                })
            }
        }

        binding.btnCancel.setOnClickListener {
            val cancel = Intent(this@EditUserActivity, FragmentUser::class.java)
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

    fun getDetailData(id:String){
        RClient.instances.getData(id).enqueue(object :Callback<ResponseDataUser>{
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { listUser.addAll(it.data) }
                    with(binding){
                        editNama.setText(listUser[0].nama)
                        editUsername.setText(listUser[0].username)
                        editPas.setText(listUser[0].password)
                        editEmail.setText(listUser[0].email)
                        editTglLahir.setText(listUser[0].tgLahir)
                        editNoTelp.setText(listUser[0].noTelp)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {

            }
        })
    }


    /*      shareP = Preference(this)
        sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        db = Room.databaseBuilder(applicationContext,UserDB::class.java,"user-db").build()*/



/*        val id = shareP.getUser()?.id
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
        }*/

}