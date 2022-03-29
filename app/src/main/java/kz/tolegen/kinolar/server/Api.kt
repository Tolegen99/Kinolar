package kz.tolegen.kinolar.server

import kz.tolegen.kinolar.server.response.MovieDetailResp
import kz.tolegen.kinolar.server.response.MovieListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    ): Response<MovieListResp>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieListResp>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieListResp>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long
    ): Response<MovieDetailResp>
}