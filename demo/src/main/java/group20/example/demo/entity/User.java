package group20.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Make sure the table name matches exactly
public class User {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "user_password")
  private String userPassword;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  /**
   * @param userId
   * @param fullName
   * @param email
   * @param phoneNumber
   * @param userPassword
   * @param dateOfBirth
   */
  public User(Long userId, String fullName, String email, String phoneNumber, String userPassword,
      LocalDate dateOfBirth) {
    this.userId = userId;
    this.fullName = fullName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userPassword = userPassword;
    this.dateOfBirth = dateOfBirth;
  }

  // Getters and Setters
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

}
