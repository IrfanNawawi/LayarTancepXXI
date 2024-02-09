package id.heycoding.home.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.home.domain.GetPopularMovieUseCase
import id.heycoding.home.domain.GetUpcomingMovieUseCase
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase) : ViewModel() {

    val homePopularResult: MutableLiveData<ViewResource<List<HomeUiItem>>> = MutableLiveData()
    val homeUpcomingResult: MutableLiveData<ViewResource<List<HomeUiItem>>> = MutableLiveData()

    fun fetchHomePopular() {
        viewModelScope.launch {
            getPopularMovieUseCase().collect {
                homePopularResult.postValue(it)
            }
        }
    }

    fun fetchHomeUpcoming() {
        viewModelScope.launch {
            getUpcomingMovieUseCase().collect {
                homeUpcomingResult.postValue(it)
            }
        }
    }
}