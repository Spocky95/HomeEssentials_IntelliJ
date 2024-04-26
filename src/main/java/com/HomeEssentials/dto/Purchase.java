package com.HomeEssentials.dto;

import com.HomeEssentials.entity.Address;
import com.HomeEssentials.entity.Customer;
import com.HomeEssentials.entity.Order;
import com.HomeEssentials.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

        private Customer customer;
        private Address shippingAddress;
        private Address billingAddress;
        private Order order;
        private Set<OrderItem> orderItems;
}
