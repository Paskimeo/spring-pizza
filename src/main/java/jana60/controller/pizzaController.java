package jana60.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.pizza;
import jana60.repository.pizzaRepository;

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
	
}