package com.example.springboot.mvc.validationdemo;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,trimmerEditor);
    }

    @GetMapping("/showForm")
    public String showForm(Model theModel){
        theModel.addAttribute("customer",new Customer());
        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult){

        System.out.println("LastName |"+customer.getLastName()+"|");
        System.out.println(bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "customer-form";
        }
        return "customer-confirmation";
    }
}
