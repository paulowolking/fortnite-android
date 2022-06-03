package com.wolking.fortnite.presentation.ui.friends.viewmodel.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.wolking.fortnite.data.database.models.friends.model.Friend

data class FriendsUiState(
    var friends: MutableList<Friend> = mutableStateListOf(),
    var friend: Friend? = null,
    var showDialogFriend: MutableState<Boolean> = mutableStateOf(false),
    var nameFriend: MutableState<String> = mutableStateOf(""),
    var nickNameFriend: MutableState<String> = mutableStateOf(""),
    var loading: Boolean = false
)