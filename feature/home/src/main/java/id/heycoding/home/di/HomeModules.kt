package id.heycoding.home.di

import id.heycoding.core.base.FeatureModules
import id.heycoding.home.data.network.datasource.HomeDataSource
import id.heycoding.home.data.network.datasource.HomeDataSourceImpl
import id.heycoding.home.data.network.service.HomeFeatureApi
import id.heycoding.home.data.repository.HomeRepository
import id.heycoding.home.data.repository.HomeRepositoryImpl
import id.heycoding.home.domain.GetHomeFeedsUseCase
import id.heycoding.home.domain.GetWatchlistUseCase
import id.heycoding.home.presentation.ui.home.HomeViewModel
import id.heycoding.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object HomeModules : FeatureModules {

    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::HomeViewModel)
    }
    override val dataSources: Module = module {
        single<HomeDataSource> { HomeDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { GetHomeFeedsUseCase(get(), Dispatchers.IO) }
        single { GetWatchlistUseCase(get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<HomeFeatureApi> { get<NetworkClient>().create() }
    }
}