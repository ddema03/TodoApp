package com.example.todoapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDate(data: Date): Long{
        return data.time
    }
    @TypeConverter
    fun toDate(time: Long) : Date{
        return Date(time)
    }
}