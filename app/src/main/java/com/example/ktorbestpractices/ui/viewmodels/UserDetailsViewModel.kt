package com.example.ktorbestpractices.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorbestpractices.data.models.UserModel
import com.example.ktorbestpractices.data.repositories.GetUsersResponse
import com.example.ktorbestpractices.data.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    val userRepository: UsersRepository
): ViewModel() {

    private val _users: MutableLiveData<UserDetailsState> = MutableLiveData()
    val users: LiveData<UserDetailsState> = _users

    fun fetch() {
        viewModelScope.launch {
            when(val result = userRepository.getUsers()){
                is GetUsersResponse.Error -> {

                }
                is GetUsersResponse.Success -> {
                    _users.value = users.value?.copy(
                        users = result.users
                    ) ?: UserDetailsState(result.users)
                }
            }
        }
    }

}

data class UserDetailsState(
    val users: List<UserModel>
)