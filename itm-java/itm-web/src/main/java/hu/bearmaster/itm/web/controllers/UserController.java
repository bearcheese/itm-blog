package hu.bearmaster.itm.web.controllers;

import java.util.List;

import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(final Model model) {
        List<UserVO> users = userService.listUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new UserVO());
        return "users/index";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newUser(final Model model) {
        model.addAttribute("user", new UserVO());
        return "users/new";
    }
}