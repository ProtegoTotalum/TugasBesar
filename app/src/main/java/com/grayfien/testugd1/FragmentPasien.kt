package com.grayfien.testugd1


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grayfien.testugd1.R.layout.fragment_pasien
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.databinding.FragmentPasienBinding
import com.grayfien.testugd1.package_room.Constant
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.activity_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentPasien : Fragment() {
    val db by lazy { PasienDB(requireActivity()) }
    lateinit var pasienAdapter: PasienAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(fragment_pasien, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        list_pasien_fragment.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = pasienAdapter
        }
    }

    private fun deleteDialog(pasien: Pasien){
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to Delete This Data From ${pasien.name}?")
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
        if (::pasienAdapter.isInitialized) {
            CoroutineScope(Dispatchers.IO).launch {
                val pasiens = db.pasienDao().getPasiens()
                Log.d("MainActivity", "dbResponse: $pasiens")
                withContext(Dispatchers.Main) {
                    pasienAdapter.setData(pasiens)
                }
            }
        }
    }

    fun setupListener() {
        button_create_fragment.setOnClickListener { intentEdit(0, Constant.TYPE_CREATE) }
    }

    fun intentEdit(pasienId : Int, intentType: Int) {
        startActivity(
            Intent(requireActivity(), EditPasienActivity::class.java)
                .putExtra("intent_id", pasienId)
                .putExtra("intent_type", intentType)
        )
    }

}