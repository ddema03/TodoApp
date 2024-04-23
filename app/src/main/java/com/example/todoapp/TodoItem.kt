package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var id: UUID = UUID.randomUUID(),
    var title: String,
    var createdAt: Date = Date()
)

