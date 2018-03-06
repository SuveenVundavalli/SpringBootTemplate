package me.suveen.portfolio.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Mock implementation of email service
 */
public class MockEmailService extends AbstractEmailService {

    /** The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOG.debug("Simulating an email service");
        LOG.info(message.toString());
        LOG.debug("Email sent...");
    }
}
