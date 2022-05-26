package com.salimisler.fieldposts.domain.usecases

import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.base.BaseUseCase
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListenFavsUseCase @Inject constructor(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository
) : BaseUseCase<ListenFavsUseCase.ParamsIn, List<PostUiModel>>() {
    class ParamsIn

    override fun invoke(params: ParamsIn): Flow<Resource<List<PostUiModel>>> {
        return jsonPlaceholderRepository.listenAllFavs()
            .map {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val mapped = it.data?.map {
                            PostUiModel(
                                id = it.id.toInt(),
                                title = it.title,
                                body = it.body,
                                isFav = true
                            )
                        }
                        Resource.success(mapped)
                    }
                    Resource.Status.LOADING -> Resource.loading()
                    Resource.Status.ERROR -> Resource.error(it.message)
                }
            }
    }
}