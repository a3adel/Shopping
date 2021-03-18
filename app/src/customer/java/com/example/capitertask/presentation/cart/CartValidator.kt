package com.example.capitertask.presentation.cart

import android.content.Context
import com.example.capitertask.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CartValidator @Inject constructor(@ApplicationContext private val context: Context) {
    fun validateOrderName(name: String): ValidationResult {
        if (name.isEmpty())
            return ValidationResult.NAME_ERROR
        return ValidationResult.TRUE
    }

    enum class ValidationResult {
        TRUE, NAME_ERROR
    }

    val errorMessages: Map<ValidationResult, String>
        get() = mapOf(
            Pair(ValidationResult.NAME_ERROR, context.getString(R.string.enter_order_name_error))

        )
}