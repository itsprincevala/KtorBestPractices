package com.example.ktorbestpractices.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktorbestpractices.data.models.UserModel
import com.example.ktorbestpractices.ui.viewmodels.UserDetailsViewModel

@Composable
fun UserListScreen(viewModel: UserDetailsViewModel) {
    val state = viewModel.users.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetch()
    }

    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Users(${state.value?.users?.size ?: "0"})",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            style = TextStyle(
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
        state.value?.users?.let { users ->
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                items(users) { user ->
                    UserItem(user = user)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: UserModel) {

    Row(
        Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Column(
            Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .weight(1f)) {
            Text(
                text = user.firstName + " " + user.lastName,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Text(
                text = user.email,
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
            )
        }
    }
}