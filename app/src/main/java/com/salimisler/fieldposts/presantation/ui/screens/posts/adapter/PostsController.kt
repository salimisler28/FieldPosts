package com.salimisler.fieldposts.presantation.ui.screens.posts.adapter

import com.airbnb.epoxy.EpoxyController
import com.salimisler.fieldposts.domain.model.PostUiModel

class PostsController : EpoxyController() {
    var posts: List<PostUiModel>? = null
    var onItemClickListener: ((PostUiModel) -> Unit)? = null
    var onFavClickListener: ((PostUiModel) -> Unit)? = null

    override fun buildModels() {
        posts?.forEach { _post ->
            postItem {
                id(_post.id.toString())
                item(_post)
                onItemClickListener(onItemClickListener)
                onFavClickListener(onFavClickListener)
            }
        }
    }
}