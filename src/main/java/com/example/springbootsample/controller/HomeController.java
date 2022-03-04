package com.example.springbootsample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Homeコントローラークラス
 */
@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Home画面を表示させる
   * 
   * @param model
   * @return Home画面へのパス
   */
  @GetMapping("/")
  public String index(Model model) {
    // クエリを作成
    String sql = "SELECT * FROM test_table";
    // クエリを実行
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    // Modelにlistオブジェクトを追加(viewで使用)
    model.addAttribute("userList", list);
    // viewファイルの指定
    return "home";
  }

  /**
   * 新規登録画面へのリダイレクトパスに遷移する
   * 
   * @return 新規登録入力画面へのリダイレクトパス
   */
  @PostMapping("/register")
  public String register() {
    return "redirect:/register/form";
  }

  /**
   * ユーザーを削除する
   * 
   * @param id ユーザーID
   * @return Home画面へのリダイレクトパス
   */
  @PostMapping("/delete")
  public String delete(@RequestParam Integer id) {
    // クエリを作成
    String sql = "DELETE FROM test_table WHERE id = ?";
    // クエリを実行
    jdbcTemplate.update(sql, id);

    return "redirect:/";
  }
}
