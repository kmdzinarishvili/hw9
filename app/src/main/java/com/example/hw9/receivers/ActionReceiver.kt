package com.example.hw9.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.time.LocalDateTime

class ActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val currentTime: LocalDateTime = LocalDateTime.now()
        Log.d("TEST","Intent action ${intent.action!!}" )
    }
}