package org.example.service;

import org.example.entity.Card;
import org.example.repository.CardRepository;
import org.example.specification.CardSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CardService cardService;

    public CardServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnAllCards() {
        List<Card> cards = List.of(new Card(), new Card());
        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> result = cardService.findAll();

        assertEquals(2, result.size());
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnCardWhenExists() {
        Card card = new Card();
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        Card result = cardService.findById(1L);

        assertEquals(card, result);
    }

    @Test
    void findById_shouldReturnNullWhenNotExists() {
        when(cardRepository.findById(1L)).thenReturn(Optional.empty());

        Card result = cardService.findById(1L);

        assertNull(result);
    }

    @Test
    void save_shouldSaveAndReturnCard() {
        Card card = new Card();
        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.save(card);

        assertEquals(card, result);
        verify(emailService, times(1)).sendSaveNotification(eq("Card"), anyString());
    }

    @Test
    void deleteById_shouldDeleteCard() {
        doNothing().when(cardRepository).deleteById(1L);

        cardService.deleteById(1L);

        verify(cardRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAllById_shouldReturnCardsMatchingIds() {
        List<Card> cards = List.of(new Card(), new Card());
        when(cardRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(cards);

        List<Card> result = cardService.findAllById(new Long[]{1L, 2L});

        assertEquals(2, result.size());
    }
}
