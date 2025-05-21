// package group20.example.demo.repo;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import group20.example.demo.entity.User;
// import jakarta.transaction.Transactional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Integer> {

// /**
// * Check if a user exists by email and password and return user object
// *
// * @param email
// * @param userPassword
// * @return
// */
// public Boolean existsByEmailAndUserPassword(String email, String
// userPassword);

// /**
// * Find user by email and password
// *
// * @param email
// * @param userPassword
// * @return
// */
// public User findByEmailAndUserPassword(String email, String userPassword);

// /**
// * Update email by id
// *
// * @param userId
// * @param email
// */
// @Modifying // <-- Báo cho Spring Data JPA biết đây là một câu truy vấn CẬP
// NHẬT
// @Transactional // <-- Đảm bảo giao dịch được hoàn thành
// @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
// void updateEmailById(@Param("id") Long userId, @Param("email") String email);

// /**
// * Update password by id
// *
// * @param userId
// * @param userPassword
// */

// @Modifying
// @Transactional
// @Query("UPDATE User u SET u.userPassword = :password WHERE u.id = :id")
// void updatePasswordById(@Param("id") Long userId, @Param("password") String
// userPassword);

// /**
// * Update phone number by id
// *
// * @param userId
// * @param phoneNumber
// */
// @Modifying
// @Transactional
// @Query("UPDATE User u SET u.phoneNumber = :phoneNumber WHERE u.id = :id")
// void updatePhoneNumberById(@Param("id") Long userId, @Param("phoneNumber")
// String phoneNumber);

// }
