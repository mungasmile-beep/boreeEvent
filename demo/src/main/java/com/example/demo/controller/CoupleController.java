package com.example.demo.controller;

import com.example.demo.model.Couple;
import com.example.demo.repository.CoupleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couples")
@CrossOrigin(origins = "*")
public class CoupleController {

    @Autowired
    private CoupleRepository coupleRepository;

    @GetMapping
    public List<Couple> getAllCouples() {
        return coupleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addCouple(@RequestBody Couple couple) {
        try {
            // Tentative de sauvegarde
            Couple savedCouple = coupleRepository.save(couple);
            return new ResponseEntity<>(savedCouple, HttpStatus.CREATED);
        } catch (Exception e) {
            // Affiche l'erreur réelle dans les logs du serveur pour comprendre le crash
            e.printStackTrace(); 
            // Retourne un message explicite au client pour le débogage
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCouple(@PathVariable Long id) {
        coupleRepository.deleteById(id);
    }
}