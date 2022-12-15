package com.grayfien.testugd1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback
import com.grayfien.testugd1.databinding.ActivityUserBinding
import com.grayfien.testugd1.package_room.UserDB
import kotlinx.android.synthetic.main.activity_user.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserActivity : AppCompatActivity() {

    private lateinit var shareP: Preference
    private lateinit var binding: ActivityUserBinding
    private lateinit var db: UserDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
//        db = Room.databaseBuilder(applicationContext, UserDB::class.java, "user-db").build()
        val view = binding.root
        setContentView(view)
        shareP = Preference(this)


        val nama = shareP.getUser()?.name
        val email = shareP.getUser()?.email
        val tglLahir = shareP.getUser()?.tglLahir
        val noTelp = shareP.getUser()?.noTelp

        binding.editNama.setText(nama)
        binding.editEmail.setText(email)
        binding.editTglLahir.setText(tglLahir)
        binding.editNoTelp.setText(noTelp)

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditUserActivity::class.java)
            startActivity(intent)
        }

        btnImage.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }


    }

    private fun dateToString(dayofMonth: Int, month: Int, year: Int): String {
        return dayofMonth.toString()+"/"+(month+1)+"/"+year.toString()
    }
}