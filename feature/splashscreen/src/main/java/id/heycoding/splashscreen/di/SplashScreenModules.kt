package id.heycoding.splashscreen.di

import id.heycoding.core.base.FeatureModules
import id.heycoding.shared.data.remote.NetworkClient
import id.heycoding.splashscreen.data.network.datasource.SplashScreenDataSource
import id.heycoding.splashscreen.data.network.datasource.SplashScreenDataSourceImpl
import id.heycoding.splashscreen.data.network.service.SplashScreenFeatureApi
import id.heycoding.splashscreen.data.repository.SplashScreenRepository
import id.heycoding.splashscreen.data.repository.SplashScreenRepositoryImpl
import id.heycoding.splashscreen.domain.SyncUserUseCase
import id.heycoding.splashscreen.presentation.SplashScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object SplashScreenModules : FeatureModules {
    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )
    override val repositories: Module = module {
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::SplashScreenViewModel)
    }
    override val dataSources: Module = module {
        single<SplashScreenDataSource> { SplashScreenDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { SyncUserUseCase(get(), get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<SplashScreenFeatureApi> { get<NetworkClient>().create() }
    }
}