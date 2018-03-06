package me.suveen.portfolio.backend.service;

import me.suveen.portfolio.web.domain.frontend.ContactPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.to.address}")
    private String defaultToAddress;

    /**
     * Create simple mail message from a contact Pojo
     * @param contact The feedback pojo
     * @return
     */
    protected SimpleMailMessage prepareSimpleMailMessageFromContactPojo(ContactPojo contact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(contact.getEmail());
        message.setSubject("[Portfolio]: Contact us message from "+contact.getFirstName()+" "+contact.getLastName()+"!");
        message.setText(contact.getMessage());
        return message;
    }

    @Override
    public void sendContact(ContactPojo contact) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromContactPojo(contact));
    }
}
