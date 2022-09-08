package com.grayfien.testugd1


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val firstFragment=FragmentHome()
        val secondFragment=FragmentPasien()

        setCurrentFragment(firstFragment)
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuHome->setCurrentFragment(firstFragment)
                R.id.menuPasien->setCurrentFragment(secondFragment)
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

    }
}

