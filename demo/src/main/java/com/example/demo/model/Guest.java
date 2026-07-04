package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "couple_id")
    @JsonIgnore
    private Couple couple;

    @Column(name = "guest_table")
    private String table;

    private String status;

    // Suppression de @Transient et ajout du nom exact de la colonne en base
    @Column(name = "drink_choice")
    private String drinkChoice;
}