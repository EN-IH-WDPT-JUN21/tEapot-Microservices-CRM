package com.Ironhack.AccountService.repository;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.enums.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}