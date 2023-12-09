package com.example.hw9.db_management

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.hw9.recycler_view.ActionItem
import com.example.hw9.recycler_view.enums.ActionState
import com.example.hw9.recycler_view.enums.ActionType
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DatabaseHelper(private val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val allActions: MutableLiveData<List<ActionItem>> = MutableLiveData()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIONS")
        onCreate(db)
    }
    fun addAction(action: ActionItem): Long {
        val values = ContentValues()
        values.put(COLUMN_ACTION_TYPE, action.actionType.toString())
        values.put(COLUMN_ACTION_STATE, action.actionState.toString())
        values.put(COLUMN_ACTION_TIME, action.actionTime.toString())
        val db = this.writableDatabase
        db.beginTransaction()
        val newRowId = db.insert(TABLE_ACTIONS, null, values)
        Log.d("TEST", "actionState ${action.actionState}")
        Log.d("TEST", "actionType${action.actionType}")

        db.setTransactionSuccessful()
        db.endTransaction()
        db.close()
        getAllActions()
        return newRowId
    }

    fun getAllActions(): List<ActionItem> {
        val actions = mutableListOf<ActionItem>()
        val db = readableDatabase

        val projection = arrayOf(
            COLUMN_ID,
            COLUMN_ACTION_TYPE,
            COLUMN_ACTION_STATE,
            COLUMN_ACTION_TIME
        )

        val cursor = db.query(
            TABLE_ACTIONS,
            projection,
            null,
            null,
            null,
            null,
            "$COLUMN_ACTION_TIME DESC"
        )

        while (cursor.moveToNext()) {
            try{
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTION_TYPE))
                val state = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTION_STATE))
                val timeString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTION_TIME))
                val time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_DATE_TIME)

                actions.add(ActionItem(id, ActionType.valueOf(type), ActionState.valueOf(state), time))
            } catch (e:Exception){
                Log.d("TEST", e.toString())
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        cursor.close()
        db.close()
        allActions.postValue(actions)
        return actions
    }


    companion object {
        private const val DATABASE_NAME = "action_history.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_ACTIONS = "actions"
        private const val COLUMN_ID = "action_id"
        private const val COLUMN_ACTION_TYPE = "action_type"
        private const val COLUMN_ACTION_STATE = "action_state"
        private const val COLUMN_ACTION_TIME = "action_time"

        private const val TABLE_CREATE = "CREATE TABLE $TABLE_ACTIONS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_ACTION_TYPE TEXT, " +
                "$COLUMN_ACTION_STATE TEXT, " +
                "$COLUMN_ACTION_TIME TEXT);"
    }
}
