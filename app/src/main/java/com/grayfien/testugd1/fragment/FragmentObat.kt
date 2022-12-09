package com.grayfien.testugd1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.ObatAdapter
import com.grayfien.testugd1.RClient
import com.grayfien.testugd1.dataClass.ObatData
import com.grayfien.testugd1.dataClass.ResponseDataObat
import com.grayfien.testugd1.databinding.FragmentObatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class FragmentObat : Fragment() {
    private var _binding: FragmentObatBinding? = null
    private val binding get() = _binding!!
    private val listObat = ArrayList<ObatData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentObatBinding.inflate(inflater, container,false)
        return binding.root
        getDataObat()
    }


    override fun onStart() {
        super.onStart()
        getDataObat()
    }

    private fun getDataObat(){
        binding.rvDataObat.setHasFixedSize(true)
        binding.rvDataObat.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString("cari")
        binding.progressBar.visibility
        RClient.instances.getDataObat(cari).enqueue(object : Callback<ResponseDataObat> {
            override fun onResponse(
                call: Call<ResponseDataObat>,
                response: Response<ResponseDataObat>
            ) {
                if(response.isSuccessful){
                    listObat.clear()
                    response.body()?.let {
                        listObat.addAll(it.data) }
                    val adapter = ObatAdapter (listObat, requireContext())
                    binding.rvDataObat.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<ResponseDataObat>, t: Throwable) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}