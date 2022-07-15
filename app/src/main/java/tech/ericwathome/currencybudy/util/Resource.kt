package tech.ericwathome.currencybudy.util

sealed class Resource<T> (val response: T?, val message: String?) {
    class Success<T>(data: T?): Resource<T>(data,null)
    class Error<T>(message: String): Resource<T>(null, message)
}