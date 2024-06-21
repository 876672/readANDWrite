package com.estatementsgenerate.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estatementsgenerate.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, String> {

}
