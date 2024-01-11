package id.heycoding.login.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.login.data.network.datasource.LoginDataSource
import id.heycoding.login.data.network.model.request.LoginRequest
import id.heycoding.shared.data.model.response.AuthResponse
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
typealias LoginDataResource = DataResource<BaseResponse<AuthResponse>>

interface LoginRepository {
    suspend fun loginUser(
        email: String,
        password: String,
    ): Flow<LoginDataResource>
}

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository, Repository() {
    override suspend fun loginUser(email: String, password: String): Flow<LoginDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.loginUser(LoginRequest(email, password)) })
        }
    }

}