package com.HomeEssentials.service;

import com.HomeEssentials.dto.Purchase;
import com.HomeEssentials.dto.PurchaseResponse;

public interface CheckoutService {

        PurchaseResponse placeOrder(Purchase purchase);
}
