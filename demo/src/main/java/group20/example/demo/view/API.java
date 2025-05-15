package group20.example.demo.view;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import group20.example.demo.AtmSimulationApplication;
import group20.example.demo.controller.Controller;

public class API {
  Controller controller;

  /**
   * @param controller
   */
  public API() {
    ApplicationContext context = SpringApplication.run(AtmSimulationApplication.class);
    this.controller = context.getBean(Controller.class);
  }

  public void getUSer() {
    this.controller.getUSerBYEMAIL("21130349@st.hcmuaf.edu.vn", "21130349");
  }
}
