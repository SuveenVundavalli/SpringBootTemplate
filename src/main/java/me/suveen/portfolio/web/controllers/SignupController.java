package me.suveen.portfolio.web.controllers;

import me.suveen.portfolio.web.domain.frontend.UserAccountPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {

    /**
     * The application logger
     **/
    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

    public static final String SIGNUP_URL_MAPPING = "/signup";

    public static final String SIGNUP_VIEW_NAME = "registration/signup";

    public static final String PAYLOAD_MODEL_KEY_NAME = "payload";

    @RequestMapping(value = SIGNUP_URL_MAPPING, method = RequestMethod.GET)
    public String signupGet(ModelMap model) {
        model.addAttribute(PAYLOAD_MODEL_KEY_NAME, new UserAccountPayload());
        return SIGNUP_VIEW_NAME;
    }


}
