package com.todouno.kardex.proxy;

import com.todouno.kardex.dto.ProductDto;
import com.todouno.kardex.dto.UserDto;
import java.util.List;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * UserProxy.
 */
public interface UserProxy {

  @GET("findAll")
  public Mono<List<UserDto>> findAll();

  @POST("save")
  public Mono<UserDto> saveProduct(@Body UserDto user);

}
