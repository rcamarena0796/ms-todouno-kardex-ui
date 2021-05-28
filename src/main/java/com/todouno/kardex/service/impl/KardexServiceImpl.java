package com.todouno.kardex.service.impl;

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import com.todouno.kardex.config.ConfigProperties;
import com.todouno.kardex.dto.ProductDto;
import com.todouno.kardex.dto.UserDto;
import com.todouno.kardex.proxy.ProductProxy;
import com.todouno.kardex.proxy.UserProxy;
import com.todouno.kardex.service.KardexService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * KardexServiceImpl.
 */
@Service
public class KardexServiceImpl implements KardexService {

  private ProductProxy productProxy;
  private UserProxy userProxy;

  @Autowired
  private ConfigProperties configProperties;

  /**
   * setupInit.
   */
  @PostConstruct
  public void setupInit() {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(configProperties.getProductUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ReactorCallAdapterFactory.create())
        .build();

    productProxy = retrofit.create(ProductProxy.class);

    Retrofit retrofitUser = new Retrofit.Builder()
        .baseUrl(configProperties.getUserUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ReactorCallAdapterFactory.create())
        .build();

    userProxy = retrofitUser.create(UserProxy.class);

  }

  @Override
  public Flux<ProductDto> findAllProducts() {
    return productProxy.findAll().flatMapMany(Flux::fromIterable);
  }

  @Override
  public Mono<ProductDto> saveProduct(ProductDto product) {
    return productProxy.saveProduct(product);
  }

  @Override
  public Mono<ProductDto> updateProduct(String id, ProductDto product) {
    return productProxy.updateProduct(id, product);
  }

  @Override
  public Mono<Void> deleteProduct(String id) {
    return productProxy.deleteProduct(id);
  }

  @Override
  public Flux<UserDto> findAllUsers() {
    return userProxy.findAll().flatMapMany(Flux::fromIterable);
  }

  @Override
  public Mono<UserDto> saveUser(UserDto user) {
    return userProxy.saveProduct(user);
  }
}
