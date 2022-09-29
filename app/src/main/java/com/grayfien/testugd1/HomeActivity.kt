package com.grayfien.testugd1


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment


class HomeActivity : AppCompatActivity() {

    private val LOGOUT_ID = "logout"
    private val logoutId = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        createNotificationChannel()

        val firstFragment=FragmentHome()
        val secondFragment=FragmentPasien()


        setCurrentFragment(firstFragment)
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuHome->setCurrentFragment(firstFragment)
                R.id.menuPasien->setCurrentFragment(secondFragment)
                R.id.menuProfil->{
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                }
                R.id.menuLogOut->{
                    val builder : AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                    builder.setMessage("Are you sure want to exit?")
                        .setPositiveButton("YES", object : DialogInterface.OnClickListener{
                            override fun onClick(dialogInterface: DialogInterface, i:Int){
                                //Notifikasi Logout
                                sendNotificationLogout()

                                //Keluar dari aplikasi
                                finishAndRemoveTask()
                            }
                        })
                        .show()
                }

            }
            true
        }

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val login = NotificationChannel(LOGOUT_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(login)
        }

    }

    private fun sendNotificationLogout() {

        val builder = NotificationCompat.Builder(this, LOGOUT_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(getString(R.string.apotek_kita))
            .setContentText("Sayonara sampai berjumpa pulang")
            .setStyle(NotificationCompat.InboxStyle()
                .addLine("Blablabblablaalbala")
                .addLine("Blablabblablaalbala")
                .addLine("Blablabblablaalbala")
                .addLine("Blablabblablaalbala")
                .addLine("Blablabblablaalbala")
                .addLine("Blablabblablaalbala")
                .setBigContentTitle(getString(R.string.apotek_kita))
                .setSummaryText("hehehehe"))
            .setPriority(NotificationCompat.PRIORITY_LOW)

        with(NotificationManagerCompat.from(this)) {
            notify(logoutId, builder.build())
        }

    }



    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_fragment,fragment)
            commit()
        }

}


