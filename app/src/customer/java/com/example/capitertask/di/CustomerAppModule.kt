package com.example.capitertask.di

import android.content.Context
import androidx.room.Room
import com.example.capitertask.data.api.CartAPIInterface
import com.example.capitertask.data.api.ProductsAPIInterface
import com.example.capitertask.data.persistence.CartDao
import com.example.capitertask.data.persistence.CustomerDatabase
import com.example.capitertask.data.persistence.DATABASE_NAME
import com.example.capitertask.data.repos.ProductRepositoryImpl
import com.example.capitertask.domain.dataSources.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CustomerAppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CustomerDatabase =
        Room.databaseBuilder(context, CustomerDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideCartDao(customerDatabase: CustomerDatabase): CartDao =
        customerDatabase.getCartProductsDao()

}