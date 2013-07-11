package hu.bearmaster.itm.web.controllers;

import javax.validation.Valid;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for user authentication and registration.
 *
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);

    private UserService userService;

    /**
     * .
     * @param userService .
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * .
     * @param model .
     * @return .
     */
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String newUser(final Model model) {
        model.addAttribute("user", new UserVO());
        return "users/register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@Valid @ModelAttribute("user") final UserVO user, final BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.info("Binding errors in user object");
            LOGGER.info("Binding results: {}", result);
            return "users/register";
        }
        try {
            LOGGER.info("User trying to register: {}", user);
            userService.registerUser(user);
        } catch (ItmException e) {
            // TODO hibakezelés?
            LOGGER.error("User registration failed!", e);
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String viewLoginUser(final Model model) {
        model.addAttribute("user", new UserVO());
        return "users/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String loginUser(@ModelAttribute("user") final UserVO user, final BindingResult result) {

        return "redirect:/";
    }

}