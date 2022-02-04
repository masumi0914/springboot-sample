package com.example.springbootsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springbootsample.model.User;

/**
 * UserRegisterコントローラークラス
 * 
 * 画面からの入力値をUserオブジェクトに格納する
 * Userオブジェクトは、セッションとして持たせ画面間で持ち回す
 */
@Controller
@RequestMapping("/register")
@SessionAttributes(value = "user")
public class UserRegisterController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @ModelAttribute("user")
  private User user() {
    return new User();
  }

  /**
   * 入力画面へ遷移する
   * 
   * @param user Userオブジェクト
   * @return 入力画面へのパス
   */
  @GetMapping("/form")
  private String form(@ModelAttribute User user) {
    return "form";
  }

  /**
   * 確認画面へ遷移する
   * （自動的にUserオブジェクトが生成されセッションに格納される）
   * 
   * @param user Userオブジェクト
   * @return 確認画面へのパス
   */
  @PostMapping("/confirm")
  private String confirm(@ModelAttribute User user) {
    return "confirm";
  }

  /**
   * DBに画面からの入力値を登録し、Home画面へ遷移する
   * 
   * @param sessionStatus セッションステータス
   * @return Home画面へのリダイレクトパス
   */
  @PostMapping("/complete")
  private String complete(@ModelAttribute User user, SessionStatus sessionStatus) {
    // クエリを作成
    String sql = "INSERT INTO test_table(name, email, age) VALUES(?, ?, ?);";
    // クエリを実行
    jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge());
    // セッションを破棄
    sessionStatus.setComplete();
    return "redirect:/";
  }
}
