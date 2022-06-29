package com.wolking.fortnite.data.friends

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wolking.fortnite.data.friends.dao.FriendDao
import com.wolking.fortnite.data.friends.data_source.Friend

@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}
