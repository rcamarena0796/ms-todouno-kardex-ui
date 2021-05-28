package com.todouno.kardex.service;


import com.todouno.kardex.dto.ProductDto;
import com.todouno.kardex.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * KardexService.
 */
public interface KardexService {

  public Flux<ProductDto> findAllProducts();

  public Mono<ProductDto> saveProduct(ProductDto product);

  public Mono<ProductDto> updateProduct(String id, ProductDto product);

  public Mono<Void> deleteProduct(String id);

  public Flux<UserDto> findAllUsers();

  public Mono<UserDto> saveUser(UserDto user);

}