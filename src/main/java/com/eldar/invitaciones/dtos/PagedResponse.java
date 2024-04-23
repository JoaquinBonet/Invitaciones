package com.eldar.invitaciones.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagedResponse {
  private Integer pageNumber;
  private Integer pageSize;
  private Integer pages;
  private Long totalElements;

  private List<Object> pageElements;

  public PagedResponse(Integer pageNumber, Integer pageSize, Integer pages, Long totalElements,
                       List<Object> pageElements) {

    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.pages = pages;
    this.totalElements = totalElements;
    this.pageElements = pageElements;

  }
}
