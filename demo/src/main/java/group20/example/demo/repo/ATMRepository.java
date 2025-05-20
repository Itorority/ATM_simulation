// package group20.example.demo.repo;

// import java.math.BigDecimal;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import group20.example.demo.entity.ATM;
// import jakarta.transaction.Transactional;

// @Repository
// public interface ATMRepository extends JpaRepository<ATM, Long> {
// /**
// * * Tìm ATM theo ID
// *
// * @param atmId
// */
// public ATM findByAtmId(Long atmId);

// /**
// * update amount of the ATM By ID
// *
// * @param atmtd
// */
// @Modifying
// @Transactional
// @Query("UPDATE ATM a SET a.amount = :amount WHERE a.atmId = :id")
// void updateAmountById(@Param("id") Long atmId, @Param("amount") BigDecimal
// amount);

// }