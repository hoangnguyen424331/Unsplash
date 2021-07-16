package com.example.unsplash.data.source.local.sqlite.database

import android.content.Context
import androidx.room.Room
import com.example.unsplash.utils.Constant

class DatabaseBuilder {

    fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            Constant.DATABASE_NAME
        ).build()
}
