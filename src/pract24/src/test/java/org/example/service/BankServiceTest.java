package org.example.service;

import org.example.entity.Bank;
import org.example.repository.BankRepository;
import org.example.specification.BankSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private BankService bankService;

    public BankServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnAllBanks() {
        List<Bank> banks = List.of(new Bank(), new Bank());
        when(bankRepository.findAll()).thenReturn(banks);

        List<Bank> result = bankService.findAll();

        assertEquals(2, result.size());
        verify(bankRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnBankWhenExists() {
        Bank bank = new Bank();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(bank));

        Bank result = bankService.findById(1L);

        assertEquals(bank, result);
    }

    @Test
    void findById_shouldReturnNullWhenNotExists() {
        when(bankRepository.findById(1L)).thenReturn(Optional.empty());

        Bank result = bankService.findById(1L);

        assertNull(result);
    }

    @Test
    void save_shouldSaveAndReturnBank() {
        Bank bank = new Bank();
        when(bankRepository.save(bank)).thenReturn(bank);

        Bank result = bankService.save(bank);

        assertEquals(bank, result);
        verify(emailService, times(1)).sendSaveNotification(eq("Bank"), anyString());
    }

    @Test
    void deleteById_shouldDeleteBank() {
        doNothing().when(bankRepository).deleteById(1L);

        bankService.deleteById(1L);

        verify(bankRepository, times(1)).deleteById(1L);
    }
}
