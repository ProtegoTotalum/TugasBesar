package com.grayfien.testugd1.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.adapter.PasienAdapter
import com.grayfien.testugd1.databinding.FragmentPasienBinding
import com.grayfien.testugd1.retrofit.PasienData
import com.grayfien.testugd1.retrofit.RClient
import com.grayfien.testugd1.retrofit.ResponseDataPasien
import kotlinx.android.synthetic.main.activity_pasien.*
import kotlinx.android.synthetic.main.fragment_pasien.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class FragmentPasien : Fragment() {
//    val db by lazy { PasienDB(requireActivity()) }

    lateinit var pasienAdapter: PasienAdapter
    private var _binding: FragmentPasienBinding? = null
    private val binding get() = _binding!!
    private val listPasien = ArrayList<PasienData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasienBinding.inflate(inflater,
            container, false)
        return binding.root
        getDataPasien()
//        return inflater.inflate(fragment_pasien, container, false)
    }

    private fun getDataPasien() {
        binding.listPasienFragment.setHasFixedSize(true)
        binding.listPasienFragment.layoutManager=LinearLayoutManager(context)

        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")

        binding.progressBar.visibility

        RClient.instances.getSearch(cari).enqueue(object : Callback<ResponseDataPasien> {
            override fun onResponse(
                call: Call<ResponseDataPasien>,
                response: Response<ResponseDataPasien>
            ){
                if (response.isSuccessful){
                    listPasien.clear()
                    response.body()?.let {
                        listPasien.addAll(it.data) }
                    val adapter =
                        PasienAdapter(listPasien, requireContext())
                    binding.listPasienFragment.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDataPasien>, t:
            Throwable) {
            }
        }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    private fun setupRecyclerView() {
//        pasienAdapter = PasienAdapter(arrayListOf(), object :
//            PasienAdapter.OnAdapterListener {
//            override fun onClick(pasien: PasienData) {
//                //Toast.makeText(applicationContext, memo.title, Toast.LENGTH_SHORT).show()
//                intentEdit(pasien.id, Constant.TYPE_READ)
//            }
//            override fun onUpdate(pasien: Pasien) {
//                intentEdit(pasien.id, Constant.TYPE_UPDATE)
//            }
//            override fun onDelete(pasien: Pasien) {
//                deleteDialog(pasien)
//            }
//        })
//        list_pasien_fragment.apply {
//            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
//            adapter = pasienAdapter
//        }
//    }

    //    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupListener()
//        setupRecyclerView()
//    }

    //    fun setupListener() {
//        button_create_fragment.setOnClickListener { intentEdit(0, Constant.TYPE_CREATE) }
//    }
//
//    fun intentEdit(pasienId : Int, intentType: Int) {
//        startActivity(
//            Intent(requireActivity(), EditPasienActivity::class.java)
//                .putExtra("intent_id", pasienId)
//                .putExtra("intent_type", intentType)
//        )
//    }

}

//    private fun deleteDialog(pasien: Pasien){
//        val alertDialog = AlertDialog.Builder(requireActivity())
//        alertDialog.apply {
//            setTitle("Confirmation")
//            setMessage("Are You Sure to Delete This Data From ${pasien.name}?")
//            setNegativeButton("Cancel") { dialogInterface, _ ->
//                dialogInterface.dismiss()
//            }
//            setPositiveButton("Delete") { dialogInterface, _ ->
//                dialogInterface.dismiss()
//                CoroutineScope(Dispatchers.IO).launch {
//                    db.pasienDao().deletePasien(pasien)
//                    loadData()
//                }
//            }
//        }
//        alertDialog.show()
//    }
//    override fun onStart() {
//        super.onStart()
//        loadData()
//    }

//    fun loadData() {
//        if (::pasienAdapter.isInitialized) {
//            CoroutineScope(Dispatchers.IO).launch {
//                val pasiens = db.pasienDao().getPasiens()
//                Log.d("MainActivity", "dbResponse: $pasiens")
//                withContext(Dispatchers.Main) {
//                    pasienAdapter.setData(pasiens)
//                }
//            }
//        }
//    }

//}