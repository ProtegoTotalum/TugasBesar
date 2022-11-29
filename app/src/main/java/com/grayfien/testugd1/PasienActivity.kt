package com.grayfien.testugd1

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.grayfien.testugd1.databinding.ActivityPasienBinding

class PasienActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasienBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        binding.txtCari.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddPasienActivity::class.java))
        }
    }

    fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentPasien()
        val textCari = binding.txtCari.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }
}