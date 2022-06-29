package com.wolking.fortnite.data.friends.dao

import androidx.room.*
import com.wolking.fortnite.data.friends.data_source.Friend

@Dao
interface FriendDao {
    @Query("SELECT * FROM friends")
    fun getAll(): List<Friend>

    @Query("SELECT * FROM friends WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Friend>

    @Query("SELECT * FROM friends WHERE nick_name LIKE :nick_name  LIMIT 1")
    fun findByName(nick_name: String): Friend?

    @Query("SELECT * FROM friends WHERE id LIKE :id  LIMIT 1")
    fun findById(id: Int): Friend?

    @Insert
    fun insert(friend: Friend)

    @Insert
    fun insertAll(vararg friends: Friend)

    @Update
    fun update(friend: Friend)

    @Delete
    fun delete(friend: Friend)
}