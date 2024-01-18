package id.heycoding.home.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.home.data.repository.HomeRepository
import id.heycoding.home.mapper.SectionMapper
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.mapper.MovieMapper
import id.heycoding.shared.utils.ext.suspendSubscribe
import id.heycoding.shared.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetHomeFeedsUseCase(private val repository: HomeRepository, dispatcher: CoroutineDispatcher) :
    BaseUseCase<Nothing, List<HomeUiItem>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<HomeUiItem>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchHomeFeeds().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val data = mutableListOf<HomeUiItem>()
                    result.payload?.data?.let { homeData ->
                        homeData.header?.let { movie ->
                            data.add(HomeUiItem.HeaderSectionItem(MovieMapper.toViewParam(movie)))
                        }
                        homeData.sections?.forEach { section ->
                            data.add(HomeUiItem.ContentSectionItem(SectionMapper.toViewParam(section)))
                        }
                    }
                    emit(ViewResource.Success(data))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}