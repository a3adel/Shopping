package com.example.capitertask.domain.useCases

import javax.inject.Inject

class CartInteractor @Inject constructor(
    val createCartUseCase: CreateCartUseCase,
    val getCartOrdersUseCase: GetCartOrdersUseCase,
    val deleteItemUseCase: DeleteItemUseCase,
    val getProductsUseCase:GetProductsUseCase
)