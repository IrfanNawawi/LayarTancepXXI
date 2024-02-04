package id.heycoding.shared.delegates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.domain.AddOrRemoveWatchlistUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by Irfan Nawawi on 22/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface AddOrRemoveWatchlistDelegates {
    fun init(scope: CoroutineScope)
    fun getWatchlistResult(): LiveData<ViewResource<MovieViewParam>>
    fun addOrRemoveWatchlist(movie: MovieViewParam)
}

class AddOrRemoveWatchlistDelegatesImpl : AddOrRemoveWatchlistDelegates {
    private lateinit var coroutineScope: CoroutineScope
    private val usecase: AddOrRemoveWatchlistUseCase by inject(AddOrRemoveWatchlistUseCase::class.java)
    private val result = MutableLiveData<ViewResource<MovieViewParam>>()

    override fun init(scope: CoroutineScope) {
        coroutineScope = scope
    }

    override fun getWatchlistResult(): LiveData<ViewResource<MovieViewParam>> = result

    override fun addOrRemoveWatchlist(movie: MovieViewParam) {
        coroutineScope.launch {
            usecase(AddOrRemoveWatchlistUseCase.Param(movie)).collect {
                result.postValue(it)
            }
        }
    }

}