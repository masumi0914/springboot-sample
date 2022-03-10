package com.example.springbootsample.entity;

/**
 * Userテーブルのデータ格納用クラス
 */
public class User {
  private Integer id;
  private String name;
  private String email;
  private Integer age;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Integer getAge() {
    return age;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
