package id.heycoding.splashscreen.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.splashscreen.domain.SyncResult
import id.heycoding.splashscreen.domain.SyncUserUseCase
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class SplashScreenViewModel(private val syncUserUseCase: SyncUserUseCase) : ViewModel() {

    val syncResult: MutableLiveData<ViewResource<SyncResult>> = MutableLiveData()
    fun syncUser() {
        viewModelScope.launch {
            syncUserUseCase().collect {
                syncResult.value = it
            }
        }
    }
}