package org.example.controller;

import org.example.entity.Card;
import org.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.findById(id);
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.save(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card card) {
        card.setId(id);
        return cardService.save(card);
    }
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteById(id);
    }
    @GetMapping("/filter")
    public List<Card> filterCards(@RequestParam(required = false) String cardNumber,
                                  @RequestParam(required = false) String code,
                                  @RequestParam(required = false) Long bankId) {
        return cardService.findByCriteria(cardNumber, code, bankId);
    }
}
