package id.heycoding.register.data.network.datasource

import id.heycoding.register.data.network.model.request.RegisterRequest
import id.heycoding.register.data.network.service.RegisterFeatureApi
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface RegisterDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}

class RegisterDataSourceImpl(private val service: RegisterFeatureApi) : RegisterDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse> {
        return service.registerUser(registerRequest)
    }

}