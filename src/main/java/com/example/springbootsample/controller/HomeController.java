package com.example.springbootsample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Homeコントローラークラス
 */
@Controller
@RequestMapping
public class HomeController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Home画面を表示させる
   * 
   * @return Home画面へのパス
   */
  @GetMapping("/")
  public String index(Model model) {
    // クエリを作成
    String sql = "SELECT * FROM test_table";
    // クエリを実行
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    // Modelにlistオブジェクトを追加(viewで使用)
    model.addAttribute("testList", list);
    // viewファイルの指定
    return "home";
  }
}
