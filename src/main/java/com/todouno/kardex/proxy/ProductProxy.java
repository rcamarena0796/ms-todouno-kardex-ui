package com.todouno.kardex.proxy;

import com.todouno.kardex.dto.ProductDto;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * ProductProxy.
 */
public interface ProductProxy {

  @GET("findAll")
  public Mono<List<ProductDto>> findAll();

  @POST("save")
  public Mono<ProductDto> saveProduct(@Body ProductDto product);

  @PUT("update/{id}")
  public Mono<ProductDto> updateProduct(@Path("id") String id, @Body ProductDto product);

  @DELETE("delete/{id}")
  public Mono<Void> deleteProduct(@Path("id") String id);

}