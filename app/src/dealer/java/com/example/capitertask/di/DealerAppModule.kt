package com.example.capitertask.di

import com.example.capitertask.data.api.DealerAPIService
import com.example.capitertask.data.repos.OrdersRepoImpl
import com.example.capitertask.domain.data_sources.OrdersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DealerAppModule {
    @Singleton
    @Provides
    fun provideDealerAPIService(retrofit: Retrofit): DealerAPIService {
        return retrofit.create(DealerAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideOrdersRepo(orderRepoImpl:OrdersRepoImpl): OrdersRepo {
        return orderRepoImpl
    }
}