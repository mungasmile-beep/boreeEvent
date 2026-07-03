package com.example.demo.repository;

import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Cette ligne va générer automatiquement la requête SQL : SELECT * FROM guest WHERE couple_id = ?
    List<Guest> findByCoupleId(Long coupleId); 
}