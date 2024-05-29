package org.example.service;

import org.example.entity.Card;
import org.example.repository.CardRepository;
import org.example.specification.CardSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EmailService emailService;

    public List<Card> findAll() {
        logger.debug("Finding all cards");
        return cardRepository.findAll();
    }

    public Card findById(Long id) {
        logger.debug("Finding card by ID: {}", id);
        return cardRepository.findById(id).orElse(null);
    }

    public Card save(Card card) {
        logger.debug("Saving card: {}", card);
        Card savedCard = cardRepository.save(card);
        emailService.sendSaveNotification("Card", savedCard.toString());
        return savedCard;
    }

    public void deleteById(Long id) {
        logger.debug("Deleting card by ID: {}", id);
        cardRepository.deleteById(id);
    }

    public List<Card> findByCriteria(String cardNumber, String code, Long bankId) {
        logger.debug("Finding cards by criteria - CardNumber: {}, Code: {}, BankId: {}", cardNumber, code, bankId);
        Specification<Card> spec = CardSpecification.getCardsByCriteria(cardNumber, code, bankId);
        return cardRepository.findAll(spec);
    }

    public List<Card> findAllById(Long[] ids) {
        logger.debug("Finding cards by IDs: {}", (Object) ids);
        return cardRepository.findAllById(Arrays.asList(ids));
    }
}
