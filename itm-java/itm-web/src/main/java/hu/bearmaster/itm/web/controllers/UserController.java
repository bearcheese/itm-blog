package hu.bearmaster.itm.web.controllers;

import java.util.List;

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

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<UserVO> users = userService.listUsers();
		model.addAttribute("users", users);
		return "users/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	public String newUser(Model model) {
		model.addAttribute("user", new UserVO());
		return "users/new";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") UserVO user, BindingResult result) {		
		
		if (result.hasErrors()) {
			logger.info("Binding errors in user object");
			logger.info("Binding results: {}", result);
			return "users/new";
		}
		try {
			logger.info("User trying to register: {}", user);
			userService.registerUser(user);
		} catch (ItmException e) {
			// TODO hibakezelés?
			logger.error("User registration failed!", e);
		}
		return "redirect:/users";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
   public String viewLoginUser(Model model) {
      model.addAttribute("user", new UserVO());
      return "users/login";
   }
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
   public String loginUser(@ModelAttribute("user") UserVO user, BindingResult result) {
      
      return "redirect:/users";
   }

}