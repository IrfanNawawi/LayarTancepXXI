package id.heycoding.home.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.home.domain.GetHomeFeedsUseCase
import id.heycoding.home.domain.GetWatchlistUseCase
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.data.model.viewparam.UserViewParam
import id.heycoding.shared.delegates.AddOrRemoveWatchlistDelegates
import id.heycoding.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import id.heycoding.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeViewModel(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel(), AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {
    val homeFeedsResult: MutableLiveData<ViewResource<List<HomeUiItem>>> = MutableLiveData()
    val watchlistResult: MutableLiveData<ViewResource<List<MovieViewParam>>> = MutableLiveData()
    val currentUserResult: MutableLiveData<ViewResource<UserViewParam>> = MutableLiveData()

    init {
        init(viewModelScope)
    }

    fun fetchHomeFeeds() {
        viewModelScope.launch {
            getHomeFeedsUseCase().collect {
                homeFeedsResult.postValue(it)
            }
        }
    }

    fun fetchWatchList() {
        viewModelScope.launch {
            getWatchlistUseCase().collect {
                watchlistResult.postValue(it)
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect {
                currentUserResult.postValue(it)
            }
        }
    }
}