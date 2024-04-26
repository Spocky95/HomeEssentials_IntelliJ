package com.HomeEssentials.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="order_item")
@Getter
@Setter
public class OrderItem {

        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "unit_price")
        private BigDecimal unitPrice;

        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;

        @Column(name = "total_price")
        private BigDecimal totalPrice;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;

}
