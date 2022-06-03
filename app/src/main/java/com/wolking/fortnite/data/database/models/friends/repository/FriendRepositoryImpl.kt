package com.wolking.fortnite.data.database.models.friends.repository

import com.wolking.fortnite.data.database.AppDatabase
import com.wolking.fortnite.data.database.models.friends.model.Friend
import com.wolking.fortnite.domain.friends.repository.FriendsRepository

class FriendRepositoryImpl(
    private val appDatabase: AppDatabase
) : FriendsRepository {
    override fun insert(friend: Friend): Boolean {
        appDatabase.friendDao().insert(friend)
        return true
    }

    override fun getAll(): List<Friend> {
        return appDatabase.friendDao().getAll()
    }

    override fun findByName(nickName: String): Friend? {
        return appDatabase.friendDao().findByName(nickName)
    }

    override fun delete(friend: Friend): Boolean {
        appDatabase.friendDao().delete(friend)
        return true
    }

    override fun edit(friend: Friend): Boolean {
        appDatabase.friendDao().updateFriend(friend = friend)
        return true
    }
}