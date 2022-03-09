package com.example.springbootsample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.springbootsample.controller.form.UserSearchForm;

/**
 * UserListコントローラークラス
 */
@Controller
@RequestMapping("/")
public class UserListController {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  /**
   * ユーザー一覧画面を表示させる
   * 
   * @param Model
   * @param model
   * @return ユーザー一覧画面へのパス
   */
  @GetMapping("/")
  public String index(@ModelAttribute UserSearchForm form, Model model) {
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("SELECT * FROM test_table WHERE state = 1");

    Map<String, Object> param = new HashMap<>();
    // 入力値が空でない場合、WHERE句にセット
    if (form.getName() != null && form.getName() != "") {
      sqlBuilder.append(" AND name = :name");
      param.put("name", form.getName());
    }
    if (form.getEmail() != null && form.getEmail() != "") {
      sqlBuilder.append(" AND email = :email");
      param.put("email", form.getEmail());
    }
    if (form.getAge() != null) {
      sqlBuilder.append(" AND age = :age");
      param.put("age", form.getAge());
    }

    String sql = sqlBuilder.toString();
    // ユーザー一覧を取得
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);

    // 画面にデータを渡す
    model.addAttribute("userList", list);
    model.addAttribute("form", form);

    return "user_list";
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
   * @return ユーザー一覧画面へのリダイレクトパス
   */
  @PostMapping("/delete")
  public String delete(@RequestParam Integer id) {
    String sql = "UPDATE test_table SET state = 0 WHERE id = :id";

    Map<String, Object> param = new HashMap<>();
    param.put("id", id);

    jdbcTemplate.update(sql, param);

    return "redirect:/";
  }
}
