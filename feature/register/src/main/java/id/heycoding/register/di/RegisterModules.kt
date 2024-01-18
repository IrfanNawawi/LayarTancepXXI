package id.heycoding.register.di

import id.heycoding.core.base.FeatureModules
import id.heycoding.register.data.network.datasource.RegisterDataSource
import id.heycoding.register.data.network.datasource.RegisterDataSourceImpl
import id.heycoding.register.data.network.service.RegisterFeatureApi
import id.heycoding.register.data.repository.RegisterRepository
import id.heycoding.register.data.repository.RegisterRepositoryImpl
import id.heycoding.register.domain.CheckRegisterFieldUseCase
import id.heycoding.register.domain.RegisterUserUseCase
import id.heycoding.register.presentation.ui.RegisterViewModel
import id.heycoding.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object RegisterModules : FeatureModules {
    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )

    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }
    override val dataSources: Module = module {
        single<RegisterDataSource> { RegisterDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { CheckRegisterFieldUseCase(Dispatchers.IO) }
        single { RegisterUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<RegisterFeatureApi> { get<NetworkClient>().create() }
    }
}