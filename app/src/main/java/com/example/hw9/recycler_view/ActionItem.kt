package com.example.hw9.recycler_view

import com.example.hw9.recycler_view.enums.ActionState
import com.example.hw9.recycler_view.enums.ActionType
import java.time.LocalDateTime

data class ActionItem (val actionId: Long?, val actionType: ActionType, val actionState: ActionState, val actionTime: LocalDateTime)