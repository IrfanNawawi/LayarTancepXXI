package id.heycoding.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.login.domain.LoginUserUseCase
import id.heycoding.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 11/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {
    val loginResult: MutableLiveData<ViewResource<UserViewParam?>> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUserUseCase(
                LoginUserUseCase.LoginParam(
                    email = email,
                    password = password
                )
            ).collect {
                loginResult.postValue(it)
            }
        }
    }

}