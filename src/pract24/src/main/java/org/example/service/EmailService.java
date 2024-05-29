package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Async
    public void sendSaveNotification(String entityType, String entityDetails) {
        logger.info("Sending email notification for saved {}: {}", entityType, entityDetails);
    }
}
