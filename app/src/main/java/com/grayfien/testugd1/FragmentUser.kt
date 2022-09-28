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
    private val edit : EditActivity? = null


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

       /* binding.btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.pasienDao().updatePasien(pasienId,
                editNama.text.toString(),
                editEmail.text.toString(),
                editTglLahir.text.toString(),
                editNoTelp.text.toString())
            }
        }

        fun getPasien(){
            CoroutineScope(Dispatchers.IO).launch {
                val notes = db.pasienDao().getPasienID()
                editNama.setText("")
                editEmail.setText("")
                editTglLahir.setText("")
                editNoTelp.setText("")
            }
        }
        */

    /*
        binding.btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val id = shareP.getUser()?.id
                val nama = binding.tampilNama.getEditText()?.getText().toString()
                val username = binding.tampilUsername.getEditText()?.getText().toString()
                val pass = binding.tampilPas.getEditText()?.getText().toString()
                val email = binding.tampilEmail.getEditText()?.getText().toString()
                val tglLahir = binding.tampilTglLahir.getEditText()?.getText().toString()
                val noHP = binding.tampilNoTelp.getEditText()?.getText().toString()
                db.pasienDao().updatePasien(id,nama,username,pass,email,tglLahir,noHP)
                val userPasien = db.pasienDao().getPasienID(id)
                shareP.setUser(user)



                withContext(Dispatchers.Main){
                    Toast.makeText(getActivity(), "Data berhasil di Edit", Toast.LENGTH_SHORT).show()
                    binding.textNamaUser.setText(nama)
                    binding.tietNamaLengkap.setText(nama)
                    binding.tietTglLahir.setText(tglLahir)
                    binding.tietNoHP.setText(noHP)
                    binding.tietEmail.setText(email)
                }

     */



        return binding.root

    }



}