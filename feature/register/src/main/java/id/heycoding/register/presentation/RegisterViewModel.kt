package id.heycoding.register.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.register.domain.RegisterUserUseCase
import id.heycoding.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {
    val registerResult: MutableLiveData<ViewResource<UserViewParam?>> = MutableLiveData()

    fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) {
        viewModelScope.launch {
            registerUserUseCase(
                RegisterUserUseCase.RegisterParam(
                    birtdate = birthdate,
                    email = email,
                    gender = gender,
                    password = password,
                    username = username
                )
            ).collect {
                registerResult.postValue(it)
            }
        }
    }
}