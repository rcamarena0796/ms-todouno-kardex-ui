package com.todouno.kardex.controller;

import com.todouno.kardex.dto.ProductDto;
import com.todouno.kardex.dto.UserDto;
import com.todouno.kardex.service.KardexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * KardexController.
 */
@Api(tags = "Product API", value = "CRUD operations for products")
@RestController
@RequestMapping("/kardex")
public class KardexController {

  @Autowired
  private KardexService kardexService;

  @ApiOperation(value = "Service used to return all products")
  @GetMapping("/product/findAll")
  public Flux<ProductDto> findAllProducts() {
    return kardexService.findAllProducts();
  }

  /**
   * SAVE A PRODUCT.
   */
  @ApiOperation(value = "Service used to create products")
  @PostMapping("/product/save")
  public Mono<ResponseEntity<ProductDto>> createProduct(@Valid @RequestBody ProductDto product) {
    return kardexService.saveProduct(product)
        .map(c -> ResponseEntity
            .created(URI.create("/products".concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
  }

  /**
   * UPDATE A PRODUCT.
   */
  @ApiOperation(value = "Service used to update a product")
  @PutMapping("/product/update/{id}")
  public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable("id") String id,
      @Valid @RequestBody ProductDto product) {
    return kardexService.updateProduct(id, product)
        .map(c -> ResponseEntity
            .created(URI.create("/products".concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * DELETE A PRODUCT.
   */
  @ApiOperation(value = "Service used to delete a product")
  @DeleteMapping("/product/delete/{id}")
  public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
    return kardexService.deleteProduct(id).map(res -> ResponseEntity
        .ok()
        .<Void>build())
        .defaultIfEmpty(ResponseEntity
            .notFound()
            .build()
        );

  }

  @ApiOperation(value = "Service used to return all users")
  @GetMapping("/user/findAll")
  public Flux<UserDto> findAllUsers() {
    return kardexService.findAllUsers();
  }

  /**
   * SAVE A USER.
   */
  @ApiOperation(value = "Service used to create users")
  @PostMapping("/user/save")
  public Mono<ResponseEntity<UserDto>> createUser(@Valid @RequestBody UserDto user) {
    return kardexService.saveUser(user)
        .map(c -> ResponseEntity
            .created(URI.create("/users".concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
  }
}