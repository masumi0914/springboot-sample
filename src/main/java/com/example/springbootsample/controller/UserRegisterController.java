package com.example.springbootsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.springbootsample.controller.form.UserRegisterForm;
import com.example.springbootsample.service.UserService;

/**
 * UserRegisterコントローラークラス
 * 
 * 画面からの入力値をUserRegisterFormオブジェクトに格納する
 * UserRegisterFormオブジェクトは、セッションとして持たせ画面間で持ち回す
 */
@Controller
@RequestMapping("/register")
@SessionAttributes(types = UserRegisterForm.class)
public class UserRegisterController {

  private final UserService userService;

  @Autowired
  public UserRegisterController(UserService userService) {
    this.userService = userService;
  }

  /**
   * オブジェクトをHTTPセッションに追加する
   * 
   * @return UserRegisterFormオブジェクト
   */
  @ModelAttribute("userRegisterForm")
  public UserRegisterForm setForm() {
    return new UserRegisterForm();
  }

  /**
   * 入力画面へ遷移する
   * 
   * @param UserRegisterForm
   * @return 入力画面へのパス
   */
  @GetMapping("/form")
  private String form(@ModelAttribute UserRegisterForm userRegisterForm) {
    return "user_register_form";
  }

  /**
   * 確認画面へ遷移する
   * （自動的にUserオブジェクトが生成されセッションに格納される）
   * 
   * @param UserRegisterForm
   * @return 確認画面へのパス
   */
  @PostMapping("/confirm")
  private String confirm(@ModelAttribute UserRegisterForm userRegisterForm) {
    return "user_register_confirm";
  }

  /**
   * DBに画面からの入力値を登録し、Home画面へ遷移する
   * 
   * @param UserRegisterForm
   * @param sessionStatus
   * @return Home画面へのリダイレクトパス
   */
  @PostMapping("/complete")
  private String complete(@ModelAttribute UserRegisterForm userRegisterForm, SessionStatus sessionStatus) {
    // DBに登録
    userService.insert(userRegisterForm);
    // セッションを破棄
    sessionStatus.setComplete();

    return "redirect:/";
  }
}
