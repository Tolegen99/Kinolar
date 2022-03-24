package kz.tolegen.kinolar.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import kz.tolegen.kinolar.BuildConfig
import kz.tolegen.kinolar.server.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit) =
        retrofit.create(Api::class.java)

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}