package com.salimisler.fieldposts.data.repositories

import appdb.FavsEntity
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.datasources.database.FavsDatabaseDataSource
import com.salimisler.fieldposts.data.datasources.network.JsonPlaceholderNetworkDataSource
import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JsonPlaceholderRepositoryImpl @Inject constructor(
    private val jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource,
    private val favsDatabaseDataSource: FavsDatabaseDataSource
) : JsonPlaceholderRepository {
    override fun getAllPosts(): Flow<Resource<List<PostDto>>> {
        return jsonPlaceholderNetworkDataSource.getPosts()
    }

    override fun getPostById(id: Int): Flow<Resource<PostDto>> {
        return jsonPlaceholderNetworkDataSource.getPostById(id)
    }

    override fun getCommentsByPostId(id: Int): Flow<Resource<List<CommentDto>>> {
        return jsonPlaceholderNetworkDataSource.getCommentsByPostId(id)
    }

    override fun listenAllFavs(): Flow<Resource<List<FavsEntity>>> {
        return favsDatabaseDataSource.listen()
    }

    override fun getAllFavs(): Flow<Resource<List<FavsEntity>>> {
        return favsDatabaseDataSource.get()
    }

    override fun addFav(
        id: Long,
        title: String,
        body: String
    ): Flow<Resource<Unit>> {
        return favsDatabaseDataSource.insert(id, title, body)
    }

    override fun removeFav(id: Long): Flow<Resource<Unit>> {
        return favsDatabaseDataSource.deleteById(id)
    }

    override fun getFavById(id: Long): Flow<Resource<FavsEntity>> {
        return favsDatabaseDataSource.getById(id)
    }
}