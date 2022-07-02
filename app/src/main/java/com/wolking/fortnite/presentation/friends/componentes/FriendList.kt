package com.wolking.fortnite.presentation.friends.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.wolking.fortnite.data.friends.data_source.Friend
import com.wolking.fortnite.presentation.friends.viewmodel.FriendsViewModel

@Composable
fun FriendsList(
    viewModel: FriendsViewModel,
    onClickFriend: (friend: Friend) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    uiState.friend = null
                    uiState.nameFriend.value = ""
                    uiState.nickNameFriend.value = ""
                    uiState.showDialogFriend.value = true
                },
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }, content = {
            if (uiState.showDialogFriend.value) {
                DialogFriendTextField(
                    friend = uiState.friend,
                    name = uiState.nameFriend,
                    nickName = uiState.nickNameFriend,
                    onDismiss = { uiState.showDialogFriend.value = false },
                    onConfirmAdd = {
                        uiState.showDialogFriend.value = false
                        viewModel.saveFriend()
                    })
            }

            LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)) {
                items(uiState.friends) { friend ->
                    FriendRow(friend = friend, onClickFriend = onClickFriend,
                        onCLickEditFriend = {
                            uiState.friend = it
                            uiState.nameFriend.value = it.name
                            uiState.nickNameFriend.value = it.nickName
                            uiState.showDialogFriend.value = true
                        },
                        onCLickEditDelete = { viewModel.removeFriend(it) })
                }
            }

            if (uiState.friends.isEmpty()) {
                EmptyList(message = "Nenhum item encontrado")
            }
        })
}