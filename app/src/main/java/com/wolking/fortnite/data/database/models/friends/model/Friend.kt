package com.wolking.fortnite.data.database.models.friends.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friend(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "nick_name") var nickName: String,
    @ColumnInfo(name = "nick_photo") val nickPhoto: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}