package com.example.hw9

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw9.receivers.ActionReceiver
import com.example.hw9.recycler_view.ActionAdapter
import com.example.hw9.recycler_view.ActionItem
import com.example.hw9.recycler_view.ActionViewHolder
import com.example.hw9.recycler_view.enums.ActionState
import com.example.hw9.recycler_view.enums.ActionType
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<ActionViewHolder>
    private lateinit var actionReceiver: ActionReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dataList = listOf(
            ActionItem(ActionType.SCREEN_STATUS_CHANGE, ActionState.TURNED_OFF, LocalDateTime.now()),
            ActionItem(ActionType.SCREEN_STATUS_CHANGE, ActionState.TURNED_ON, LocalDateTime.now()),
            ActionItem(ActionType.CHARGE_STATUS_CHANGE, ActionState.TURNED_OFF, LocalDateTime.now())
        )

        recyclerView = findViewById(R.id.recyclerView)
        adapter = ActionAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        actionReceiver = ActionReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(actionReceiver, intentFilter)
    }
}