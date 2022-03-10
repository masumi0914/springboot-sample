package com.example.springbootsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootsample.repository.IUserDao;
import com.example.springbootsample.entity.User;
import com.example.springbootsample.controller.form.UserSearchForm;

@Service
@Transactional
public class UserService {

  private final IUserDao dao;

  @Autowired
  public UserService(IUserDao dao) {
    this.dao = dao;
  }

  public List<User> getUserList(UserSearchForm form) {
    return dao.getUserList(form);
  }
}