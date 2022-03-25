package com.example.springbootsample.repository;

import java.util.List;
import java.util.Optional;

import com.example.springbootsample.controller.form.UserSearchForm;
import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.controller.form.UserRegisterForm;

public interface IUserDao {

  // ユーザー一覧を取得する
  List<User> getList(UserSearchForm form);

  // ユーザーを取得する
  Optional<User> findUser(String email);

  // ユーザーを登録する
  int insert(UserRegisterForm form);

  // ユーザーを削除する
  int delete(Integer id);
}
