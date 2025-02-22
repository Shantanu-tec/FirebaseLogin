package com.drm.allinone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drm.allinone.repository.AuthRepository
import com.drm.allinone.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val loginMutableLiveData: Channel<Resource<AuthResult>> = Channel()

    val loginLiveData: Flow<Resource<AuthResult>>
        get() = loginMutableLiveData.consumeAsFlow()


    fun loginUser(userName: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(userName, password).collect { result ->
                loginMutableLiveData.send(result)
            }
        }
    }


}

//ViewModel - Act as bridge between model and view : Repository : Api ya local