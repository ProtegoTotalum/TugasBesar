package com.grayfien.testugd1.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback
import com.grayfien.testugd1.*
import com.grayfien.testugd1.dataClass.ResponseDataUser
import com.grayfien.testugd1.dataClass.UserData
import com.grayfien.testugd1.databinding.FragmentUserBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.btnImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Suppress("UNREACHABLE_CODE")
class FragmentUser : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private var b: Bundle? = null
    private val listUser = ArrayList<UserData>()
    private var id_user: String = ""
    private var dateString: String = ""
    private lateinit var shareP: Preference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity:HomeActivity? = activity as HomeActivity?
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        id_user = activity!!.getId().toString()

        id_user?.let{ getDataUser(it)}

        binding.edittvTglLahirUser.setOnClickListener {
            val callback = SingleDayPickCallback {
                    singleDay ->
                binding.editTglLahir.text =
                    dateToString(singleDay.dayOfMonth,singleDay.month,singleDay.year)
            }

            val date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"))

            val pickedDay = CivilCalendar().also {
                it.year = date.year
                it.month = date.monthValue-1
                it.dayOfMonth = date.dayOfMonth
            }

            val datePicker = PrimeDatePicker.dialogWith(pickedDay)
                .pickSingleDay(callback)
                .initiallyPickedSingleDay(pickedDay)
                .build()
            datePicker.show(childFragmentManager, "DD/MM/YYYY")
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(requireActivity(), EditUserActivity::class.java)
            intent.putExtra("id_user", id_user)
            startActivity(intent)
        }

        binding.btnImage.setOnClickListener {
            val intent = Intent(requireActivity(), CameraActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun dateToString(dayofMonth: Int, month: Int, year: Int): String {
        return dayofMonth.toString()+"/"+(month+1)+"/"+year.toString()
    }

    private fun getTanggalLahir(tgLahir: String): String {
        return tgLahir
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareP = Preference(requireContext())
    }

    private fun getDataUser(id:String){
        val id_user = b?.getString("id")
        id_user?.let{ getDataUser(it)}

        Log.d("retrooo",id)
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
                        dateString = getTanggalLahir(listUser[0].tgLahir)
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
