package com.grayfien.testugd1


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.grayfien.testugd1.fragment.FragmentHome
import com.grayfien.testugd1.fragment.FragmentObat
import com.grayfien.testugd1.fragment.FragmentPasien
import com.grayfien.testugd1.fragment.FragmentUser
import nl.joery.animatedbottombar.AnimatedBottomBar


class HomeActivity : AppCompatActivity() {

    private var id_user: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        id_user = intent.extras!!.getString("id_user").toString()

        val firstFragment = FragmentHome()
        val secondFragment = FragmentPasien()
        val thirdFragment = FragmentUser()
        val fourthFragment = FragmentObat()


        setCurrentFragment(firstFragment)


        val bottomNavigationView =
            findViewById<AnimatedBottomBar>(R.id.bottomNavigationView)

        bottomNavigationView.onTabSelected = {
            Log.d("bottomNavigationView", "Selected tab: " + it.title)
        }
        bottomNavigationView.onTabReselected = {
            Log.d("bottomNavigationView", "Reselected tab: " + it.title)
        }

        bottomNavigationView.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newTab.id) {
                    R.id.tab_home -> setCurrentFragment(firstFragment)

                    //R.id.tab_pasien -> setCurrentFragment(secondFragment)
                    R.id.tab_pasien -> {
                        val intent = Intent(this@HomeActivity, PasienActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.tab_obat -> {
                        val intent = Intent(this@HomeActivity, ObatActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.tab_supplier -> {
                        val intent = Intent(this@HomeActivity, SupplierActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.tab_profil -> setCurrentFragment(thirdFragment)
//                    R.id.tab_profil -> {
//                        val intent = Intent(this@HomeActivity, UserActivity::class.java)
//                        startActivity(intent)
//                    }

                    R.id.logout -> {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                        builder.setMessage("Are you sure want to exit?")
                            .setPositiveButton("YES", object : DialogInterface.OnClickListener {
                                override fun onClick(dialogInterface: DialogInterface, i: Int) {

                                    //Keluar dari aplikasi
                                    finishAndRemoveTask()
                                }
                            })
                            .show()
                    }
                }
            }
        })

//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.menuHome -> setCurrentFragment(firstFragment)
//                R.id.menuPasien -> {
//                    val intent = Intent(this, PasienActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.menuProfil -> setCurrentFragment(thirdFragment)
///*              R.id.menuProfil->{
//                    val intent = Intent(this, UserActivity::class.java)
//                    startActivity(intent)
//                }*/
//                R.id.menuLogOut -> {
//                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
//                    builder.setMessage("Are you sure want to exit?")
//                        .setPositiveButton("YES", object : DialogInterface.OnClickListener {
//                            override fun onClick(dialogInterface: DialogInterface, i: Int) {
//
//                                //Keluar dari aplikasi
//                                finishAndRemoveTask()
//                            }
//                        })
//                        .show()
//                }
//
//            }
//            true
//        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_fragment, fragment)
            commit()
        }

    fun getId(): String? {
        return id_user
    }

}


