package com.HomeEssentials.dao;

import com.HomeEssentials.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String theEmail);
    //SELECT * FROM Customer WHERE email = theEmail


}
