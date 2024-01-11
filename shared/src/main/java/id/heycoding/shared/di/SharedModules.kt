package id.heycoding.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import id.heycoding.core.base.BaseModules
import id.heycoding.shared.data.local.datastore.UserPreferenceDataSource
import id.heycoding.shared.data.local.datastore.UserPreferenceDataSourceImpl
import id.heycoding.shared.data.local.datastore.UserPreferenceFactory
import id.heycoding.shared.data.remote.NetworkClient
import id.heycoding.shared.data.remote.datasource.SharedFeatureApiDataSource
import id.heycoding.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import id.heycoding.shared.data.remote.services.SharedFeatureApi
import id.heycoding.shared.data.repository.SharedApiRepository
import id.heycoding.shared.data.repository.SharedApiRepositoryImpl
import id.heycoding.shared.data.repository.UserPreferenceRepository
import id.heycoding.shared.data.repository.UserPreferenceRepositoryImpl
import id.heycoding.shared.domain.GetUserTokenUseCase
import id.heycoding.shared.domain.SaveAuthDataUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object SharedModules: BaseModules {
    override fun getModules(): List<Module> = listOf(
        remote, local, dataSource, repository, sharedUseCase, common
    )

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build()}
        single { NetworkClient(get(), get())}
        single<SharedFeatureApi> { get<NetworkClient>().create()}
    }

    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val dataSource = module {
        single<UserPreferenceDataSource> { UserPreferenceDataSourceImpl(get(), get()) }
        single<SharedFeatureApiDataSource> { SharedFeatureApiDataSourceImpl(get())}
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get())}
        single<SharedApiRepository> { SharedApiRepositoryImpl(get())}
    }

    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO)}
        single { SaveAuthDataUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }

}