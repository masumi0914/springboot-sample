package com.example.springbootsample.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.example.springbootsample.controller.form.UserSearchForm;
import com.example.springbootsample.service.UserService;
import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.domain.dto.UserCsv;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * UserListコントローラークラス
 */
@Controller
@RequestMapping("/")
public class UserListController {

  private final UserService userService;

  @Autowired
  public UserListController(UserService userService) {
    this.userService = userService;
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
    // ユーザー一覧を取得
    List<User> list = userService.getUserList(form);

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
   * @param form
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "*.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
      + "; charset=Shift_JIS; Content-Disposition: attachment")
  @ResponseBody
  public Object download(@ModelAttribute UserSearchForm form) throws JsonProcessingException {
    // DBから取得
    List<User> list = userService.getUserList(form);

    // Dtoに詰め直す
    List<UserCsv> csvs = list.stream().map(
        e -> new UserCsv(e.getId(), e.getName(), e.getEmail(), e.getAge())).collect(Collectors.toList());

    // Csvをダウンロードする
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(UserCsv.class).withHeader();

    return mapper.writer(schema).writeValueAsString(csvs);
  }
}
