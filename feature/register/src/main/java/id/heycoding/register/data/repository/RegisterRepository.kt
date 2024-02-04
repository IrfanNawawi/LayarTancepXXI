package id.heycoding.register.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.register.data.network.datasource.RegisterDataSource
import id.heycoding.register.data.network.model.request.RegisterRequest
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
typealias RegisterDataResource = DataResource<BaseResponse<AuthResponse>>

interface RegisterRepository {
    suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(private val dataSource: RegisterDataSource) : RegisterRepository,
    Repository() {
    override suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource> {
        return flow {
            emit(safeNetworkCall {
                dataSource.registerUser(
                    RegisterRequest(
                        birthdate,
                        email,
                        gender,
                        password,
                        username
                    )
                )
            })
        }
    }

}