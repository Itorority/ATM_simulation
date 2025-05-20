package group20.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "group20.example.demo")
@EnableJpaRepositories(basePackages = "group20.example.demo.repo")
@EntityScan(basePackages = "group20.example.demo.entity")
@SpringBootApplication
public class AtmSimulationApplication {
  public static void main(String[] args) {
    org.springframework.boot.SpringApplication.run(AtmSimulationApplication.class, args);
  }
}
