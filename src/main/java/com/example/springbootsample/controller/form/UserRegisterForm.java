package com.example.springbootsample.controller.form;

/**
 * 登録画面からの入力値を格納するためのクラス
 */
public class UserRegisterForm {
  private String name;
  private String email;
  private Integer age;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Integer getAge() {
    return age;
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
