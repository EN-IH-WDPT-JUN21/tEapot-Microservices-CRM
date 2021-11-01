package com.ironhack.opportunityservice.repository;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByStatusAndProduct(Status status, Product product);
    List<Opportunity> findByProduct(Product product);
    List<Opportunity> findByStatus(Status status);
    List<Opportunity> findByStatusAndSalesrepId(Status status, Long id);
}
