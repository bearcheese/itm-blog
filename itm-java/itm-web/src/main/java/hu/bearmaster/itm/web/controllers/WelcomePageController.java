package hu.bearmaster.itm.web.controllers;

import hu.bearmaster.itm.common.model.UserVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomePageController {
   
   @RequestMapping("/")
   public String index(Model model) {
      model.addAttribute("user", new UserVO());
      return "index";
   }

}
