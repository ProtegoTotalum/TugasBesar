package com.grayfien.testugd1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.grayfien.testugd1.databinding.ActivityMainBinding
import com.grayfien.testugd1.package_room.PasienDB
import com.grayfien.testugd1.package_room.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var db: UserDB
    private lateinit var shareP: Preference
    lateinit var mBundle: Bundle
    lateinit var vUsername : String

    private val LOGIN_ID = "login"
    private val loginId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shareP = Preference(this)

        createNotificationChannel()

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
            db = Room.databaseBuilder(applicationContext,UserDB::class.java,"user-db").build()
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
                val user = db.userDao().getUser(username, password)

                if(user == null){
                    Log.d("MainActivity", "USER TIDAK ADA ")
                    withContext(Dispatchers.Main){
                        inputUsername.setError("Username Tidak Sesuai !")
                        inputPassword.setError("Password Tidak Sesuai !")
                        checkLogin = false
                    }
                }else{
                    sendNotificationLogin()
                    Log.d("Login Activity", "USER DITEMUKAN")
                    withContext(Dispatchers.Main){
                        val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(moveHome)
                        checkLogin = true
                        shareP.setUser(user)
                    }
                }
            }


        })

        btnRegister.setOnClickListener (View.OnClickListener {
            val moveRegister = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(moveRegister)
        })
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val login = NotificationChannel(LOGIN_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(login)
        }

    }

    private fun sendNotificationLogin() {

        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val broadcastIntent : Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", inputUsername.getEditText()?.getText().toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, LOGIN_ID)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setContentTitle(getString(R.string.welcome_msg))
            .setContentText(inputUsername.getEditText()?.getText().toString())
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Selamat Anda telah berhasil masuk ke aplikasi Apotik Kita. Selamat menikmati fitur yang ada. Semoga sehat selalu :)")
                .setBigContentTitle("Halo!")
                .setSummaryText(getString(R.string.apotek_kita)))
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(loginId, builder.build())
        }

    }

    fun getBundle(){
        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("username")

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputUsername.getEditText()?.setText(name)
    }
}