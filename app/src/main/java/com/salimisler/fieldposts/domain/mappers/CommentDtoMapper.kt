package com.salimisler.fieldposts.domain.mappers

import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.domain.base.BaseMapper
import com.salimisler.fieldposts.domain.model.CommentUiModel
import javax.inject.Inject

class CommentDtoMapper @Inject constructor(
) : BaseMapper<CommentDto, CommentUiModel>() {
    override fun map(params: CommentDto): CommentUiModel {
        return CommentUiModel(
            id = params.id ?: 0,
            name = params.name.orEmpty(),
            body = params.body.orEmpty(),
        )
    }
}