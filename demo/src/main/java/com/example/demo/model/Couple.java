package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String datemariage;
    @Lob
@Column(name = "photo", columnDefinition = "LONGTEXT")
private String photo;
}