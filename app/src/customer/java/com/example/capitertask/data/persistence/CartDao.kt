package com.example.capitertask.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.capitertask.data.models.CartProduct
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CartDao {
    @Insert
    fun insertProduct(product: CartProduct): Long

    @Delete
    fun deleteProduct(product: CartProduct): Int

    @Update
    fun updateProduct(product: CartProduct): Int

    @androidx.room.Query("SELECT * FROM cart_products")
    fun getCartProducts(): LiveData<List<CartProduct>>
}