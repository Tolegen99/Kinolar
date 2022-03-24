package kz.tolegen.kinolar.server

import kz.tolegen.kinolar.server.response.MovieListResp
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("movie/top_rated?api_key=42a73e7c5255eff2d58bfc47361eeccb&language=ru")
    suspend fun getTopRatedMovies(): Response<MovieListResp>
}