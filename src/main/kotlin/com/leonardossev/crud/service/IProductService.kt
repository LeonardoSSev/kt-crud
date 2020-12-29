package com.leonardossev.crud.service

import com.leonardossev.crud.model.dto.ProductDTO
import com.leonardossev.crud.model.dto.ProductStoreDTO

interface IProductService {

    fun listProduct(): List<ProductDTO>

    fun getProduct(id: Long): ProductDTO

    fun storeProduct(productStore: ProductStoreDTO): ProductDTO

    fun updateProduct(productStore: ProductStoreDTO, id: Long): ProductDTO

    fun deleteProduct(id: Long): Unit
}