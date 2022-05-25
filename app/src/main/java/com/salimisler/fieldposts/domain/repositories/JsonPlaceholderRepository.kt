package com.salimisler.fieldposts.domain.repositories

import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.PostDto
import kotlinx.coroutines.flow.Flow

interface JsonPlaceholderRepository {
    fun getAllPosts(): Flow<Resource<List<PostDto>>>
}