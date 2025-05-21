// package group20.example.demo.repo;

// import java.util.List;

// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import group20.example.demo.entity.Transaction;
// import jakarta.transaction.Transactional;

// @Repository
// public interface TransantionRepository extends JpaRepository<Transaction,
// Long> {

// /**
// * select and print n transaction in database with accounntn number
// *
// * @param accountNumber
// * @param quantity
// * @return
// */

// @Transactional //
// @Query("""
// SELECT t FROM Transaction t
// WHERE t.accountNumber = :accountNumber
// ORDER BY t.dateCreated DESC
// """)
// List<Transaction> findTopNByAccountNumber(@Param("accountNumber") String
// accountNumber, Pageable pageable);
// }
