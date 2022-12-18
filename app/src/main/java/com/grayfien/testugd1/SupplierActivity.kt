package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.grayfien.testugd1.databinding.ActivitySupplierBinding
import com.grayfien.testugd1.fragment.FragmentHome
import com.grayfien.testugd1.fragment.FragmentSupplier


class SupplierActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySupplierBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        binding.txtCariSupplier.setOnKeyListener(View.OnKeyListener{ _, keyCode, event->if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
            showDataFragment()
            return@OnKeyListener true
        }
            false
        })

        binding.btnAddSupplier.setOnClickListener{
            startActivity(Intent(this,AddSupplierActivity::class.java))
        }
        binding.btnCancelSupplier.setOnClickListener{
            startActivity(Intent(this, FragmentHome::class.java))
        }
    }

    fun showDataFragment (){
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentSupplier()
        val textCari = binding.txtCariSupplier.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data_supplier, mFragment).commit()
    }
}