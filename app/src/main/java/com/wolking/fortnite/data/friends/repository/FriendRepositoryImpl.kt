package com.wolking.fortnite.data.friends.repository

import com.wolking.fortnite.data.friends.AppDatabase
import com.wolking.fortnite.data.friends.data_source.Friend
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
        appDatabase.friendDao().update(friend = friend)
        return true
    }
}