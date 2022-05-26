package com.salimisler.fieldposts.domain.usecases

import appdb.FavsEntity
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.base.BaseUseCase
import com.salimisler.fieldposts.domain.mappers.PostDetailMapper
import com.salimisler.fieldposts.domain.model.PostDetailUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class InitPostDetailUseCase @Inject constructor(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postDetailMapper: PostDetailMapper
) : BaseUseCase<InitPostDetailUseCase.ParamsIn, InitPostDetailUseCase.ParamsOut>() {
    data class ParamsIn(
        val id: Int
    )

    data class ParamsOut(
        val postDetail: PostDetailUiModel
    )

    override fun invoke(params: ParamsIn): Flow<Resource<ParamsOut>> {
        val id = params.id

        return combine(
            jsonPlaceholderRepository.getPostById(id),
            jsonPlaceholderRepository.getCommentsByPostId(id),
            jsonPlaceholderRepository.listenAllFavs()
        ) { _post, _comments, _favs ->
            when {
                _post.status == Resource.Status.SUCCESS && _comments.status == Resource.Status.SUCCESS && _favs.status == Resource.Status.SUCCESS -> {
                    val result = allSuccess(_post.data!!, _comments.data, _favs.data)
                    val out = ParamsOut(result)
                    Resource.success(out)
                }

                _post.status == Resource.Status.LOADING || _comments.status == Resource.Status.LOADING || _favs.status == Resource.Status.LOADING -> {
                    Resource.loading()
                }

                _post.status == Resource.Status.ERROR || _comments.status == Resource.Status.ERROR || _favs.status == Resource.Status.ERROR -> {
                    Resource.error(_post.message + _comments.message + _favs.message)
                }

                else -> {
                    Resource.error(_post.message + _comments.message)
                }
            }
        }
    }

    private fun allSuccess(
        postDto: PostDto,
        comments: List<CommentDto>?,
        favList: List<FavsEntity>?
    ): PostDetailUiModel {
        val params = PostDetailMapper.ParamsIn(
            postDto, comments, favList
        )
        return postDetailMapper.map(params)
    }

}