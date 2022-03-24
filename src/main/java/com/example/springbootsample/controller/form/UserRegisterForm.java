package com.example.springbootsample.controller.form;

import lombok.Data;

/**
 * 登録画面からの入力値を格納するためのクラス
 */
@Data
public class UserRegisterForm {
  private String name;
  private String email;
  private Integer age;
  private String password;
}
