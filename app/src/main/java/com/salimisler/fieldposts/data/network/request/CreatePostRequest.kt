package com.salimisler.fieldposts.data.network.request

data class CreatePostRequest(
    val userId: Int,
    val title: String,
    val body: String
)
