package com.example.springbootsample.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * CSVファイル用のDTO
 */
@Data
@JsonPropertyOrder({ "ID", "Name", "Email", "Age" })
public class UserCsv {
  @JsonProperty("ID")
  private Integer id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Email")
  private String email;
  @JsonProperty("Age")
  private Integer age;

  public UserCsv(Integer id, String name, String email, Integer age) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.age = age;
  }
}