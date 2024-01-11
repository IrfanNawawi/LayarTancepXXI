package id.heycoding.login.di

import id.heycoding.core.base.FeatureModules
import id.heycoding.login.data.network.datasource.LoginDataSource
import id.heycoding.login.data.network.datasource.LoginDataSourceImpl
import id.heycoding.login.data.network.service.LoginFeatureApi
import id.heycoding.login.data.repository.LoginRepository
import id.heycoding.login.data.repository.LoginRepositoryImpl
import id.heycoding.login.domain.CheckLoginFieldUseCase
import id.heycoding.login.domain.LoginUserUseCase
import id.heycoding.login.presentation.LoginViewModel
import id.heycoding.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object LoginModules : FeatureModules {

    override fun getModules(): List<Module> = listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }

    override val dataSources: Module = module {
        single<LoginDataSource> { LoginDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckLoginFieldUseCase(Dispatchers.IO) }
        single { LoginUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<LoginFeatureApi> { get<NetworkClient>().create() }
    }

}