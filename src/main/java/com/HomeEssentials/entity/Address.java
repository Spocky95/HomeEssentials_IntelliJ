package com.HomeEssentials.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "street")
        private String street;
        @Column(name = "city")
        private String city;
        @Column(name = "state")
        private String state;
        @Column(name = "country")
        private String country;
        @Column(name = "zip_code")
        private String zipCode;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToOne
        @PrimaryKeyJoinColumn
        @JsonIgnore//to avoid infinite recursion
        private Order order;
}
