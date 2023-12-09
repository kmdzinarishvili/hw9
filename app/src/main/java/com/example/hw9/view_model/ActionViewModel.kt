package com.example.hw9.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.hw9.db_management.DatabaseHelper
import com.example.hw9.recycler_view.ActionItem

class ActionViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper: DatabaseHelper = DatabaseHelper(application)

    val allActions: LiveData<List<ActionItem>> get() = dbHelper.allActions

    fun getAllActions() {
        dbHelper.getAllActions()
    }
}
