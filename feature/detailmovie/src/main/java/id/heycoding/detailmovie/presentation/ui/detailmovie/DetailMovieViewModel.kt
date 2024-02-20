package id.heycoding.detailmovie.presentation.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.detailmovie.data.remote.model.viewparam.DetailMovieViewParam
import id.heycoding.detailmovie.domain.GetDetailMovieUseCase
import id.heycoding.shared.data.remote.model.viewparam.VideoViewParam
import id.heycoding.shared.domain.GetVideoMovieUseCase
import kotlinx.coroutines.launch


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class DetailMovieViewModel(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getVideoMovieUseCase: GetVideoMovieUseCase
) : ViewModel() {
    val detailMovieResult: MutableLiveData<ViewResource<DetailMovieViewParam>> = MutableLiveData()
    val videoMovieResult: MutableLiveData<ViewResource<List<VideoViewParam>>> = MutableLiveData()

    fun fetchDetailMovie(movieId: String) {
        viewModelScope.launch {
            getDetailMovieUseCase(GetDetailMovieUseCase.Param(movieId)).collect {
                detailMovieResult.postValue(it)
            }
        }
    }

    fun fetchVideoMovie(movieId: String) {
        viewModelScope.launch {
            getVideoMovieUseCase(GetVideoMovieUseCase.Param(movieId)).collect {
                videoMovieResult.postValue(it)
            }
        }
    }
}