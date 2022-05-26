package com.salimisler.fieldposts.domain.mappers

import appdb.FavsEntity
import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.base.BaseMapper
import com.salimisler.fieldposts.domain.model.PostDetailUiModel
import javax.inject.Inject

class PostDetailMapper @Inject constructor(
    private val commentDtoMapper: CommentDtoMapper,
    private val postDtoMapper: PostDtoMapper
) : BaseMapper<PostDetailMapper.ParamsIn, PostDetailUiModel>() {
    data class ParamsIn(
        val dto: PostDto,
        val comments: List<CommentDto>?,
        val favs: List<FavsEntity>?
    )

    override fun map(params: ParamsIn): PostDetailUiModel {
        val dto = params.dto
        val commentList = params.comments
        val favList = params.favs

        val commentsUiModelList = commentList?.let {
            it.map { commentDtoMapper.map(it) }
        } ?: emptyList()

        val paramsForPostDtoMapper = PostDtoMapper.ParamsIn(
            postDto = dto,
            favs = favList
        )
        val postUiModel = postDtoMapper.map(paramsForPostDtoMapper)

        return PostDetailUiModel(postUiModel, commentsUiModelList)
    }
}