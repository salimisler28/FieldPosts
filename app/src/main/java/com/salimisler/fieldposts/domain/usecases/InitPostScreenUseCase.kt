package com.salimisler.fieldposts.domain.usecases

import appdb.FavsEntity
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.base.BaseUseCase
import com.salimisler.fieldposts.domain.mappers.PostDtoMapper
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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
        return combine(
            jsonPlaceholderRepository.getAllPosts(),
            jsonPlaceholderRepository.listenAllFavs()
        ) { _posts, _favs ->
            when {
                _posts.status == Resource.Status.SUCCESS && _favs.status == Resource.Status.SUCCESS -> {
                    val mapped = allSuccess(_posts.data, _favs.data)
                    val out = ParamsOut(mapped)
                    Resource.success(out)
                }

                _posts.status == Resource.Status.LOADING || _favs.status == Resource.Status.LOADING -> {
                    Resource.loading()
                }

                _posts.status == Resource.Status.ERROR || _favs.status == Resource.Status.ERROR -> {
                    Resource.error(_posts.message + _favs.message)
                }

                else -> {
                    Resource.error("Unknown")
                }
            }
        }


    }

    private fun allSuccess(posts: List<PostDto>?, favs: List<FavsEntity>?): List<PostUiModel> {
        return posts?.map {
            val paramsForPostDtoMapper = PostDtoMapper.ParamsIn(
                postDto = it, favs
            )
            postDtoMapper.map(paramsForPostDtoMapper)
        }.orEmpty()
    }
}