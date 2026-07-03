package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.model.Couple;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.CoupleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // =========================================================================
    // AJOUT : Récupérer UN invité par son ID (Résout l'erreur 405 de l'image)
    // URL : GET http://localhost:8080/api/guests/70
    // =========================================================================
    @GetMapping("/guests/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return guestRepository.findById(id)
                .map(guest -> ResponseEntity.ok().body(guest))
                .orElse(ResponseEntity.notFound().build());
    }

    // 1. Récupérer les invités d'un couple spécifique
    // URL : GET http://localhost:8080/api/guests?coupleId=3
    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getGuestsByCouple(@RequestParam(name = "coupleId") Long coupleId) {
        List<Guest> guests = guestRepository.findByCoupleId(coupleId);
        return ResponseEntity.ok(guests);
    }

    // 2. Ajouter un invité avec liaison forcée au Couple
    // URL : POST http://localhost:8080/api/guests
    @SuppressWarnings("unchecked")
    @PostMapping("/guests")
    public ResponseEntity<?> createGuest(@RequestBody Map<String, Object> payload) {
        Guest guest = new Guest();
        guest.setName((String) payload.get("name"));
        guest.setEmail((String) payload.get("email"));
        guest.setTable((String) payload.get("guestTable"));
        guest.setStatus("En attente");
        guest.setDrinkChoice("Aucun");

        // Extraction sécurisée de l'ID du couple
        Long coupleId = null;
        if (payload.containsKey("coupleId") && payload.get("coupleId") != null) {
            coupleId = Long.valueOf(payload.get("coupleId").toString());
        } else if (payload.containsKey("couple") && payload.get("couple") != null) {
            Map<String, Object> coupleMap = (Map<String, Object>) payload.get("couple");
            if (coupleMap.containsKey("id") && coupleMap.get("id") != null) {
                coupleId = Long.valueOf(coupleMap.get("id").toString());
            }
        }

        if (coupleId != null) {
            final Long finalId = coupleId;
            Couple couple = coupleRepository.findById(finalId)
                    .orElseThrow(() -> new RuntimeException("Couple non trouvé avec l'id : " + finalId));
            guest.setCouple(couple); // Liaison BDD indispensable
        } else {
            return ResponseEntity.badRequest().body("L'ID du couple est manquant ou invalide.");
        }

        Guest savedGuest = guestRepository.save(guest);
        return ResponseEntity.ok(savedGuest);
    }

    // 3. Mettre à jour le statut (Gère à la fois le PUT et le PATCH pour éviter l'erreur 405)
    // URL : PUT ou PATCH http://localhost:8080/api/guests/{id}/status
    @RequestMapping(value = "/guests/{id}/status", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> updateGuestStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Le champ 'status' est requis.");
        }

        return guestRepository.findById(id)
                .map(guest -> {
                    guest.setStatus(newStatus);
                    guestRepository.save(guest);
                    return ResponseEntity.ok(guest);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Enregistrer la liste des boissons sélectionnées
    // URL : PATCH http://localhost:8080/api/guests/{id}/drinks
    @PatchMapping("/guests/{id}/drinks")
    public ResponseEntity<Guest> updateDrinks(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return guestRepository.findById(id).map(guest -> {
            if (body.containsKey("drinkChoice")) {
                guest.setDrinkChoice((String) body.get("drinkChoice"));
                guestRepository.save(guest);
            }
            return ResponseEntity.ok(guest);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. Supprimer un invité
    // URL : DELETE http://localhost:8080/api/guests/{id}
    @DeleteMapping("/guests/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        if (guestRepository.existsById(id)) {
            guestRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}