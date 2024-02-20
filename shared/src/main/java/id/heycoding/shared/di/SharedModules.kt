package id.heycoding.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import id.heycoding.core.base.BaseModules
import id.heycoding.shared.data.remote.NetworkClient
import id.heycoding.shared.data.remote.datasource.SharedFeatureApiDataSource
import id.heycoding.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import id.heycoding.shared.data.remote.services.SharedFeatureApi
import id.heycoding.shared.data.remote.services.interceptor.TmdbAuthInterceptor
import id.heycoding.shared.data.repository.SharedApiRepository
import id.heycoding.shared.data.repository.SharedApiRepositoryImpl
import id.heycoding.shared.domain.GetVideoMovieUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object SharedModules : BaseModules {
    override fun getModules(): List<Module> = listOf(
        remote, dataSource, repository, sharedUseCase, common
    )

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { TmdbAuthInterceptor() }
        single { NetworkClient(get(), get()) }
        single<SharedFeatureApi> { get<NetworkClient>().create() }
    }

    private val dataSource = module {
        single<SharedFeatureApiDataSource> { SharedFeatureApiDataSourceImpl(get()) }
    }

    private val repository = module {
        single<SharedApiRepository> { SharedApiRepositoryImpl(get()) }
    }

    private val sharedUseCase = module {
        single { GetVideoMovieUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }

}