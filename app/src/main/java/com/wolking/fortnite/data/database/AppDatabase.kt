package com.wolking.fortnite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wolking.fortnite.data.database.dao.friends.FriendDao
import com.wolking.fortnite.data.database.models.friends.model.Friend

@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}
