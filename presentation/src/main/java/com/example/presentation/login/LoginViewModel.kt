package com.example.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.login.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {
    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    postSideEffect(LoginSideEffect.Toast(msg = throwable.message.orEmpty()))
                }
            }
        }

    )

    fun onLoginClick() = intent {
        val id = state.id
        val pwd = state.pwd
        val token = loginUseCase(id, pwd).getOrThrow()
        setTokenUseCase(token)
        postSideEffect(LoginSideEffect.NavigateToMain())
    }

    fun onIdChange(id: String) = blockingIntent {
        reduce {
            state.copy(id = id)
        }
    }

    fun onPwdChange(pwd: String) = blockingIntent {
        reduce {
            state.copy(pwd = pwd)
        }
    }
}

@Immutable
data class LoginState(
    val id: String = "",
    val pwd: String = ""
)

sealed interface LoginSideEffect {
    class Toast(val msg: String) : LoginSideEffect
    class NavigateToMain() : LoginSideEffect
}