package com.example.hw9

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw9.db_management.DatabaseHelper
import com.example.hw9.receivers.ActionReceiver
import com.example.hw9.recycler_view.ActionAdapter
import com.example.hw9.view_model.ActionViewModel
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActionAdapter
    private lateinit var actionReceiver: ActionReceiver
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var actionViewModel: ActionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DatabaseHelper(this)
        actionViewModel = ViewModelProvider(this)[ActionViewModel::class.java]

        val actions = dbHelper.getAllActions()

        recyclerView = findViewById(R.id.recyclerView)
        adapter = ActionAdapter(actions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        actionReceiver = ActionReceiver(dbHelper)
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(actionReceiver, intentFilter)

        dbHelper.allActions.observe(this) { newActions ->
            adapter.updateData(newActions)
        }

    }
}