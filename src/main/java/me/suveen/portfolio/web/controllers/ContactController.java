package me.suveen.portfolio.web.controllers;

import me.suveen.portfolio.backend.service.EmailService;
import me.suveen.portfolio.web.domain.frontend.ContactPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
    /** The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    /* The key which identifies the  feedback payload in the model*/
    private static final String CONTACT_MODEL_KEY = "contact";

    /* The contact me view name */
    private static final String CONTACT_US_VIEW_NAME = "contact/contact";

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGet(ModelMap model){
        LOG.debug("Starting of contact get");
        ContactPojo contactPojo = new ContactPojo();
        model.addAttribute(ContactController.CONTACT_MODEL_KEY, contactPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPost(@ModelAttribute(ContactController.CONTACT_MODEL_KEY) ContactPojo contact){
        LOG.debug("Contact POJO Content : {}", contact);
        emailService.sendContact(contact);
         return ContactController.CONTACT_US_VIEW_NAME;
    }
}
