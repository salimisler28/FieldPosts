package com.salimisler.fieldposts.domain.mappers

import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.base.BaseMapper
import com.salimisler.fieldposts.domain.model.PostUiModel
import javax.inject.Inject

class PostDtoMapper @Inject constructor(
) : BaseMapper<PostDto, PostUiModel>() {
    override fun map(params: PostDto): PostUiModel {
        return PostUiModel(
            id = params.id ?: 0,
            title = params.title.orEmpty(),
            body = params.body.orEmpty()
        )
    }
}