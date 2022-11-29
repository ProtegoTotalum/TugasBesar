package com.grayfien.testugd1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()

    }

    fun setupListener() {
        btnLocation.setOnClickListener {
            startActivity(
                Intent(requireActivity(), LocationActivity::class.java)
            )
        }

        btnQR.setOnClickListener {
            startActivity(
                Intent(requireActivity(), QrActivity::class.java)
            )
        }

        btnPieChart.setOnClickListener {
            startActivity(
                Intent(requireActivity(), PieChartActivity::class.java)
            )
        }
    }

}