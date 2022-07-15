package tech.ericwathome.currencybudy.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ericwathome.currencybudy.util.API_KEY
import tech.ericwathome.currencybudy.util.BASE_URL

object RateClient {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val authInterceptor = Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("apikey", API_KEY)
            .build()
        chain.proceed(request)
    }
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
    private val retrofit = Retrofit.Builder()
        .client(httpClient.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rateApi: RateApi = retrofit.create(RateApi::class.java)
}