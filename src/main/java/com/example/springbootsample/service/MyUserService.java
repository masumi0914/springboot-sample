package com.example.springbootsample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springbootsample.domain.entity.MyUserDetails;
import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.repository.IUserDao;

@Service
public class MyUserService implements UserDetailsService {
  private final IUserDao dao;

  @Autowired
  public MyUserService(IUserDao dao) {
    this.dao = dao;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<User> user = dao.findUser(email);
    if (!user.isPresent()) {
      throw new UsernameNotFoundException(email + "が存在しません");
    }
    return new MyUserDetails(user.get());
  }
}
