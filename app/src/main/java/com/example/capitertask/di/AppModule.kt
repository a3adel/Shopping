package com.example.capitertask.di

import com.example.capitertask.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val keyInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("x-apikey", BuildConfig.API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }

        interceptors.add(keyInterceptor)
        return interceptors
    }


    @Provides
    @Singleton
    fun provideRetrofit(interceptors: ArrayList<Interceptor>): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        if (!interceptors.isEmpty()) {
            interceptors.forEach { interceptor ->
                clientBuilder.addInterceptor(interceptor)
            }
        }
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }



    @Singleton
    @Provides
    @Named("observer")
    fun provideObserverScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Singleton
    @Provides
    @Named("observed")
    fun provideObservedOnScheduler(): Scheduler {
        return Schedulers.io()
    }


}