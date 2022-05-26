package com.salimisler.fieldposts.data.network.api

import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.data.network.request.CreatePostRequest
import retrofit2.Response
import retrofit2.http.*

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: Int
    ): Response<PostDto>

    @POST("posts")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): Response<Unit>

    @GET("comments")
    suspend fun getCommentsByPostId(
        @Query("postId") postId: Int
    ): Response<List<CommentDto>>
}