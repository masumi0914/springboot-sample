package com.example.springbootsample.domain.entity;

import lombok.Data;

/**
 * Userテーブルのデータ格納用クラス
 */
@Data
public class User {
  private Integer id;
  private String name;
  private String email;
  private Integer age;
  private String password;
}
