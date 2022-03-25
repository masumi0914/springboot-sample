package com.example.springbootsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springbootsample.controller.form.UserSearchForm;
import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.repository.IUserDao;
import com.example.springbootsample.controller.form.UserRegisterForm;

@Service
@Transactional
public class UserService {

  private final IUserDao dao;

  @Autowired
  public UserService(IUserDao dao) {
    this.dao = dao;
  }

  public List<User> getList(UserSearchForm form) {
    return dao.getList(form);
  }

  public int insert(UserRegisterForm form) {
    // パスワードをhash化して、formオブジェクトにセットする
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    form.setPassword(passwordEncoder.encode(form.getPassword()));

    return dao.insert(form);
  }

  public int delete(Integer id) {
    return dao.delete(id);
  }
}
