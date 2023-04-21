package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.servises.PersonServise;
import com.example.springsecurityapplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PersonServise personServise;

    @Autowired
    public AuthController(PersonValidator personValidator, PersonServise personServise) {
        this.personValidator = personValidator;
        this.personServise = personServise;
    }

    //@GetMapping("/login")
    //public String loginPage(@RequestParam(name = "error", required = false) String error, Model model) {
    //    if (error != null) {
     //       model.addAttribute("error", "Неправильный логин или пароль");
     //   }
     //   return "login";
    //}

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false) String error, Model model) {
        System.out.println("Error parameter value: " + error);
        if (error != null) {
            model.addAttribute("error", "Неправильный логин или пароль");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }

        personServise.register(person);
        return "redirect:/index";
    }
}
