package com.example.springbootsample.controller.form;

import lombok.Data;

/**
 * 一覧画面からのユーザー検索条件を格納するためのクラス
 */
@Data
public class UserSearchForm {
  private String name;
  private String email;
  private Integer age;
}
