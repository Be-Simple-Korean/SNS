package com.example.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.login.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.contracts.contract

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(),
    ContainerHost<SignUpState, SignUpSideEffect> {
    override val container: Container<SignUpState, SignUpSideEffect> = container(
        initialState = SignUpState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    postSideEffect(SignUpSideEffect.Toast(msg = throwable.message.orEmpty()))
                }
            }
        }
    )

    fun onIdChange(id: String) = blockingIntent {
        reduce {
            state.copy(id = id)
        }
    }

    fun onNameChange(name: String) = blockingIntent {
        reduce {
            state.copy(name = name)
        }
    }

    fun onPwdChange(pwd: String) = blockingIntent {
        reduce {
            state.copy(pwd = pwd)
        }
    }

    fun onPwd2Change(pwd: String) = blockingIntent {
        reduce {
            state.copy(repeatPwd = pwd)
        }
    }

    fun onSignUpClick() = intent {
        if(state.pwd != state.repeatPwd){
            postSideEffect(SignUpSideEffect.Toast("패스워드가 일치하지 않습니다."))
            return@intent
        }
        val isSuccessful = signUpUseCase(
            id = state.id,
            name = state.name,
            pwd = state.pwd
        ).getOrThrow()

        if(isSuccessful){
            postSideEffect(SignUpSideEffect.NavigateToLoginScreen())
            postSideEffect(SignUpSideEffect.Toast("회원가입에 성공하였습니다."))
        }
    }
}

@Immutable
data class SignUpState(
    val id: String = "",
    val pwd: String = "",
    val name: String = "",
    val repeatPwd: String = ""
)

sealed interface SignUpSideEffect {
    class Toast(val msg: String) : SignUpSideEffect
    class NavigateToLoginScreen() : SignUpSideEffect
}