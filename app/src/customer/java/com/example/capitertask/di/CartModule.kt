package com.example.capitertask.di

import com.example.capitertask.data.api.CartAPIInterface
import com.example.capitertask.data.repos.CartRepoImpl
import com.example.capitertask.domain.dataSources.CartRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
@Module
@InstallIn(ViewModelComponent::class)
class CartModule {

    @Provides
    @ViewModelScoped
    fun provideCartAPIInterface(retrofit: Retrofit): CartAPIInterface =
        retrofit.create(CartAPIInterface::class.java)

    @Provides
    @ViewModelScoped
    fun provideCartRepo(cartRepoImpl: CartRepoImpl): CartRepo = cartRepoImpl
}