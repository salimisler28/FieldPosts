package com.salimisler.fieldposts.domain.base

import com.salimisler.fieldposts.core.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase <IN, OUT> {
    abstract fun invoke(params: IN): Flow<Resource<OUT>>
}