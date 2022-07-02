package com.wolking.fortnite.presentation.friends.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.wolking.fortnite.data.friends.data_source.Friend

@Composable
fun DialogFriendTextField(
    friend: Friend?,
    name: MutableState<String>,
    nickName: MutableState<String>,
    onConfirmAdd: () -> Unit,
    onDismiss: () -> Unit
) {
    val imageFriend by rememberSaveable { mutableStateOf("https://robohash.org/${(0..100).random()}.png") }
    var isErrorName by rememberSaveable { mutableStateOf(false) }
    var isErrorNickname by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(5.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = friend?.name ?: "Novo amigo",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Image(
                    painter = rememberImagePainter(
                        friend?.nickPhoto ?: imageFriend
                    ),
                    contentDescription = "logo friend",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = name.value,
                    onValueChange = { name.value = it },
                    singleLine = true,
                    label = { Text(text = "Nome") },
                    placeholder = { Text(text = "Nome") },
                    isError = isErrorName,
                    keyboardActions = KeyboardActions {
                        isErrorName = name.value.isEmpty()
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = nickName.value,
                    onValueChange = { nickName.value = it },
                    singleLine = true,
                    label = { Text(text = "NickName") },
                    placeholder = { Text(text = "NickName") },
                    isError = isErrorNickname,
                    keyboardActions = KeyboardActions {
                        isErrorNickname = nickName.value.isEmpty()
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        isErrorName = name.value.isEmpty()
                        isErrorNickname = nickName.value.isEmpty()
                        if (!isErrorName && !isErrorNickname) {
                            onConfirmAdd.invoke()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "Salvar", color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
@Preview
fun DialogFriendTextFieldPreview() {
    DialogFriendTextField(
        friend = Friend(name = "Paulo", nickName = "wolking_", nickPhoto = ""),
        name = mutableStateOf(""),
        nickName = mutableStateOf(""),
        onConfirmAdd = {},
        onDismiss = {}
    )
}