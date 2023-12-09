package com.example.hw9.db_management

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hw9.recycler_view.enums.ActionState
import com.example.hw9.recycler_view.enums.ActionType
import java.time.LocalDateTime


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIONS")
        onCreate(db)
    }
    fun addAction(actionType: ActionType, actionState: ActionState, actionTime: LocalDateTime): Long {
        val values = ContentValues()
        values.put(COLUMN_ACTION_TYPE, actionType.toString())
        values.put(COLUMN_ACTION_TYPE, actionState.toString())
        values.put(COLUMN_ACTION_TIME, actionTime.toString())
        val db = this.writableDatabase
        val newRowId = db.insert(TABLE_ACTIONS, null, values)
        db.close()
        return newRowId
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
