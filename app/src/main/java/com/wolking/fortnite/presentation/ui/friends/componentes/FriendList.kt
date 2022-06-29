package com.wolking.fortnite.presentation.ui.friends.componentes

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wolking.fortnite.presentation.ui.friends.viewmodel.FriendsViewModel

@Composable
fun FriendsList(viewModel: FriendsViewModel) {
    val context = LocalContext.current
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
                    showDialog = uiState.showDialogFriend.value,
                    onDismiss = { uiState.showDialogFriend.value = false },
                    onConfirmAdd = {
                        uiState.showDialogFriend.value = false
                        viewModel.saveFriend()
                    })
            }

            LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)) {
                items(uiState.friends) { friend ->
                    FriendRow(friend = friend, onClickFriend = {
                        Toast.makeText(context, friend.nickName, Toast.LENGTH_LONG).show()
                    }, onCLickEditFriend = {
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