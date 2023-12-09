package com.example.hw9.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.hw9.db_management.DatabaseHelper
import com.example.hw9.recycler_view.ActionItem
import com.example.hw9.recycler_view.enums.ActionState
import com.example.hw9.recycler_view.enums.ActionType
import java.time.LocalDateTime

class ActionReceiver(private val dbHelper: DatabaseHelper) : BroadcastReceiver() {
    private val intentActionMap: HashMap<String, Pair<ActionType, ActionState>> = hashMapOf(
        Intent.ACTION_SCREEN_ON to Pair(ActionType.SCREEN_STATUS_CHANGE, ActionState.TURNED_ON),
        Intent.ACTION_SCREEN_OFF to Pair(ActionType.SCREEN_STATUS_CHANGE, ActionState.TURNED_OFF),
        Intent.ACTION_POWER_CONNECTED to Pair(ActionType.CHARGE_STATUS_CHANGE, ActionState.TURNED_ON),
        Intent.ACTION_POWER_DISCONNECTED to Pair(ActionType.CHARGE_STATUS_CHANGE, ActionState.TURNED_OFF)
    )
    override fun onReceive(context: Context, intent: Intent) {
        val actionTypeAndState = intentActionMap[intent.action]
        val actionType = actionTypeAndState?.first
        val actionState = actionTypeAndState?.second
        val actionTime: LocalDateTime = LocalDateTime.now()
        dbHelper.addAction(ActionItem(null, actionType!!, actionState!!, actionTime))
    }
}