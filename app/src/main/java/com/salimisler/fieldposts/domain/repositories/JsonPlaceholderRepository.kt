package com.salimisler.fieldposts.domain.repositories

import appdb.FavsEntity
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import kotlinx.coroutines.flow.Flow

interface JsonPlaceholderRepository {
    fun getAllPosts(): Flow<Resource<List<PostDto>>>
    fun getPostById(id: Int): Flow<Resource<PostDto>>
    fun getCommentsByPostId(id: Int): Flow<Resource<List<CommentDto>>>
    fun listenAllFavs(): Flow<Resource<List<FavsEntity>>>
    fun getAllFavs(): Flow<Resource<List<FavsEntity>>>
    fun addFav(
        id: Long,
        title: String,
        body: String
    ): Flow<Resource<Unit>>
    fun removeFav(id: Long): Flow<Resource<Unit>>
    fun getFavById(id: Long): Flow<Resource<FavsEntity>>
}