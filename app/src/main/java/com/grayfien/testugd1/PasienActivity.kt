package com.grayfien.testugd1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.activity_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasienActivity : AppCompatActivity() {

    val db by lazy { PasienDB(this) }
    lateinit var pasienAdapter: PasienAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasien)
        setupListener()
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        pasienAdapter = PasienAdapter(arrayListOf(), object :
            PasienAdapter.OnAdapterListener{
            override fun onClick(pasien: Pasien) {
                //Toast.makeText(applicationContext, memo.title, Toast.LENGTH_SHORT).show()
                intentEdit(pasien.id, Constant.TYPE_READ)
            }
            override fun onUpdate(pasien: Pasien) {
                intentEdit(pasien.id, Constant.TYPE_UPDATE)
            }
            override fun onDelete(pasien: Pasien) {
                deleteDialog(pasien)
            }
        })
        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = pasienAdapter
        }
    }

    private fun deleteDialog(pasien: Pasien){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to delete this data From${pasien.name}?")
            setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Delete") { dialogInterface, _ ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.pasienDao().deletePasien(pasien)
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
            CoroutineScope(Dispatchers.IO).launch {
                val pasiens = db.pasienDao().getPasiens()
                Log.d("MainActivity","dbResponse: $pasiens")
                withContext(Dispatchers.Main){
                    pasienAdapter.setData(pasiens)
                }
            }
    }

    fun setupListener() {
        button_create.setOnClickListener { intentEdit(0, Constant.TYPE_CREATE) }
    }

    fun intentEdit(pasienId : Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditPasienActivity::class.java)
                .putExtra("intent_id", pasienId)
                .putExtra("intent_type", intentType)
        )
    }
}