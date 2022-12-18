package com.grayfien.testugd1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.RClient
import com.grayfien.testugd1.SupplierAdapter
import com.grayfien.testugd1.dataClass.ResponseDataSupplier
import com.grayfien.testugd1.dataClass.SupplierData
import com.grayfien.testugd1.databinding.FragmentSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class FragmentSupplier : Fragment() {
    private var _binding: FragmentSupplierBinding? = null
    private val binding get() = _binding!!
    private val listSupplier = ArrayList<SupplierData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSupplierBinding.inflate(inflater, container, false)
        return binding.root
        getDataSupplier()
    }


    override fun onStart() {
        super.onStart()
        getDataSupplier()
    }

    private fun getDataSupplier() {
        binding.rvDataSupplier.setHasFixedSize(true)
        binding.rvDataSupplier.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString("cari")
        binding.progressBar.visibility
        RClient.instances.getDataSupplier(cari).enqueue(object : Callback<ResponseDataSupplier> {
            override fun onResponse(
                call: Call<ResponseDataSupplier>,
                response: Response<ResponseDataSupplier>
            ) {
                if (response.isSuccessful) {
                    listSupplier.clear()
                    response.body()?.let {
                        listSupplier.addAll(it.data)
                    }
                    val adapter = SupplierAdapter(listSupplier, requireContext())
                    binding.rvDataSupplier.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<ResponseDataSupplier>, t: Throwable) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}