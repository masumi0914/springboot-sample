package com.example.springbootsample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.springbootsample.controller.form.UserSearchForm;
import com.example.springbootsample.service.UserService;
import com.example.springbootsample.service.CsvService;
import com.example.springbootsample.domain.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * UserListコントローラークラス
 */
@Controller
@RequestMapping("/")
public class UserListController {

  @Autowired
  HttpSession session;

  private final UserService userService;
  private final CsvService csvService;

  @Autowired
  public UserListController(UserService userService, CsvService csvService) {
    this.userService = userService;
    this.csvService = csvService;
  }

  /**
   * ユーザー一覧画面を表示させる
   * 
   * @param form
   * @param model
   * @return
   */
  @GetMapping("/")
  public String index(@ModelAttribute UserSearchForm form, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // Principalからログインユーザの情報を取得
    String userName = auth.getName();

    // ユーザー一覧を取得
    List<User> list = userService.getList(form);

    // ユーザー一覧をセッションに保存する
    session.setAttribute("list", list);

    // 画面にデータを渡す
    model.addAttribute("username", userName);
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
   * @param id
   * @return ユーザー一覧画面へのリダイレクトパス
   */
  @PostMapping("/delete")
  public String delete(@RequestParam Integer id) {
    // ユーザーを削除する
    userService.delete(id);

    return "redirect:/";
  }

  /**
   * CSVをダウンロードする
   * 
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "*.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
      + "; charset=Shift_JIS; Content-Disposition: attachment")
  @ResponseBody
  public Object download() throws JsonProcessingException {
    // sessionに保持されているユーザー一覧を取得する
    Object list = session.getAttribute("list");
    // csvをダウンロードする
    return csvService.load(list);
  }
}
