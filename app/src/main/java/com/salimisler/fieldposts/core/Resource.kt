package com.salimisler.fieldposts.core

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    override fun toString(): String {
        return when (status) {
            Status.SUCCESS -> "Status: SUCCESS, DATA: ${data.toString()}"
            Status.LOADING -> "Status: LOADING"
            Status.ERROR -> "Status: ERROR!, MESSAGE: ${message.toString()}"
        }
    }
}