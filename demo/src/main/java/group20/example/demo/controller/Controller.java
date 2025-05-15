package group20.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group20.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Controller {
  @Autowired
  private UserService service;

  public void getUSerBYEMAIL(String email, String pass) {
    this.service.findByEmailAndPassword(email, pass);
    System.err.println("asdasdasdasdasd");
  }
}
