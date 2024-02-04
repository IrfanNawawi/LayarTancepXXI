package id.heycoding.login.data.network.datasource

import id.heycoding.login.data.network.model.request.LoginRequest
import id.heycoding.login.data.network.service.LoginFeatureApi
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface LoginDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse>
}

class LoginDataSourceImpl(private val service: LoginFeatureApi) : LoginDataSource {
    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse> {
        return service.loginUser(loginRequest)
    }

}