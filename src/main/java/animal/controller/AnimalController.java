package animal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import animal.beans.Animals;
import animal.repository.AnimalRepository;

@Controller
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/search")
    public ModelAndView showSearchForm() {
        ModelAndView modelAndView = new ModelAndView("search");
        return modelAndView;
    }
    
    @PostMapping("/search")
    public ModelAndView searchAnimals(@RequestParam("query") String query) {
        List<Animals> searchResults = animalRepository.findByBreedContainingIgnoreCaseOrSpeciesContainingIgnoreCaseOrColorContainingIgnoreCase(query, query, query);
        ModelAndView modelAndView = new ModelAndView("searchResults");
        modelAndView.addObject("animals", searchResults);
        return modelAndView;
    }
}
