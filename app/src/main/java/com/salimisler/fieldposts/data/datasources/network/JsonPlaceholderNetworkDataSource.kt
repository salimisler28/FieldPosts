package com.salimisler.fieldposts.data.datasources.network

import com.salimisler.fieldposts.data.network.api.JsonPlaceholderApi
import com.salimisler.fieldposts.data.network.request.CreatePostRequest
import com.salimisler.fieldposts.data.utils.networkRequest
import javax.inject.Inject

class JsonPlaceholderNetworkDataSource @Inject constructor(
    private val jsonPlaceHolderApi: JsonPlaceholderApi
) {
    fun getPosts() = networkRequest { jsonPlaceHolderApi.getPosts() }
    fun getPostById(id: Int) = networkRequest { jsonPlaceHolderApi.getPostById(id) }

    fun createPost(createPostRequest: CreatePostRequest) =
        networkRequest { jsonPlaceHolderApi.createPost(createPostRequest) }

    fun getCommentsByPostId(postId: Int) =
        networkRequest { jsonPlaceHolderApi.getCommentsByPostId(postId) }
}