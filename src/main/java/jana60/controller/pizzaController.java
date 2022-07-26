package jana60.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.pizza;
import jana60.repository.IngredientiRepository;
import jana60.repository.pizzaRepository;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
@RequestMapping("/")
public class pizzaController {

	@Autowired
	private pizzaRepository repo;
	
	@Autowired
	private IngredientiRepository ingredientiRepo;
	
	@GetMapping
	public String pizzaList(Model model) {
		model.addAttribute("pizza", repo.findAll());
		return "/menu";
	}
	
	//aggiunta controllo
	 @GetMapping("/advanced_search")
	  public String advancedSearch() {
	    return "/search";
	  }

	  @GetMapping("/search")
	  public String search(@RequestParam(name = "queryNome") String queryNome,
	      @RequestParam(name = "queryDescrizione", required = false) String queryDescrizione, Model model) {

	    if (queryNome != null && queryNome.isEmpty()) {
	    	queryNome = null;
	    }
	    if (queryDescrizione != null && queryDescrizione.isEmpty()) {
	    	queryDescrizione = null;
	    }

	    List<pizza> pizza =
	        repo.findByNomeContainingOrDescrizioneContainingIgnoreCase(queryNome, queryDescrizione);
	    model.addAttribute("pizza", pizza);
	    return "/menu";
	  }
	
	
	
	
	
	
	
	
	@GetMapping("/add")
	public String pizzaForm(Model model) {
		model.addAttribute("pizza", new pizza());
		model.addAttribute("listaIngredienti", ingredientiRepo.findAllByOrderByName());
		return "/form";
	}
	
	 @PostMapping("/add")
	  public String save(@Valid @ModelAttribute("pizza") pizza formPizza, BindingResult br, Model model) {
		 boolean hasErrors = br.hasErrors();
		 boolean validateNome = true;
		 if(formPizza.getId() != null) {
			 pizza pizzaBeforeUpdate = repo.findById(formPizza.getId()).get();
			 if(pizzaBeforeUpdate.getNome().equals(formPizza.getNome())) {
				 validateNome = false;
			 }
		}
		 
		 if (validateNome && repo.countByNome(formPizza.getNome()) > 0) {
		      br.addError(new FieldError("pizza", "name", "Non ci possono essere due pizze con lo stesso nome"));
		      hasErrors = true;
		    }
		 
	    if (hasErrors) {
	    	model.addAttribute("listaIngredienti", ingredientiRepo.findAllByOrderByName());
	      return "/form";
	    } else {
	    	
	      try {
			repo.save(formPizza);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to save the pizza");
			model.addAttribute("listaIngredienti", ingredientiRepo.findAllByOrderByName());
			return "/form";
		}
	      
	      return "redirect:/";
	    }
	  }
	 
	 
	 
	 // request a http://localhost:8080/delete/2
	    @GetMapping("/delete/{id}")
	    public String delete(@PathVariable("id") Integer pizzaId, RedirectAttributes ra) {
	    	java.util.Optional<pizza> result = repo.findById(pizzaId);
	    	if (result.isPresent()) {
	    	      // repo.deleteById(bookId);
	    	      repo.delete(result.get());
	    	      ra.addFlashAttribute("successMessage", "pizza " + result.get().getNome() + " deleted!");
	    	      return "redirect:/";
	    	    } else 
	    	    {
	    	      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	    	          "Book con id " + pizzaId + " not present");
	    }
	    }
	    	
	    
	    
	    
	    	 @GetMapping("/edit/{id}")
	    	  public String edit(@PathVariable("id") Integer pizzaId, Model model) {
	    	    java.util.Optional<pizza> result = repo.findById(pizzaId);
	    	    // controllo se il Book con quell'id è presente
	    	    if (result.isPresent()) {
	    	      // preparo il template con al form passandogli il book trovato su db

	    	      model.addAttribute("pizza", result.get());
	    	      model.addAttribute("listaIngredienti", ingredientiRepo.findAllByOrderByName());
	    	      return "/form";
	    	    } else {
	    	      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	    	          "Pizza con id " + pizzaId + " not present");
	    	    }
	    	
	    	    
	    
	    	    
	    	    
	    	
	  }
	
}