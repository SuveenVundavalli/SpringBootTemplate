package me.suveen.portfolio.backend.service;

import me.suveen.portfolio.web.domain.frontend.ContactPojo;
import org.springframework.mail.SimpleMailMessage;

/** Contract for email service **/
public interface EmailService {

    /**
     * Sends an email with the content in the ContactPojo
     * @Param contactPojo The ContactPojo
     */
    public void sendContact(ContactPojo contact);

    /**
     * Sends an email with the content of the Simple mail message object
     * @param message The object containing the email content
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);

}
