package com.grayfien.testugd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.adapter.PasienAdapter
import com.grayfien.testugd1.databinding.ActivityPasienBinding
import com.grayfien.testugd1.fragment.FragmentPasien
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.retrofit.PasienData
import com.grayfien.testugd1.retrofit.RClient
import com.grayfien.testugd1.retrofit.ResponseCreate
import com.grayfien.testugd1.retrofit.ResponseDataPasien
import kotlinx.android.synthetic.main.activity_pasien.*
import kotlinx.android.synthetic.main.pasien_adapter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasienActivity : AppCompatActivity() {

//    val db by lazy { PasienDB(this) }
    lateinit var pasienAdapter: PasienAdapter
    private lateinit var binding: ActivityPasienBinding
    private var b:Bundle? = null
    private val listPasien = ArrayList<PasienData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id_pasien = b?.getInt("id_pasien")
        val nama_pasien = b?.getString("nama_pasien")

        id_pasien?.let { getPasien(it) }

        binding.buttonCreate.setOnClickListener {
            startActivity(Intent(this,
            EditPasienActivity::class.java).apply {
                putExtra("id_pasien", id_pasien)
             })
            intentEdit(0, Constant.TYPE_CREATE)
        }

        binding.txtCari.setOnKeyListener(View.OnKeyListener{ _, keyCode, event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action
                == KeyEvent.ACTION_UP)
            {
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })
//        setupListener()
//        setupRecyclerView()
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

    fun getPasien(id_pasien:Int) {
        RClient.instances.getData(id_pasien).enqueue(object :
            Callback<ResponseDataPasien> {
            override fun onResponse(
                call: Call<ResponseDataPasien>,
                response: Response<ResponseDataPasien>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listPasien.addAll(it.data) }
                    with(binding) {
                        tv_nama_pasien.text = listPasien[0].nama_pasien
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPasien>, t: Throwable) {
            }
        })

    }


//    private fun setupRecyclerView() {
//        pasienAdapter = PasienAdapter(listPasien, object : PasienAdapter.OnAdapterListener{
//            override fun onClick(pasien: PasienData) {
//                intentEdit(pasien.id, Constant.TYPE_READ)
//            }
//            override fun onUpdate(pasien: PasienData) {
//                intentEdit(pasien.id, Constant.TYPE_UPDATE)
//            }
//            override fun onDelete(pasien: PasienData) {
//                pasien.id?.let { it1 -> deleteData(it1) }
//            }
//        })
//        list_note.apply {
//            layoutManager = LinearLayoutManager(applicationContext)
//            adapter = pasienAdapter
//        }
//    }

    override fun onStart() {
        super.onStart()
        this.recreate()
    }

    fun deleteData(id_pasien: Int){
        val builder =
            AlertDialog.Builder(this@PasienActivity)
        builder.setMessage("Are You Sure to delete this data?")
            .setCancelable(false)
            .setPositiveButton("Delete"){dialog, id->
                doDeleteData(id_pasien)
            }
            .setNegativeButton("Cancel"){dialog,id ->
                    dialog.dismiss()
    }
    val alert = builder.create()
        alert.show()
}

    private fun doDeleteData(id_pasien: Int) {
        RClient.instances.deleteData(id_pasien).enqueue(object :
            Callback<ResponseCreate>{
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                            finish()
                }
            }
            override fun onFailure(call: Call<ResponseCreate>, t:
            Throwable) {
            }
        })
    }

    fun intentEdit(pasienId : Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditPasienActivity::class.java)
                .putExtra("intent_id", pasienId)
                .putExtra("intent_type", intentType)
        )
    }


//    fun setupListener() {
//        button_create.setOnClickListener { intentEdit(0, Constant.TYPE_CREATE) }
//    }
//



//    fun loadData() {
//            CoroutineScope(Dispatchers.IO).launch {
//                val pasiens = db.pasienDao().getPasiens()
//                Log.d("MainActivity","dbResponse: $pasiens")
//                withContext(Dispatchers.Main){
//                    pasienAdapter.setData(pasiens)
//                }
//            }
//    }
}