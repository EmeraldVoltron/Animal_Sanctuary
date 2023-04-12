package animal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import animal.beans.Address;
import animal.beans.Adopter;
//import animal.beans.AdopterLoginForm;
import animal.beans.Animals;
import animal.repository.AnimalRepository;
import animal.repository.AdopterRepository;

@Controller
public class WebController {

    @Autowired
    AnimalRepository animalRepository;
    
    @Autowired
    AdopterRepository adopterRepository;
    
    @GetMapping("/register")
    public String showAdopterRegistrationForm(Model model) {
        model.addAttribute("adopter", new Adopter());
        return "/register";
    }

    @PostMapping("/register")
    public String submitAdopterRegistrationForm(@ModelAttribute("adopter") @Valid Adopter adopter, BindingResult result, @RequestParam("street") String street, @RequestParam("city") String city, @RequestParam("state") String state) {
        if (result.hasErrors()) {
            return "/register";
        }
        Address address = new Address(street, city, state);
        adopter.setAddress(address);
        adopterRepository.save(adopter);
        return "redirect:/adopterLogin";
    }
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
