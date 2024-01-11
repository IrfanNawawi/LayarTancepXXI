package id.heycoding.login.data.network.service

import id.heycoding.login.data.network.model.request.LoginRequest
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface LoginFeatureApi {
    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<AuthResponse>
}