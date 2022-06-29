package com.wolking.fortnite.presentation.ui.friends.viewmodel.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.wolking.fortnite.data.friends.data_source.Friend

data class FriendsUiState(
    var friends: List<Friend> = arrayListOf(),
    var friend: Friend? = null,
    var showDialogFriend: MutableState<Boolean> = mutableStateOf(false),
    var nameFriend: MutableState<String> = mutableStateOf(""),
    var nickNameFriend: MutableState<String> = mutableStateOf(""),
    var loading: Boolean = false
)