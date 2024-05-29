package org.example.service;

import org.example.entity.Bank;
import org.example.repository.BankRepository;
import org.example.specification.BankSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankService.class);

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private EmailService emailService;

    public List<Bank> findAll() {
        logger.debug("Finding all banks");
        return bankRepository.findAll();
    }

    public Bank findById(Long id) {
        logger.debug("Finding bank by ID: {}", id);
        return bankRepository.findById(id).orElse(null);
    }

    public Bank save(Bank bank) {
        logger.debug("Saving bank: {}", bank);
        Bank savedBank = bankRepository.save(bank);
        emailService.sendSaveNotification("Bank", savedBank.toString());
        return savedBank;
    }

    public void deleteById(Long id) {
        logger.debug("Deleting bank by ID: {}", id);
        bankRepository.deleteById(id);
    }

    public List<Bank> findByCriteria(String name, String address) {
        logger.debug("Finding banks by criteria - Name: {}, Address: {}", name, address);
        Specification<Bank> spec = BankSpecification.getBanksByCriteria(name, address);
        return bankRepository.findAll(spec);
    }
}
