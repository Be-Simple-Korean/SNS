package com.example.presentation

import com.example.domain.usecase.login.SignUpUseCase
import com.example.presentation.login.SignUpViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private val testId = "Time"
private val testPassword = "1234"

class SignUpViewModelTest {
    lateinit var signUpUseCase: SignUpUseCase
    lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        signUpUseCase = FakeSignUpUseCase()
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun 입력된_텍스트_상태_테스트() {
        // Given
        // 입력하지 않았을때의 상태
        Assert.assertEquals(viewModel.container.stateFlow.value.id, "")

        // When
        viewModel.onIdChange(testId)

        // Then
        // 입력하고 나서의 상태
        Assert.assertEquals(viewModel.container.stateFlow.value.id, testId)
    }

    @Test
    fun 입력된_패스워드_테스트() {
        Assert.assertEquals(viewModel.container.stateFlow.value.pwd, "")
        viewModel.onPwdChange(testPassword)
        Assert.assertEquals(viewModel.container.stateFlow.value.pwd, testPassword)
    }

    class FakeSignUpUseCase : SignUpUseCase {
        override suspend fun invoke(
            id: String,
            name: String,
            pwd: String
        ): Result<Boolean> = runCatching {
            true
        }
    }
}