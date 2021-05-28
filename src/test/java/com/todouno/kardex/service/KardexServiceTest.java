package com.todouno.kardex.service;

import com.todouno.kardex.dto.UserDto;
import com.todouno.kardex.proxy.ProductProxy;
import com.todouno.kardex.proxy.UserProxy;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;


@SpringBootTest
public class KardexServiceTest {

  @Mock
  private ProductProxy productProxy;

  @Mock
  private UserProxy userProxy;


  @Autowired
  private KardexService kardexService;

  @Mock
  private static UserDto user1;

  @Mock
  private static List<UserDto> expectedUserList;


  @BeforeAll
  public static void setUp() {

    user1 = UserDto.builder().id("1").username("test").password("aaa").email("aaa@aa.a")
        .phoneNumber(
            "123456789").build();

    expectedUserList = Arrays.asList(
        UserDto.builder().id("1").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build(),
        UserDto.builder().id("2").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build(),
        UserDto.builder().id("3").username("test").password("*****").email("aaa@aa.a").phoneNumber(
            "123456789").build()
    );

  }


  private void assertResults(Publisher<UserDto> publisher, UserDto... expectedClientTypes) {
    StepVerifier
        .create(publisher)
        .expectNext(expectedClientTypes)
        .verifyComplete();
  }

}

