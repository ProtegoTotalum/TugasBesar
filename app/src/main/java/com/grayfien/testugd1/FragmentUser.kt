package com.grayfien.testugd1

import android.app.AlertDialog
import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.testugd1.dataClass.ResponseDataUser
import com.grayfien.testugd1.dataClass.UserData
import com.grayfien.testugd1.databinding.FragmentUserBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("UNREACHABLE_CODE")
class FragmentUser : Fragment(){
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
        getDataUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getDataUser()
    }
    private fun getDataUser(){
        val bundle = arguments
        val id = bundle?.getString("id")
        Log.d("retrooo",id.toString())
        RClient.instances.getData(id).enqueue(object : Callback<ResponseDataUser> {
            override fun onResponse(
                call:Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ){
                if(response.isSuccessful){
                    response.body()?.let{
                        listUser.addAll(it.data) }
                    with(binding){
                        editNama.setText(listUser[0].nama)
                        editEmail.setText(listUser[0].email)
                        editTglLahir.setText(listUser[0].tgLahir)
                        editNoTelp.setText(listUser[0].noTelp)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog.apply {
                    setTitle("Confirmation")
                    setMessage("Are You Sure to Delete This Data From ?")
                    setNegativeButton("Cancel") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    setPositiveButton("Delete") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                }
                alertDialog.show()
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupListener() {
        val bundle = arguments
        val id = bundle?.getString("id")
        btnUpdate.setOnClickListener { startActivity(
            Intent(requireActivity(), EditUserActivity::class.java).apply { putExtra("id",id) })  }
    }
}
