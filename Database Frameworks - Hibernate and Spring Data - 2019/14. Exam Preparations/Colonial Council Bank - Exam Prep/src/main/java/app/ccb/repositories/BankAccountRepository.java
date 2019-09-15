package app.ccb.repositories;

import app.ccb.domain.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    BankAccount findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
}
