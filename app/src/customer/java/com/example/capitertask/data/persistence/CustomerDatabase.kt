package com.example.capitertask.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.capitertask.data.models.CartProduct
const val DATABASE_NAME="customer_app_db"

@Database(entities = [CartProduct::class], version = 3)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun getCartProductsDao():CartDao
}