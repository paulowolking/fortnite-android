package com.wolking.fortnite.presentation.ui.friends.viewmodel

import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.friends.data_source.Friend
import com.wolking.fortnite.domain.friends.repository.FriendsRepository
import com.wolking.fortnite.presentation.ui.friends.viewmodel.state.FriendsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendRepository: FriendsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState: StateFlow<FriendsUiState> = _uiState.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getFriends() {
        scope.launch {
            val friends = friendRepository.getAll()
//            _uiState.value.friends.
            _uiState.update {
                it.copy(
                    friends = friends
                )
            }
//            uiState.friends.clear()
//            uiState.friends.addAll(friends)
        }
    }

    fun saveFriend() {
        scope.launch {
            val friendState = uiState.value.friend
            if (friendState == null) {
                val friend = Friend(
                    name = uiState.value.nameFriend.value,
                    nickName = uiState.value.nickNameFriend.value,
                    nickPhoto = "https://robohash.org/${(0..100).random()}.png"
                )
                friendRepository.insert(friend = friend)
                getFriends()
            } else {
                friendState.name = uiState.value.nameFriend.value
                friendState.nickName = uiState.value.nickNameFriend.value
                editFriend(friendState)
            }
        }
    }

    private fun editFriend(friend: Friend) {
        scope.launch {
            friendRepository.edit(friend = friend)
            uiState.value.friends.first { it.id == friend.id }.apply {
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