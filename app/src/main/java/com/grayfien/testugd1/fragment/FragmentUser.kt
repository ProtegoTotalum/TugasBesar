package com.grayfien.testugd1.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grayfien.testugd1.EditUserActivity
import com.grayfien.testugd1.HomeActivity
import com.grayfien.testugd1.Preference
import com.grayfien.testugd1.RClient
import com.grayfien.testugd1.dataClass.ResponseDataUser
import com.grayfien.testugd1.dataClass.UserData
import com.grayfien.testugd1.databinding.FragmentUserBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("UNREACHABLE_CODE")
class FragmentUser : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private var b: Bundle? = null
    private val listUser = ArrayList<UserData>()
    private var id_user: String = ""
    private lateinit var shareP: Preference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity: HomeActivity? = activity as HomeActivity?
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        id_user = activity!!.getId().toString()

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(requireActivity(), EditUserActivity::class.java)
            intent.putExtra("id_user", id_user)
            startActivity(intent)
        }
        return binding.root

        getDataUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareP = Preference(requireContext())
    }

    override fun onStart() {
        super.onStart()
        getDataUser()
    }

    private fun getDataUser() {
        val id_user = b?.getString("id")

        val token_auth = "Bearer ${shareP.getToken()}"

        Log.d("retrooo", id.toString())
        RClient.instances.getData(token_auth, id_user).enqueue(object : Callback<ResponseDataUser> {
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listUser.addAll(it.data)
                    }
                    with(binding) {
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
}
