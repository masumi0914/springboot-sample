package com.example.springbootsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.springbootsample.model.User;

/**
 * PostRegisterコントローラークラス
 */
@Controller
public class PostRegisterController {
  /**
   * 入力画面へ遷移する
   * 
   * @param user Userオブジェクト
   * @return 入力画面へのパス
   */
  @GetMapping("/form")
  private String readForm(@ModelAttribute User user) {
    return "form";
  }

  /**
   * 確認画面へ遷移する
   * 
   * @param user Userオブジェクト
   * @return 確認画面へのパス
   */
  @PostMapping("/confirm")
  private String confirm(@ModelAttribute User user) {
    return "confirm";
  }

  /**
   * 完了画面へのリダイレクトパスに遷移する
   * 
   * @return 完了画面へのリダイレクトパス
   */
  @PostMapping("/register")
  private String register() {
    return "redirect:/complete";
  }

  /**
   * 完了画面に遷移する
   * 
   * @return 完了画面へのパス
   */
  @GetMapping("/complete")
  private String complete() {
    return "complete";
  }
}
