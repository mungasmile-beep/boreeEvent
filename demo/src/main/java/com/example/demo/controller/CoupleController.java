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
        try {
            return coupleRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @PostMapping
    public ResponseEntity<?> addCouple(@RequestBody Couple couple) {
        try {
            Couple savedCouple = coupleRepository.save(couple);
            return new ResponseEntity<>(savedCouple, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCouple(@PathVariable Long id) {
        try {
            coupleRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}