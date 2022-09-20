package com.grayfien.testugd1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.testugd1.package_room.Pasien


class FragmentPasien : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasien, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        //val adapter : RVPasienAdapter = RVPasienAdapter(Pasien.listOfPasien)

        val rvPasien : RecyclerView = view.findViewById(R.id.rv_pasien)

        rvPasien.layoutManager = layoutManager

        rvPasien.setHasFixedSize(true)

        //rvPasien.adapter = adapter
    }

}