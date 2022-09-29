package com.grayfien.testugd1


import android.app.AlertDialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.R.layout.fragment_pasien
import com.grayfien.testugd1.package_room.Constant


import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentPasien : Fragment() {
    val db by lazy {PasienDB(requireActivity())}
    lateinit var pasienAdapter: RVPasienAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_pasien, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        pasienAdapter = RVPasienAdapter(arrayListOf(), object :
            RVPasienAdapter.OnAdapterListener{
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
        rv_pasien.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = pasienAdapter
        }
    }

    private fun deleteDialog(pasien: Pasien){
        val alertDialog = AlertDialog.Builder(requireActivity())
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
        if (::pasienAdapter.isInitialized) {
            CoroutineScope(Dispatchers.Default).launch {
                val pasiens = db.pasienDao().getAllPasien()
                Log.d("FragmentPasien","dbResponse: $pasiens")
                withContext(Dispatchers.Main){
                    pasienAdapter.setData(pasiens)
                }
            }
        }

    }

    fun intentEdit(pasienId : Int, intentType: Int) = startActivity(
        Intent(requireActivity(), EditUserActivity::class.java)
            .putExtra("intent_id", pasienId)
            .putExtra("intent_type", intentType)
    )

}