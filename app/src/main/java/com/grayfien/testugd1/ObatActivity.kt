package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.grayfien.testugd1.databinding.ActivityObatBinding
import com.grayfien.testugd1.fragment.FragmentObat


class ObatActivity : AppCompatActivity() {
    private lateinit var binding : ActivityObatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        binding.txtCariObat.setOnKeyListener(View.OnKeyListener{ _, keyCode, event->if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
            showDataFragment()
            return@OnKeyListener true
        }
            false
        })

        binding.btnAddObat.setOnClickListener{
            startActivity(Intent(this,AddObatActivity::class.java))
        }
        binding.btnCancelObat.setOnClickListener{
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    fun showDataFragment (){
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentObat()
        val textCari = binding.txtCariObat.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data_obat, mFragment).commit()
    }
}