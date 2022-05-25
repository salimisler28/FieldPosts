package com.salimisler.fieldposts.domain.mappers

import appdb.FavsEntity
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.base.BaseMapper
import com.salimisler.fieldposts.domain.model.PostUiModel
import javax.inject.Inject

class PostDtoMapper @Inject constructor(
) : BaseMapper<PostDtoMapper.ParamsIn, PostUiModel>() {
    data class ParamsIn(
        val postDto: PostDto,
        val favs: List<FavsEntity>?
    )

    override fun map(params: ParamsIn): PostUiModel {
        val dto = params.postDto
        val favs = params.favs

        val isFav = favs?.let {
            it.any { dto.id?.toLong() == it.id }
        } ?: false

        return PostUiModel(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            body = dto.body.orEmpty(),
            isFav = isFav
        )
    }
}