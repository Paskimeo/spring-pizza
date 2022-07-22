package jana60.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.pizza;
import jana60.repository.pizzaRepository;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
@RequestMapping("/")
public class pizzaController {

	@Autowired
	private pizzaRepository repo;
	
	@GetMapping
	public String pizzaList(Model model) {
		model.addAttribute("pizza", repo.findAll());
		return "/menu";
	}
	
	@GetMapping("/add")
	public String pizzaForm(Model model) {
		model.addAttribute("pizza", new pizza());
		return "/form";
	}
	
	 @PostMapping("/add")
	  public String save(@Valid @ModelAttribute("pizza") pizza formPizza, BindingResult br) {
	    if (br.hasErrors()) {
	      return "/form";
	    } else {
	      repo.save(formPizza);
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
	    	  public String edit(@PathVariable("id") Integer bookId, Model model) {
	    	    java.util.Optional<pizza> result = repo.findById(bookId);
	    	    // controllo se il Book con quell'id è presente
	    	    if (result.isPresent()) {
	    	      // preparo il template con al form passandogli il book trovato su db

	    	      model.addAttribute("book", result.get());
	    	      return "/book/edit";
	    	    } else {
	    	      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	    	          "Book con id " + bookId + " not present");
	    	    }
	    	
	    	
	  }
	
}