package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.model.Couple;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.CoupleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;
    
    @Autowired
    private CoupleRepository coupleRepository;

    @GetMapping("/guests/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return guestRepository.findById(id)
                .map(guest -> ResponseEntity.ok().body(guest))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/guests")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Guest>> getGuestsByCouple(@RequestParam(name = "coupleId") Long coupleId) {
        return ResponseEntity.ok(guestRepository.findByCoupleId(coupleId));
    }

    @PostMapping("/guests")
    @Transactional
    public ResponseEntity<?> createGuest(@RequestBody Guest guest) {
        if (guest.getCouple() == null || guest.getCouple().getId() == null) {
            return ResponseEntity.badRequest().body("L'ID du couple est requis.");
        }
        
        Couple couple = coupleRepository.findById(guest.getCouple().getId())
                .orElseThrow(() -> new RuntimeException("Couple introuvable avec l'ID : " + guest.getCouple().getId()));
        
        guest.setCouple(couple);
        guest.setStatus("En attente");
        
        return ResponseEntity.ok(guestRepository.save(guest));
    }

    @PutMapping("/guests/{id}/status")
    @Transactional
    public ResponseEntity<?> updateGuestStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        return guestRepository.findById(id).map(guest -> {
            guest.setStatus(payload.get("status"));
            return ResponseEntity.ok(guestRepository.save(guest));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/guests/{id}/drinks")
    @Transactional
    public ResponseEntity<Guest> updateDrinks(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return guestRepository.findById(id).map(guest -> {
            guest.setDrinkChoice((String) body.get("drinkChoice"));
            return ResponseEntity.ok(guestRepository.save(guest));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/guests/{id}")
    @Transactional
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}