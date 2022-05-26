package com.salimisler.fieldposts.data.datasources.database

import appdb.FavsEntity
import com.salimisler.fieldposts.AppDatabase
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.utils.getFromDatabase
import com.salimisler.fieldposts.data.utils.listenDatabase
import com.salimisler.fieldposts.data.utils.updateDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavsDatabaseDataSource @Inject constructor(
    db: AppDatabase
) {
    private val queries = db.favsEntityQueries

    fun listen(): Flow<Resource<List<FavsEntity>>> {
        return listenDatabase { queries.getAll().asFlow().mapToList() }
    }

    fun get(): Flow<Resource<List<FavsEntity>>> {
        return getFromDatabase { queries.getAll().executeAsList() }
    }

    fun insert(
        id: Long,
        title: String,
        body: String
    ): Flow<Resource<Unit>> {
        return updateDatabase { queries.insert(id, title, body) }
    }

    fun deleteById(id: Long): Flow<Resource<Unit>> {
        return updateDatabase { queries.deleteById(id) }
    }

    fun getById(id: Long): Flow<Resource<FavsEntity>> {
        return getFromDatabase { queries.getById(id).executeAsOneOrNull() }
    }
}