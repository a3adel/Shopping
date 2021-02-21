package com.example.capitertask.domain.use_cases

import javax.inject.Inject

class CartInteractor @Inject constructor(
    val createCartUseCase: CreateCartUseCase,
    val getCartOrdersUseCase: GetCartOrdersUseCase,
    val deleteItemUseCase: DeleteItemUseCase
)