package com.example.capitertask.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.capitertask.data.models.CartProduct
import io.reactivex.rxjava3.core.Single

@Dao
interface CartDao {
    @Insert
    fun insertProduct(product: CartProduct): Single<Integer>

    @Delete
    fun deleteProduct(product: CartProduct): Single<Integer>

    @Update
    fun updateProduct(product: CartProduct): Single<Integer>

    @androidx.room.Query("SELECT * FROM cart_products")
    fun getCartProducts(): LiveData<List<CartProduct>>
}