package com.leonardossev.crud.controller

import com.leonardossev.crud.model.dto.ProductDTO
import com.leonardossev.crud.model.dto.ProductStoreDTO
import com.leonardossev.crud.model.entity.ProductEntity
import com.leonardossev.crud.service.impl.ProductServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/product"])
class ProductController {

    @Autowired
    lateinit var productService: ProductServiceImpl;


    @GetMapping
    fun listProduct(): ResponseEntity<List<ProductDTO>> {
        return ResponseEntity<List<ProductDTO>>(this.productService.listProduct(), HttpStatus.OK);
    }

    @GetMapping(value = ["/{id}"])
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductDTO> {
        return ResponseEntity<ProductDTO>(this.productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping
    fun storeProduct(@RequestBody product: ProductStoreDTO): ResponseEntity<ProductDTO> {
        return ResponseEntity<ProductDTO>(this.productService.storeProduct(product), HttpStatus.OK);
    }

    @PutMapping(value = ["/{id}"])
    fun updateProduct(@RequestBody product: ProductStoreDTO, @PathVariable id: Long): ResponseEntity<ProductDTO> {
        return ResponseEntity<ProductDTO>(this.productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping(value = ["/{id}"])
    fun destroyProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity<Unit>(this.productService.deleteProduct(id), HttpStatus.OK);
    }
}