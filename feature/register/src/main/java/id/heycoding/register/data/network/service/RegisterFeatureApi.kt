package id.heycoding.register.data.network.service

import id.heycoding.register.data.network.model.request.RegisterRequest
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface RegisterFeatureApi {
    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}