package com.saifkhan.productdbapp


class MainRepository(
) {
 //   suspend fun getProducts() = apiService.getProducts()

    suspend fun getAllProducts() = RetrofitInstance.productApi.getProducts()

}