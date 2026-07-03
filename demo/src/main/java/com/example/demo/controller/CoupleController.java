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
    public ResponseEntity<Couple> addCouple(@RequestBody Couple couple) {
        Couple savedCouple = coupleRepository.save(couple);
        return new ResponseEntity<>(savedCouple, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCouple(@PathVariable Long id) {
        coupleRepository.deleteById(id);
    }
}