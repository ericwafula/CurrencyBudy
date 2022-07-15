package tech.ericwathome.currencybudy.util

sealed class Resource<T> (val response: T?, val message: String?) {
    class Success<T>(data: T?, message: String?): Resource<T>(data, message)
    class Error<T>(message: String?): Resource<T>(null, message)
}