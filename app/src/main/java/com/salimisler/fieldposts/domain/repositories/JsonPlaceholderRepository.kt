package com.salimisler.fieldposts.domain.repositories

import appdb.FavsEntity
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.PostDto
import kotlinx.coroutines.flow.Flow

interface JsonPlaceholderRepository {
    fun getAllPosts(): Flow<Resource<List<PostDto>>>
    fun getAllFavs(): Flow<Resource<List<FavsEntity>>>
    fun addFav(
        id: Long,
        title: String,
        body: String
    ): Flow<Resource<Unit>>
    fun removeFav(id: Long): Flow<Resource<Unit>>
    fun getById(id: Long): Flow<Resource<FavsEntity>>
}