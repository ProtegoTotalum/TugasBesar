package com.grayfien.testugd1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.package_room.Pasien
import com.grayfien.testugd1.package_room.PasienDB
import kotlinx.android.synthetic.main.fragment_pasien.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPasien : Fragment() {

    val db by lazy { PasienDB(requireContext()) }
    lateinit var pasienAdapter: RVPasienAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasien, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pasienAdapter = RVPasienAdapter(arrayListOf(), object : RVPasienAdapter.OnAdapterListener{
            override fun onClick(pasien: Pasien) {
                TODO("Not yet implemented")
            }

            override fun onUpdate(pasien: Pasien) {
                TODO("Not yet implemented")
            }

            override fun onDelete(pasien: Pasien) {
                TODO("Not yet implemented")
            }
        })

        rv_pasien.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pasienAdapter
        }
    }

    fun intentEdit(pasienID : Int, intentType: Int){

        startActivity(
            Intent(activity, RegisterActivity::class.java)
                .putExtra("intent_id", pasienID)
                .putExtra("intent_type", intentType)
        )

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    //untuk load data yang tersimpan pada database yang sudah create data
    fun loadData() { CoroutineScope(Dispatchers.IO).launch {

        val pasiens = db?.pasienDao()?.getAllPasien()

        Log.d("FragmentPasien","dbResponse: $pasiens")
        withContext(Dispatchers.Main){
//            if (pasiens != null) {
            pasiens?.let { pasienAdapter.setData(it) }
//            }
//            else {
//                Toast.makeText(requireContext(), "Data masih kosong", Toast.LENGTH_SHORT).show()
//            }
        }

        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val layoutManager = LinearLayoutManager(context)
//        //val adapter : RVPasienAdapter = RVPasienAdapter(Pasien.listOfPasien)
//
//        val rvPasien : RecyclerView = view.findViewById(R.id.rv_pasien)
//
//        rvPasien.layoutManager = layoutManager
//
//        rvPasien.setHasFixedSize(true)
//
//        //rvPasien.adapter = adapter
//    }

}