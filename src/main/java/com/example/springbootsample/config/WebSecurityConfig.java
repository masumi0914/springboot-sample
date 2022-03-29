package com.example.springbootsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springbootsample.service.MyUserService;

/**
 * WebSecurityConfigクラス
 * 
 * EnableWebSecurityを付与し「Spring Security」を有効化する
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private MyUserService userService;

  @Autowired
  public WebSecurityConfig(MyUserService userService) {
    this.userService = userService;
  }

  /**
   * セキュリティで保護するURLを設定する
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    // 静的ファイル以外のアクセスについては、認証をするように設定
    http
        .authorizeRequests()
        .antMatchers("/js/**", "/css/**", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/", true)
        .failureUrl("/login?error=true")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutUrl("/logout") // ログアウトのURL
        .invalidateHttpSession(true);
  }

  /**
   * 認証を行う
   */
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    ;
  }

  /**
   * パスワードをhash化しエンコードする
   * 
   * @return BCryptPasswordEncoder
   */
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    return bcpe;
  }
}
