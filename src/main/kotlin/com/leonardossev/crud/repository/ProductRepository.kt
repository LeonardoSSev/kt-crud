package com.leonardossev.crud.repository

import com.leonardossev.crud.model.entity.ProductEntity
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductEntity, Long> {

}