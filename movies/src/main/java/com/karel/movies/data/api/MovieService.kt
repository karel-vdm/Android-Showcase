package com.karel.movies.data.api

import com.karel.movies.data.api.model.GetMoviesResponseDto
import com.karel.movies.data.api.model.GetMovieDetailResponseDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apikey: String,
        @Query("s") searchTerm: String,
        @Query("page") page: Int
    ): GetMoviesResponseDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apikey: String,
        @Query("i") id: String
    ): GetMovieDetailResponseDto


    companion object {
        private val BASE_URL = "https://www.omdbapi.com/"

        private var _retrofit: Retrofit? = null
        private val retrofit: Retrofit
            get() {
                if (_retrofit == null) {
                    val logger = HttpLoggingInterceptor()
                    logger.level = HttpLoggingInterceptor.Level.BASIC

                    val client = OkHttpClient.Builder()
                        .addInterceptor(logger)
                        .build()

                    _retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return _retrofit!!
            }

        fun create(): MovieService = retrofit.create(MovieService::class.java)
    }

}

