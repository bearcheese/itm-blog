package hu.bearmaster.itm.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/index")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello Spring MVC World!");
        return "index";
    }
}
