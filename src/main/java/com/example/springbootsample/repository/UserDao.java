package com.example.springbootsample.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springbootsample.controller.form.UserSearchForm;
import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.controller.form.UserRegisterForm;

@Repository
public class UserDao implements IUserDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<User> getList(UserSearchForm form) {
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("SELECT * FROM users WHERE state = 1");

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
    List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
    // return用の空配列を用意
    List<User> list = new ArrayList<User>();

    // データをUserにまとめる
    for (Map<String, Object> result : resultList) {
      User user = new User();
      user.setId((Integer) result.get("id"));
      user.setName((String) result.get("name"));
      user.setEmail((String) result.get("email"));
      user.setAge((Integer) result.get("age"));
      list.add(user);
    }
    return list;
  }

  @Override
  public int insert(UserRegisterForm form) {
    int count = 0;
    String sql = "INSERT INTO users(name, email, age) VALUES(:name, :email, :age);";

    Map<String, Object> param = new HashMap<>();
    // paramを設定
    param.put("name", form.getName());
    param.put("email", form.getEmail());
    param.put("age", form.getAge());

    count = jdbcTemplate.update(sql, param);
    return count;
  }

  @Override
  public int delete(Integer id) {
    int count = 0;
    String sql = "UPDATE users SET state = 0 WHERE id = :id";

    Map<String, Object> param = new HashMap<>();
    // paramを設定
    param.put("id", id);

    count = jdbcTemplate.update(sql, param);
    return count;
  }
}
