package com.salimisler.fieldposts.domain.usecases

import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.base.BaseUseCase
import com.salimisler.fieldposts.domain.mappers.PostDtoMapper
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InitPostScreenUseCase @Inject constructor(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postDtoMapper: PostDtoMapper
) : BaseUseCase<InitPostScreenUseCase.ParamsIn, InitPostScreenUseCase.ParamsOut>() {
    class ParamsIn

    data class ParamsOut(
        val posts: List<PostUiModel>
    )

    override fun invoke(params: ParamsIn): Flow<Resource<ParamsOut>> {
        return jsonPlaceholderRepository.getAllPosts()
            .map {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val mapped = it.data?.map {
                            val paramsForPostDtoMapper = it
                            postDtoMapper.map(paramsForPostDtoMapper)
                        }.orEmpty()

                        val result = ParamsOut(posts = mapped)

                        Resource.success(result)
                    }
                    Resource.Status.LOADING -> Resource.loading()
                    Resource.Status.ERROR -> Resource.error(it.message)
                }
            }
    }
}