package id.heycoding.detailmovie.di

import id.heycoding.core.base.FeatureModules
import id.heycoding.detailmovie.data.remote.datasource.DetailMovieDataSource
import id.heycoding.detailmovie.data.remote.datasource.DetailMovieDataSourceImpl
import id.heycoding.detailmovie.data.remote.service.DetailMovieFeatureApi
import id.heycoding.detailmovie.data.repository.DetailMovieRepository
import id.heycoding.detailmovie.data.repository.DetailMovieRepositoryImpl
import id.heycoding.detailmovie.domain.GetDetailMovieUseCase
import id.heycoding.detailmovie.presentation.ui.detailmovie.DetailMovieViewModel
import id.heycoding.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object DetailMovieModules : FeatureModules {

    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )

    override val repositories: Module = module {
        single<DetailMovieRepository> { DetailMovieRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::DetailMovieViewModel)
    }
    override val dataSources: Module = module {
        single<DetailMovieDataSource> { DetailMovieDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { GetDetailMovieUseCase(get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<DetailMovieFeatureApi> { get<NetworkClient>().create() }
    }
}