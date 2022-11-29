package com.grayfien.testugd1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.grayfien.testugd1.package_room.UserDB
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var db: UserDB
    private lateinit var shareP: Preference
    private var b: Bundle? = null
    lateinit var vUsername: String

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

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }




        btnLogin.setOnClickListener(View.OnClickListener {
            var checkLogin = false
            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputPassword.getEditText()?.getText().toString()
            var checkUser: String
            var checkPas: String


            if (username.isEmpty()) {
                Toast.makeText(this@MainActivity, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()) {
                Toast.makeText(this@MainActivity, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }else{
                login()
            }
        })

        btnRegister.setOnClickListener(View.OnClickListener {
            val moveRegister = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(moveRegister)
        })
    }

    fun login() {
        val username = input_username.text.toString().trim()
        val password = input_password.text.toString().trim()

        RClient.instances.login(username, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val login = response.body()!!


                    FancyToast.makeText(
                        this@MainActivity,
                        "Login success!",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                    val moveHome = Intent(
                        this@MainActivity,
                        HomeActivity::class.java
                    ).apply { putExtra("id_user", login.data?.id) }
                    startActivity(moveHome)
                    sendNotificationLogin()
                } else {
                    FancyToast.makeText(
                        this@MainActivity,
                        "Login failed!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()
                    Log.d("retro", response.toString())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }

    /*private fun Login(username: String, password: String){
        //val loginInfo = LoginBody(username, password)
        RClient.instances.userLogin(username,password).enqueue(object : Callback<ResponseDataUser>{
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(this@MainActivity, "Login success!", Toast.LENGTH_SHORT).show()
                    val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(moveHome)
                }else{
                    Toast.makeText(this@MainActivity, "Login failed!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }

        })
    }*/

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val login =
                NotificationChannel(LOGIN_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = descriptionText
                }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(login)
        }

    }

    private fun sendNotificationLogin() {

        val intent: Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val broadcastIntent: Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", inputUsername.getEditText()?.getText().toString())
        val actionIntent = PendingIntent.getBroadcast(
            this,
            0,
            broadcastIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, LOGIN_ID)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setContentTitle(getString(R.string.welcome_msg))
            .setContentText(inputUsername.getEditText()?.getText().toString())
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Selamat Anda telah berhasil masuk ke aplikasi Apotik Kita. Selamat menikmati fitur yang ada. Semoga sehat selalu :)")
                    .setBigContentTitle("Halo!")
                    .setSummaryText(getString(R.string.apotek_kita))
            )
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

    fun getBundle() {
        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("username")

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputUsername.getEditText()?.setText(name)
    }
}