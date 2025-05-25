package group20.example.demo.model;

import java.time.LocalDate;

public class UserModel {

    private Long userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String userPassword;

    private LocalDate dateOfBirth;

    // Constructors
    public UserModel() {
    }

    public UserModel(Long userId, String fullName, String email, String phoneNumber, String userPassword, LocalDate dateOfBirth) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters & Setters
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

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            '}';
    }
}
