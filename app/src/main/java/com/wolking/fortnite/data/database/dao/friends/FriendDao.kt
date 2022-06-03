package com.wolking.fortnite.data.database.dao.friends

import androidx.room.*
import com.wolking.fortnite.data.database.models.friends.model.Friend

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
    fun updateFriend(friend: Friend)

    @Delete
    fun delete(friend: Friend)
}