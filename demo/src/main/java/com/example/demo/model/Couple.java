package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "couple")
@Data
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Utilisation de LocalDate pour une compatibilité parfaite avec le type DATE SQL
    @Column(name = "datemariage")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datemariage;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGTEXT")
    private String photo;
}