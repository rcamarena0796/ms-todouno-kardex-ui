package com.todouno.kardex.controller;

import static org.mockito.Mockito.when;

import com.todouno.kardex.dto.ProductDto;
import com.todouno.kardex.dto.UserDto;
import com.todouno.kardex.service.KardexService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = KardexController.class)
@Import(KardexController.class)
public class KardexControllerTest {

  @Mock
  private List<UserDto> expectedUserList;

  @Mock
  private UserDto expectedUser;

  @Mock
  private List<ProductDto> expectedProductList;

  @Mock
  private ProductDto expectedProduct;


  @BeforeEach
  void setUp() {

    expectedUser =
        UserDto.builder().id("1").username("test").password("aaa").email("aaa@aa.a").phoneNumber(
            "123456789").build();

    expectedUserList = Arrays.asList(
        UserDto.builder().id("1").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build(),
        UserDto.builder().id("2").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build(),
        UserDto.builder().id("3").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build()
    );

    expectedProduct = ProductDto.builder().id("1").name("test").stock(3).price(1.0).build();

    expectedProductList = Arrays.asList(
        ProductDto.builder().id("1").name("test1").stock(3).price(1.0).build(),
        ProductDto.builder().id("2").name("test2").stock(3).price(1.0).build(),
        ProductDto.builder().id("3").name("test3").stock(3).price(1.0).build()
    );
  }

  @MockBean
  protected KardexService service;

  @Autowired
  private WebTestClient webClient;

  @Test
  void getAllUsers() {
    when(service.findAllUsers()).thenReturn(Flux.fromIterable(expectedUserList));

    webClient.get().uri("/kardex/user/findAll")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(UserDto.class)
        .isEqualTo(expectedUserList);
  }

  @Test
  void saveUser() {
    when(service.saveUser(expectedUser)).thenReturn(Mono.just(expectedUser));

    webClient.post()
        .uri("/kardex/user/save").body(Mono.just(expectedUser), UserDto.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(UserDto.class)
        .isEqualTo(expectedUser);
  }

  @Test
  void saveFail() {
    when(service.saveUser(expectedUser)).thenReturn(Mono.error(new Exception()));

    webClient.post()
        .uri("/kardex/user/save").body(Mono.just(expectedUser), UserDto.class)
        .exchange()
        .expectStatus()
        .isEqualTo(500);
  }

  @Test
  void getAllProducts() {
    when(service.findAllProducts()).thenReturn(Flux.fromIterable(expectedProductList));

    webClient.get().uri("/kardex/product/findAll")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ProductDto.class)
        .isEqualTo(expectedProductList);
  }

  @Test
  void saveProduct() {
    when(service.saveProduct(expectedProduct)).thenReturn(Mono.just(expectedProduct));

    webClient.post()
        .uri("/kardex/product/save").body(Mono.just(expectedProduct), ProductDto.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(ProductDto.class)
        .isEqualTo(expectedProduct);
  }


  @Test
  void saveProductFail() {
    when(service.saveProduct(expectedProduct)).thenReturn(Mono.error(new Exception()));

    webClient.post()
        .uri("/kardex/product/save").body(Mono.just(expectedProduct), ProductDto.class)
        .exchange()
        .expectStatus()
        .isEqualTo(500);
  }

  @Test
  void updateProduct_whenProductExists_performUpdate() {
    when(service.updateProduct(expectedProduct.getId(), expectedProduct))
        .thenReturn(Mono.just(expectedProduct));

    webClient.put()
        .uri("/kardex/product/update/{id}", expectedProduct.getId())
        .body(Mono.just(expectedProduct), ProductDto.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(ProductDto.class)
        .isEqualTo(expectedProduct);
  }

  @Test
  void updateProduct_whenProductNotExist_returnNotFound() {
    String id = "-1";
    when(service.updateProduct(id, expectedProduct)).thenReturn(Mono.empty());

    webClient.put()
        .uri("/kardex/product/update/{id}", id)
        .body(Mono.just(expectedProduct), ProductDto.class)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

//  @Test
//  void deleteProduct_whenProductExists_performDeletion() {
//    when(service.deleteProduct(expectedProduct.getId()))
//        .thenReturn(Mono.empty());
//
//    webClient.delete()
//        .uri("/kardex/product/delete/{id}", expectedProduct.getId())
//        .exchange()
//        .expectStatus()
//        .isOk();
//  }


  @Test
  void deleteProduct_whenProductNotExists_returnNotFound() {
    String id = "-1";
    when(service.deleteProduct(id)).thenReturn(Mono.empty());

    webClient.delete()
        .uri("/kardex/product/delete/{id}", id)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

}