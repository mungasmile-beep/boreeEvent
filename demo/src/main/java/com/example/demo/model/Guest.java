package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    @Column(name = "status", columnDefinition = "TEXT")
    private String status;

    @Column(name = "guest_table", columnDefinition = "TEXT")
    private String guestTable;

    @Column(name = "drink_choice", columnDefinition = "TEXT")
    private String drinkChoice;

    @ManyToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    // Constructeur vide requis par JPA
    public Guest() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getGuestTable() { return guestTable; }
    public void setGuestTable(String guestTable) { this.guestTable = guestTable; }

    public String getDrinkChoice() { return drinkChoice; }
    public void setDrinkChoice(String drinkChoice) { this.drinkChoice = drinkChoice; }

    public Couple getCouple() { return couple; }
    public void setCouple(Couple couple) { this.couple = couple; }
}