package com.salimisler.fieldposts.domain.base

abstract class BaseMapper<IN, OUT> {
    abstract fun map(params: IN): OUT
}