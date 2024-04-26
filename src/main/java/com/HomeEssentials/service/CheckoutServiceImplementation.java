package com.HomeEssentials.service;

import com.HomeEssentials.dao.CustomerRepository;
import com.HomeEssentials.dto.Purchase;
import com.HomeEssentials.dto.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImplementation  implements CheckoutService {

    private CustomerRepository customerRepository;
    @Autowired
    public CheckoutServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        return null;
    }
}
