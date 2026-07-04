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
    @JsonIgnore // Empêche la boucle infinie lors de la conversion en JSON
    private Couple couple;

    // Évite le bug du mot-clé réservé 'table' dans MySQL/PostgreSQL
    @Column(name = "guest_table")
    private String table;

    private String status;

    // Mappage correct avec la colonne drink_choice de la base de données
    @Column(name = "drink_choice")
    private String drinkChoice;
}