package kz.tolegen.kinolar.di.module

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.BuildConfig
import kz.tolegen.kinolar.server.Api
import kz.tolegen.kinolar.utils.PARAMS_API_KEY_FIELD
import kz.tolegen.kinolar.utils.PARAMS_LANGUAGE_FIELD
import kz.tolegen.kinolar.utils.PARAM_LANGUAGE_RU
import okhttp3.Interceptor
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
        httpClient
            .addInterceptor(Interceptor.invoke {
                val original = it.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(PARAMS_API_KEY_FIELD, BuildConfig.API_KEY)
                    .addQueryParameter(PARAMS_LANGUAGE_FIELD, PARAM_LANGUAGE_RU)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                it.proceed(request)
            })
            .addInterceptor(logging)
            .addInterceptor(ChuckerInterceptor(App.INSTANCE.applicationContext))

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}