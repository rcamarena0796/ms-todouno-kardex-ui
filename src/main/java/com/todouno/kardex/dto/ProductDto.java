package com.todouno.kardex.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ProductDto.
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductDto {

  private String id;
  private String name;

  @NotNull(message = "stock is required")
  private Integer stock;

  @NotNull(message = "price is required")
  private Double price;

  private Date creationDate;

}