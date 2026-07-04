package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "guest")
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "couple_id")
    @JsonIgnore
    private Couple couple;

    @Column(name = "guest_table")
    private String table;

    @Column(name = "status")
    private String status;

    @Column(name = "drink_choice")
    private String drinkChoice;
}