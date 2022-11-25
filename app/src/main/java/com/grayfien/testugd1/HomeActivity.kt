package com.grayfien.testugd1


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class HomeActivity : AppCompatActivity() {

    private var id_user: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        id_user = intent.extras!!.getString("id_user").toString()

        val firstFragment=FragmentHome()
        val secondFragment=FragmentPasien()
        val thirdFragment=FragmentUser()


        setCurrentFragment(firstFragment)


        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuHome->setCurrentFragment(firstFragment)
                R.id.menuPasien-> {
                    val intent = Intent(this, PasienActivity::class.java)
                    startActivity(intent)
                }
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

    fun getId(): String? {
        return id_user
    }

}


