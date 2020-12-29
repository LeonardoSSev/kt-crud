package com.leonardossev.crud.service.impl

import com.leonardossev.crud.model.dto.ProductDTO
import com.leonardossev.crud.model.dto.ProductStoreDTO
import com.leonardossev.crud.model.entity.ProductEntity
import com.leonardossev.crud.repository.ProductRepository
import com.leonardossev.crud.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductServiceImpl : IProductService {

    @Autowired
    lateinit var productRepository: ProductRepository;

    override fun listProduct(): List<ProductDTO> {
        val products = this.productRepository.findAll()

        return products.map { ProductDTO(it.id, it.name, it.price) }
    }

    override fun getProduct(id: Long): ProductDTO {
        val productOptional = this.productRepository.findById(id)

        if (!this.productRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with given id: $id")
        }

        val product = productOptional.get()

        return ProductDTO(product.id, product.name, product.price)
    }

    override fun storeProduct(productStore: ProductStoreDTO): ProductDTO {
        val productEntity = ProductEntity(name = productStore.name, price = productStore.price)

        val productStored = this.productRepository.save(productEntity)

        return ProductDTO(productStored.id, productStored.name, productStored.price)
    }

    override fun updateProduct(productStore: ProductStoreDTO, id: Long): ProductDTO {
        val productOptional = this.productRepository.findById(id)

        if (!this.productRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with given id: $id")
        }

        var productFound = productOptional.get()

        productFound.name = productStore.name
        productFound.price = productStore.price

        val productUpdated = this.productRepository.save(productFound)

        return ProductDTO(productUpdated.id, productUpdated.name, productUpdated.price)
    }

    override fun deleteProduct(id: Long) {
        if (!this.productRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with given id: $id")
        }

        this.productRepository.deleteById(id)
    }


}