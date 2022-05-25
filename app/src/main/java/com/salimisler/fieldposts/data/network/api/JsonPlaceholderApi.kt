package com.salimisler.fieldposts.data.network.api

import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.data.network.request.CreatePostRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @POST("posts")
    suspend fun createPosts(
        @Body request: CreatePostRequest
    ): Response<Unit>

    @GET("comments")
    suspend fun getCommentsByPostId(
        @Query("postId") postId: Int
    ): Response<List<CommentDto>>
}