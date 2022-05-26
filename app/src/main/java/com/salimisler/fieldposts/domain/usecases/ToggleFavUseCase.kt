package com.salimisler.fieldposts.domain.usecases

import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.base.BaseUseCase
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ToggleFavUseCase @Inject constructor(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository
) : BaseUseCase<ToggleFavUseCase.ParamsIn, Unit>() {
    data class ParamsIn(
        val post: PostUiModel
    )

    override fun invoke(params: ParamsIn): Flow<Resource<Unit>> {
        val id = params.post.id.toLong()
        val title = params.post.title
        val body = params.post.body

        return jsonPlaceholderRepository.getFavById(id)
            .flatMapMerge {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data == null) {
                            jsonPlaceholderRepository.addFav(id, title, body)
                        } else {
                            jsonPlaceholderRepository.removeFav(id)
                        }
                    }
                    Resource.Status.LOADING -> flowOf(Resource.loading())
                    Resource.Status.ERROR -> flowOf(Resource.error(it.message))
                }
            }
    }
}