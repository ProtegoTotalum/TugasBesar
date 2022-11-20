package com.grayfien.testugd1


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        val firstFragment=FragmentHome()
        val secondFragment=FragmentPasien()
        val thirdFragment=FragmentUser()


        setCurrentFragment(firstFragment)


        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuHome->setCurrentFragment(firstFragment)
                R.id.menuPasien->setCurrentFragment(secondFragment)
                R.id.menuProfil->setCurrentFragment(thirdFragment)
/*              R.id.menuProfil->{
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                }*/
                R.id.menuLogOut->{
                    val builder : AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                    builder.setMessage("Are you sure want to exit?")
                        .setPositiveButton("YES", object : DialogInterface.OnClickListener{
                            override fun onClick(dialogInterface: DialogInterface, i:Int){

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

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_fragment,fragment)
            commit()
        }

}


