package ru.codeoverflow.junctionhack.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.model.server.JunctionHackApiML
import java.util.concurrent.TimeUnit

val networkModuleMl = module {
    single(named("mlRetrofit")) {
        provideMLRetrofit(
            androidContext(),
            "http://sorokin.asuscomm.com:4567/",
            get()
        )
    }
    single { provideMLOpenSpaceApi(get(named("mlRetrofit"))) }
}

fun provideMLDefaultOkhttpClient(contex: Context, prefs: Prefs): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(contex))
        .readTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
}

fun provideMLRetrofit(contex: Context, baseUrl: String, prefs: Prefs): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideMLDefaultOkhttpClient(contex, prefs).build())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
}

fun provideMLOpenSpaceApi(retrofit: Retrofit): JunctionHackApiML =
    retrofit.create(JunctionHackApiML::class.java)