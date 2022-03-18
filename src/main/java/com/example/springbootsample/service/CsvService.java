package com.example.springbootsample.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootsample.domain.entity.User;
import com.example.springbootsample.domain.dto.UserCsv;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Transactional
public class CsvService {

  public Object load(Object list) throws JsonProcessingException {
    // listをObjectからList<User>にキャストする
    List<User> users = new ArrayList<User>();
    if (list instanceof ArrayList<?>) {
      ArrayList<?> al = (ArrayList<?>) list;
      if (al.size() > 0) {
        for (int i = 0; i < al.size(); i++) {
          Object o = al.get(i);
          if (o instanceof User) {
            User v = (User) o;
            users.add(v);
          }
        }
      }
    }

    // Dtoに詰め直す
    List<UserCsv> csvs = users.stream().map(
        e -> new UserCsv(e.getId(), e.getName(), e.getEmail(), e.getAge())).collect(Collectors.toList());

    // Csvをダウンロードする
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(UserCsv.class).withHeader();

    return mapper.writer(schema).writeValueAsString(csvs);
  }
}
