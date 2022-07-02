package com.wolking.fortnite.presentation.friends.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.wolking.fortnite.R
import com.wolking.fortnite.data.friends.data_source.Friend
import com.wolking.fortnite.presentation.theme.FortnaticosShapes


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FriendRow(
    friend: Friend,
    onClickFriend: (friend: Friend) -> Unit,
    onCLickEditFriend: (friend: Friend) -> Unit,
    onCLickEditDelete: (friend: Friend) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        onClick = { onClickFriend(friend) },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = FortnaticosShapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
        ) {
            Image(
                painter = rememberImagePainter(friend.nickPhoto),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .background(color = Color(R.color.colorPrimary))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = friend.name)
                Text(text = friend.nickName)
            }

            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onCLickEditFriend(friend)
                        },
                    painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                    contentDescription = "",
                )

                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onCLickEditDelete(friend)
                        },
                    painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendRowPreview() {
    FriendRow(
        friend = Friend(
            name = "Paulo Henrique",
            nickName = "_wolking",
            nickPhoto = "https://robohash.org/${(0..100).random()}.png"
        ), onClickFriend = {}, onCLickEditFriend = {}, onCLickEditDelete = {})
}