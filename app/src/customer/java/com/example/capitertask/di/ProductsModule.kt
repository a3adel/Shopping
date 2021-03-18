package com.example.capitertask.di

import com.example.capitertask.data.api.ProductsAPIInterface
import com.example.capitertask.data.repos.ProductRepositoryImpl
import com.example.capitertask.domain.dataSources.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
@Module
@InstallIn(ViewModelComponent::class)
class ProductsModule {
    @Provides
    @ViewModelScoped
    fun provideProductsApiInterface(retrofit: Retrofit): ProductsAPIInterface =
        retrofit.create(ProductsAPIInterface::class.java)
    @Provides
    @ViewModelScoped
    fun provideProductsRepo(productRepositoryImpl: ProductRepositoryImpl): ProductRepository =
        productRepositoryImpl
}