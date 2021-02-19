package com.example.capitertask.di

import com.example.capitertask.data.api.CustomerAPIService
import com.example.capitertask.data.repos.ProductRepositoryImpl
import com.example.capitertask.domain.data_sources.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CustomerModule {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): CustomerAPIService {
        return retrofit.create(CustomerAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductsRepo(productRepositoryImpl: ProductRepositoryImpl):ProductRepository{
        return productRepositoryImpl
    }
}