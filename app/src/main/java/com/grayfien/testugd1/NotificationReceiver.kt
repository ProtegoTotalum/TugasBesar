package com.grayfien.testugd1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shashank.sony.fancytoastlib.FancyToast

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val message = intent.getStringExtra("toastMessage")
        FancyToast.makeText(
            context!!,
            message,
            FancyToast.LENGTH_LONG,
            FancyToast.INFO,
            false
        ).show()
    }
}