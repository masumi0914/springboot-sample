package com.example.springbootsample.repository;

import java.util.List;
import com.example.springbootsample.entity.User;
import com.example.springbootsample.controller.form.UserSearchForm;

public interface IUserDao {
  List<User> getUserList(UserSearchForm form);
}
