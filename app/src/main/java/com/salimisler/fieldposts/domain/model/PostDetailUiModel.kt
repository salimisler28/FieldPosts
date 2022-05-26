package com.salimisler.fieldposts.domain.model

data class PostDetailUiModel(
    val postUiModel: PostUiModel,
    val comments: List<CommentUiModel>
)