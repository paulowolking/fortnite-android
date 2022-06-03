package com.wolking.fortnite.presentation.ui.friends.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.database.models.friends.model.Friend
import com.wolking.fortnite.domain.friends.repository.FriendsRepository
import com.wolking.fortnite.presentation.ui.friends.viewmodel.state.FriendsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendRepository: FriendsRepository
) : ViewModel() {

    var uiState by mutableStateOf(value = FriendsUiState())
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getFriends() {
        scope.launch {
            val friends = friendRepository.getAll()
            uiState.friends.clear()
            uiState.friends.addAll(friends)
        }
    }

    fun saveFriend() {
        scope.launch {
            val friendState = uiState.friend
            if (friendState == null) {
                val friend = Friend(
                    name = uiState.nameFriend.value,
                    nickName = uiState.nickNameFriend.value,
                    nickPhoto = "https://robohash.org/${(0..100).random()}.png"
                )
                friendRepository.insert(friend = friend)
                getFriends()
            } else {
                friendState.name = uiState.nameFriend.value
                friendState.nickName = uiState.nickNameFriend.value
                editFriend(friendState)
            }
        }
    }

    private fun editFriend(friend: Friend) {
        scope.launch {
            friendRepository.edit(friend = friend)
            uiState.friends.first { it.id == friend.id }.apply {
                name = friend.name
                nickName = friend.nickName
            }
        }
    }

    fun removeFriend(friend: Friend) {
        scope.launch {
            val isDeleted = friendRepository.delete(friend)
            if (isDeleted) {
                getFriends()
            }
        }
    }
}