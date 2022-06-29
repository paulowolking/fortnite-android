package com.wolking.fortnite

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wolking.fortnite.data.friends.AppDatabase
import com.wolking.fortnite.data.friends.dao.FriendDao
import com.wolking.fortnite.data.friends.data_source.Friend
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class FriendsDaoUnitTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: FriendDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.friendDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertFriend() {
        val nickNameTest = "_wolking"
        val newFriend = Friend(nickName = nickNameTest, name = "Paulo", nickPhoto = "")

        dao.insert(newFriend)
        val friend = dao.findByName(nickNameTest)
        assert(friend?.nickName == nickNameTest)
    }

    @Test
    @Throws(Exception::class)
    fun updateFriend() {
        val nickNameUpdate = "update"
        val nickNameTest = "_wolking"
        val newFriend = Friend(nickName = "nickNameTest", name = "Paulo", nickPhoto = "")

        dao.insert(friend = newFriend)
        var friendSearch = dao.findByName(nickNameTest)

        friendSearch?.nickName = nickNameUpdate
        if (friendSearch != null)
            dao.update(friendSearch)

        friendSearch = dao.findByName(nickNameUpdate)

        assert(friendSearch != null)
    }
}