package com.HomeEssentials.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
