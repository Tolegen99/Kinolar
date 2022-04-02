package kz.tolegen.kinolar.server

import kz.tolegen.kinolar.data.models.classes.response.MovieDetailResp
import kz.tolegen.kinolar.data.models.classes.response.MovieListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{movieListType}")
    suspend fun getMovies(
        @Path("movieListType") movieListType: String,
        @Query("page") page: Int = 1
    ): Response<MovieListResp>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long
    ): Response<MovieDetailResp>
}