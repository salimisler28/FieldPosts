package com.salimisler.fieldposts.data.repositories

import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.datasources.network.JsonPlaceholderNetworkDataSource
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JsonPlaceholderRepositoryImpl @Inject constructor(
    private val jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource
) : JsonPlaceholderRepository {
    override fun getAllPosts(): Flow<Resource<List<PostDto>>> {
        return jsonPlaceholderNetworkDataSource.getPosts()
    }
}