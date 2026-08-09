package com.example.demo.controller; // Adapte le package selon ton projet

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    // Liste en mémoire pour stocker temporairement les messages (ou tu peux créer un model/repository si tu veux les stocker en base de données)
    private List<MessageDto> messagesList = new ArrayList<>();

    // GET : Récupérer tous les messages
    @GetMapping
    public List<MessageDto> getAllMessages() {
        return messagesList;
    }
// DELETE : Supprimer un message par son index
    @DeleteMapping("/{index}")
    public void deleteMessage(@PathVariable int index) {
        if (index >= 0 && index < messagesList.size()) {
            messagesList.remove(index);
        }
    }
    // POST : Ajouter un message
    @PostMapping
    public MessageDto postMessage(@RequestBody MessageDto newMessage) {
        messagesList.add(0, newMessage); // Ajoute au début de la liste
        if (messagesList.size() > 25) {
            messagesList.remove(messagesList.size() - 1); // Garde max 25 messages
        }
        return newMessage;
    }

    // Petite classe interne pour structurer le message (ou tu peux créer un fichier Message.java dans model)
    public static class MessageDto {
        private String author;
        private String text;
        private String time;

        // Getters et Setters
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
    }
}
