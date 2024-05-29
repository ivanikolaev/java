package org.example.service;

import org.example.entity.Bank;
import org.example.entity.Card;
import org.example.repository.BankRepository;
import org.example.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

@Service
@ManagedResource(objectName = "org.example:type=DataExportServiceMBean")
public class DataExportService {

    private static final Logger logger = LoggerFactory.getLogger(DataExportService.class);

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public void exportData() {
        clearDirectory("data");
        exportBanks();
        exportCards();
    }

    private void clearDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        } else {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    private void exportBanks() {
        List<Bank> banks = bankRepository.findAll();
        logger.info("Banks were written");
        for (Bank bank : banks) {
            writeToFile("data/bank_" + bank.getId() + ".txt", bank.toString());
        }
    }

    private void exportCards() {
        List<Card> cards = cardRepository.findAll();
        logger.info("Cards were written");
        for (Card card : cards) {
            writeToFile("data/card_" + card.getId() + ".txt", card.toString());
        }
    }

    private void writeToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            logger.error("Failed to write to file {}: {}", filePath, e.getMessage());
        }
    }

    @ManagedOperation(description = "Export data")
    public void exportDataViaJMX() {
        exportData();
    }

    public void registerMBean() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("org.example:type=DataExportServiceMBean");
            mBeanServer.registerMBean(this, name);
        } catch (Exception e) {
            logger.error("Failed to register MBean: {}", e.getMessage());
        }
    }
}
