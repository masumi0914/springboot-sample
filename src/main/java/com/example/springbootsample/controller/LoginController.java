package com.example.springbootsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
  /**
   * ログイン画面への遷移
   * 
   * @return
   */
  @GetMapping("/login")
  String getLogin() {
    return "login";
  }

  /**
   * ログイン成功時のメニュー画面への遷移
   * 
   * @return
   */
  @PostMapping("/login")
  String postLogin() {
    return "redirect:/";
  }
}
