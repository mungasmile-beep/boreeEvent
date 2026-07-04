package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "guest")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    // Permet de recevoir l'ID du couple en entrée sans causer de boucle infinie
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Couple couple;

    @Column(name = "guest_table")
    private String guestTable;

    @Column(name = "status")
    private String status;

    @Column(name = "drink_choice")
    private String drinkChoice;
}