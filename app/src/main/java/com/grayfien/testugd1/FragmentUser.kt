package com.grayfien.testugd1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.grayfien.testugd1.databinding.FragmentUserBinding
import com.grayfien.testugd1.package_room.PasienDB
import android.content.Context
import com.grayfien.testugd1.package_room.Pasien
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.android.material.textfield.TextInputEditText

class FragmentUser : Fragment(R.layout.fragment_user) {
    private var binding1 : FragmentUserBinding? = null
    private val binding get() = binding1!!
    private var pasienId: Int = 0


    private lateinit var db: PasienDB
    private lateinit var shareP: Preference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding1 = FragmentUserBinding.inflate(inflater, container, false)

        val rootView: View = inflater.inflate(R.layout.fragment_user, container, false)

        shareP = Preference(requireContext())
        db = PasienDB.getDatabase(requireContext())

        val nama = shareP.getUser()?.name
        val email = shareP.getUser()?.email
        val tglLahir = shareP.getUser()?.tglLahir
        val noTelp = shareP.getUser()?.noTelp


        binding.editNama.setText(nama)
        binding.editEmail.setText(email)
        binding.editTglLahir.setText(tglLahir)
        binding.editNoTelp.setText(noTelp)

        return binding.root

    }


}