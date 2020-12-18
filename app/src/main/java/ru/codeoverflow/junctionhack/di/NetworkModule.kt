package ru.codeoverflow.junctionhack.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.codeoverflow.junctionhack.BuildConfig.BASE_URL
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.model.server.JunctionHackApi
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideDefaultOkhttpClient(androidContext(), get()) }
    single { provideOpenSpaceApi(provideRetrofit(androidContext(), BASE_URL, get())) }
}

fun provideDefaultOkhttpClient(contex: Context, prefs: Prefs): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(contex))
        .addInterceptor {
            val newBuilder = it.request().newBuilder()
            prefs.token?.let { token ->
                newBuilder
                    .addHeader("Authorization", "Bearer $token")
            }
            it.proceed(newBuilder.build())
        }
        .readTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
}

fun provideRetrofit(contex: Context, baseUrl: String, prefs: Prefs): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideDefaultOkhttpClient(contex, prefs).build())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOpenSpaceApi(retrofit: Retrofit): JunctionHackApi =
    retrofit.create(JunctionHackApi::class.java)