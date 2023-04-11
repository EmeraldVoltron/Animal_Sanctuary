package animal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import animal.beans.Adopter;
import animal.beans.AdopterLoginForm;
import animal.beans.Animals;
import animal.beans.EmployeeLoginForm;
import animal.repository.AnimalRepository;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class WebController {

    @Autowired
    AnimalRepository animalRepository;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("adopter", new Adopter());
        return "/register";
    }

    @PostMapping("/register")
    public String registerAdopter(@ModelAttribute("adopter") @Valid Adopter adopter, BindingResult result) {
        if (result.hasErrors()) {
            return "/register";
        }
        //TODO: save adopter to database and redirect to login page
        return "redirect:/adopterLogin";
    }
 

    
    @GetMapping("/adopterLogin")
    public String showAdopterLoginForm(Model model) {
        model.addAttribute("adopterLogin", new AdopterLoginForm());
        return "/adopterLogin";
    }

    @PostMapping("/adopterLogin")
    public String submitAdopterLoginForm(@ModelAttribute("adopterLogin") @Valid AdopterLoginForm adopterLoginForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/adopterLogin";
        }
        //TODO: validate login and redirect to the view pets page
        return "redirect:/viewAll";
    }
    
    //AMB 4/11 - added employee login webcontrollers
    @GetMapping("/employeeLogin")
    public String showEmployeeLoginForm(Model model) {
    	model.addAttribute("employeeLogin", new EmployeeLoginForm());
    	return "/employeeLogin";
    }
    
    @PostMapping("/employeeLogin")
    public String submitEmployeeLoginForm(@ModelAttribute("employeeLogin") @Valid EmployeeLoginForm employeeLoginForm, BindingResult result) {
    	if(result.hasErrors()) {
    		return "/employeeLogin";
    	}
    	//TODO: validate login and redirect to employee menu page
    	return "redirect:/employeeMenu";
    }
    
    @GetMapping("/registerEmployee")
    public String showEmployeeRegistrationForm(Model model) {
        return "/registerEmployee";
    }

//    @PostMapping("/registerEmployee")
//    public String registerEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/registerEmployee";
//        }
//        //TODO: save adopter to database and redirect to login page
//        return "redirect:/employeeLogin";
//    }
    
    
    
    //AMB - Added animal methods for webcontroller
    @GetMapping({"/viewAll"})
    public String viewAllAnimals(Model model) {
        if (animalRepository.findAll().isEmpty()) {
            return addNewAnimal(model);
        }
        model.addAttribute("animals", animalRepository.findAll());
        return "viewAnimals";
    }

    @GetMapping("/inputAnimal")
    public String addNewAnimal(Model model) {
        Animals a = new Animals();
        model.addAttribute("newAnimal", a);
        return "addAnimals";
    }

    @PostMapping("/inputAnimal")
    public String addNewAnimal(@ModelAttribute Animals a, Model model) {
        animalRepository.save(a);
        return "redirect:/viewAll";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateAnimal(@PathVariable("id") long id, Model model) {
        Animals a = animalRepository.findById(id).orElse(null);
        model.addAttribute("newAnimal", a);
        return "addAnimals";
    }

    @PostMapping("/update/{id}")
    public String reviseAnimal(@ModelAttribute Animals a, Model model) {
        animalRepository.save(a);
        return "redirect:/viewAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable("id") long id, Model model) {
        Animals a = animalRepository.findById(id).orElse(null);
        animalRepository.delete(a);
        return "redirect:/viewAll";
    }
}
